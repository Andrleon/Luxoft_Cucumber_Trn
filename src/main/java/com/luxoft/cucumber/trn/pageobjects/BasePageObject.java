package com.luxoft.cucumber.trn.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BasePageObject<T> {

        protected WebDriver webDriver;

        protected final int MAX_WAIT_TIME = 60;

        public BasePageObject(WebDriver webDriver) {
            this.webDriver = webDriver;
        }

        protected WebElement findElementWithWait(By locator) {
            return new WebDriverWait(webDriver, MAX_WAIT_TIME)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        protected List<WebElement> findElementsWithWait(By locator) {
            return new WebDriverWait(webDriver, MAX_WAIT_TIME).
                    until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        }

        public void captureScreenShot() {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
            String time = LocalDateTime.now().format(dtf);
            File screenshot1 = new File("target/screenshot"+time+".png");
            File outFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(outFile.toPath(), screenshot1.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        protected T getPage(String url){
            webDriver.get(url);
            return (T) this;
        }



        protected void click(By element){
            findElementWithWait(element).click();
        }

        protected void write (String text, By element){
            findElementWithWait(element).sendKeys(text);
        }

        protected void clear (By element){
        findElementWithWait(element).clear();
        }

        protected String getTitle(){
            return webDriver.getTitle();
        }

        protected String getText(By element){
            return findElementWithWait(element).getText();
        }

        protected boolean isElementPresent(By element){
            return webDriver.findElement(element)!= null;
        }



}
