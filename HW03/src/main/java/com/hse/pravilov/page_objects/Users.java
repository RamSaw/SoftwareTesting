package com.hse.pravilov.page_objects;

import com.hse.pravilov.page_elements.Button;
import org.openqa.selenium.WebDriver;

public class Users {
    private final static String USERS_PAGE_URL = "http://localhost:8080/users";
    private final static String CREATE_NEW_USER_BUTTON_ID = "id_l.U.createNewUser";
    private Button createNewUserButton;
    private WebDriver webDriver;

    public Users(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.navigate().to(USERS_PAGE_URL);
        createNewUserButton = new Button(webDriver, CREATE_NEW_USER_BUTTON_ID);
    }

    public CreateUserForm getCreateUserForm() {
        createNewUserButton.click();
        return new CreateUserForm(webDriver);
    }
}
