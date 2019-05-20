package com.hse.pravilov.page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextField {
    private WebElement textField;

    public TextField(WebDriver webDriver, String textFieldId) {
        textField = webDriver.findElement(By.id(textFieldId));
    }

    public void setText(String text) {
        textField.sendKeys(text);
    }
}
