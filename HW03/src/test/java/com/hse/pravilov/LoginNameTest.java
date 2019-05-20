package com.hse.pravilov;

import com.hse.pravilov.page_objects.Login;
import com.hse.pravilov.page_objects.User;
import com.hse.pravilov.page_objects.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

class LoginNameTest {
    private final static String DEFAULT_PASSWORD = "pass";
    private final static String ROOT_USER = "root";
    private final static String ROOT_PASSWORD = "root";

    private WebDriver webDriver;

    @BeforeEach
    void setUp() {
        webDriver = new FirefoxDriver();
        Login.login(webDriver, ROOT_USER, ROOT_PASSWORD);
    }

    private void checkUserCreationOnSuccess(String userName) {
        // it is sufficient to check URL because it contains login of new created user.
        String expectedUrl = "http://localhost:8080/editUser/" + userName;

        Wait<WebDriver> wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        assertEquals(expectedUrl, webDriver.getCurrentUrl());
    }

    private void createUser(String userName) {
        User newUser = new User(userName, DEFAULT_PASSWORD);
        new Users(webDriver).getCreateUserForm().createUser(newUser);
    }

    private void createUserWithSuccessChecking(String userName) {
        createUser(userName);
        checkUserCreationOnSuccess(userName);
    }

    @Test
    void defaultPositiveTest() {
        createUserWithSuccessChecking("testLoginName1");
    }

    @Test
    void supportsUnderlineTest() {
        createUserWithSuccessChecking("test_login_name2");
    }

    @Test
    void loginNameCanContainOnlyNumbersTest() {
        createUserWithSuccessChecking("123");
    }

    @Test
    void loginNameCanBeEmailTest() {
        createUserWithSuccessChecking("myemail@gmail.com");
    }

    @Test
    void supportsUnicodeSymbolsTest() {
        String userName = "это_китайские_символы_漢字";
        createUser(userName);

        String expectedTitle = "User: " + userName;
        Wait<WebDriver> wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        assertEquals(expectedTitle, webDriver.getTitle());
    }

    @Test
    void isProtectedFromSqlInjectionTest() {
        createUser("105; DROP TABLE Users");
        String expectedErrorMessage = "Restricted character ' ' in the name";
        assertEquals(expectedErrorMessage, webDriver.findElement(By.className("errorSeverity")).getText());
    }

    @Test
    void loginNameCannotBeEmptyTest() {
        assertThrows(NoSuchElementException.class, () -> webDriver.findElement(By.className("error-bulb2")));
        createUser("");
        assertTrue(webDriver.findElement(By.className("error-bulb2")).isDisplayed());
    }

    @Test
    void userCannotBeCreatedTwiceTest() {
        String userName = "testLoginName3";
        createUserWithSuccessChecking(userName);
        createUser("testLoginName3");
        String expectedErrorMessage = "Value should be unique: login";
        assertEquals(expectedErrorMessage, webDriver.findElement(By.className("errorSeverity")).getText());
    }

    @AfterEach
    void tearDown() {
        webDriver.close();
    }
}
