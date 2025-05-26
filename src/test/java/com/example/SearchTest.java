package com.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {

    @BeforeAll
    static void setUp() {
        String browser = System.getProperty("browser", "firefox");
        Configuration.browser = browser;

        switch (browser) {
            case "firefox" -> WebDriverManager.firefoxdriver(3).setup(1);
            //case "edge"    -> WebDriverManager.edgedriver().setup();
            //case "opera"   -> WebDriverManager.operadriver().setup();
            //default        -> WebDriverManager.chromedriver().setup();
        }
    }

    @Test
    void blabla() throws IOException, InterruptedException {
        open("https://duckduckgo.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=r1-0").shouldHave(text("selenide.org"));


        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.google.com"))
                .GET()
                .build();

        HttpResponse<Void> response =
                client.send(request, HttpResponse.BodyHandlers.discarding());

        assertEquals(200, response.statusCode(), "Google must return HTTP 200");
    }
}

