package com.luxoft.cucumber.trn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EtsyComPageObject extends BasePageObject {

    private String homePageURL = "http://www.etsy.com";
    private By searchField = By.id("search-query");
    private By searchButton = By.cssSelector("#gnav-search > div > div.search-button-wrapper.hide > button");
    private By localisationButton = By.xpath("//span[@class = 'ui-toolkit unified-locale-settings']/button");
    private By localisationMenuLanguageField = By.xpath("//*[@id='locale-overlay-select-language_code']");
    private By localisationSaveButton = By.xpath("//*[@id='locale-overlay-save']");

    public EtsyComPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public EtsyComPageObject openHomePage(){
        webDriver.get(homePageURL);
        return this;
    }

    public SearcResultsPage searchFor(String searchQuery){
        write(searchQuery + Keys.ENTER, searchField);
        //click(searchButton);
        return new SearcResultsPage(webDriver);
    }

    public EtsyComPageObject changeLanguageTo(String language){
        click(localisationButton);
        //click(localisationMenuLanguageField);
        write(language + Keys.ENTER, localisationMenuLanguageField);
        click(localisationSaveButton);
        return this;
    }
}