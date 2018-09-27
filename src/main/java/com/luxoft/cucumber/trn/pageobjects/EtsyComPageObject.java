package com.luxoft.cucumber.trn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EtsyComPageObject extends BasePageObject {

    public EtsyComPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    private By searchField = By.id("search-query");
    private By searchButton = By.cssSelector("#gnav-search > div > div.search-button-wrapper.hide > button");


    public EtsyComPageObject openHomePage(){
        getPage("http://www.etsy.com/uk");
        return this;
    }

    public SearcResultsPage searchFor(String searchQuery){
        write(searchQuery, searchField);
        click(searchButton);
        return new SearcResultsPage(webDriver);
    }

}