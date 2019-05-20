package com.hse.pravilov;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WebDriverConfiguration {
    private final static Path PATH_TO_FIREFOX_DRIVER = Paths.get("./geckodriver").toAbsolutePath();
    static {
        System.setProperty("webdriver.gecko.driver", PATH_TO_FIREFOX_DRIVER.toString());
    }
}
