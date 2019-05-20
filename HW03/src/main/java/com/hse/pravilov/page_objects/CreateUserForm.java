package com.hse.pravilov.page_objects;

import com.hse.pravilov.page_elements.Button;
import com.hse.pravilov.page_elements.TextField;
import org.openqa.selenium.WebDriver;

public class CreateUserForm {
    private final static String LOGIN_ID = "id_l.U.cr.login";
    private final static String PASSWORD_ID = "id_l.U.cr.password";
    private final static String CONFIRM_PASSWORD_ID = "id_l.U.cr.confirmPassword";
    private final static String OK_BUTTON_ID = "id_l.U.cr.createUserOk";
    private TextField login;
    private TextField password;
    private TextField confirmPassword;
    private Button submit;

    CreateUserForm(WebDriver webDriver) {
        login = new TextField(webDriver, LOGIN_ID);
        password = new TextField(webDriver, PASSWORD_ID);
        confirmPassword = new TextField(webDriver, CONFIRM_PASSWORD_ID);
        submit = new Button(webDriver, OK_BUTTON_ID);
    }

    public void createUser(User user) {
        login.setText(user.getLogin());
        password.setText(user.getPassword());
        confirmPassword.setText(user.getPassword());
        submit.click();
    }
}
