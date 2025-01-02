package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginAutomationTest {
    @Test
     void testLogin() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ASUS\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            // Navigate to the login page
            driver.get("http://the-internet.herokuapp.com/login");

            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            // Perform login
            usernameField.sendKeys("tomsmith");
            passwordField.sendKeys("SuperSecretPassword!");
            loginButton.click();

            // Validate successful login by checking for a success message
            WebElement successMessage = driver.findElement(By.cssSelector("div.flash.success"));
            assertTrue(successMessage.getText().contains("You logged into a secure area!"), "Login success message not found");
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
