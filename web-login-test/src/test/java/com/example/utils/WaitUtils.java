package com.example.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT_SECONDS = 120;

    /**
     * รอจนกว่าจะเห็น elements ไม่เกินเวลา {@link #DEFAULT_TIMEOUT_SECONDS}
     *
     * @param driver
     * @param locator
     * @return
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * 
     * @param driver
     * @param locator
     * @return
     */
    public static List<WebElement> waitForAllElementsToBeVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  
    }

    /**
     * รอจนกว่า web element พร้อม click ไม่เกินเวลา
     * {@link #DEFAULT_TIMEOUT_SECONDS}
     *
     * @param driver
     * @param locator
     * @return
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
