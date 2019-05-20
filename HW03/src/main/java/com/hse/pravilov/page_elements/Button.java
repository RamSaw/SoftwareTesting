package com.hse.pravilov.page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button {
    private WebElement button;

    public Button(WebDriver webDriver, String buttonId) {
        button = webDriver.findElement(By.id(buttonId));
    }

    public void click() {
        button.click();
    }
}
