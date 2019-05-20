package com.hse.pravilov.page_objects;

import com.hse.pravilov.page_elements.Button;
import com.hse.pravilov.page_elements.TextField;
import org.openqa.selenium.WebDriver;

public class Login {
    public static void login(WebDriver webDriver, String login, String password) {
        webDriver.navigate().to("http://localhost:8080/login");
        new TextField(webDriver, "id_l.L.login").setText(login);
        new TextField(webDriver, "id_l.L.password").setText(password);
        new Button(webDriver, "id_l.L.loginButton").click();
    }
}
