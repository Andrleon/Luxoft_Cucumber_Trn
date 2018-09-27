package com.luxoft.cucumber.trn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearcResultsPage extends BasePageObject{
    public SearcResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    By searchResultSign = By.xpath("//div/h2[@class = 'screen-reader-only' and contains(text(), 'Результаты')]");
    By searchResultName = By.xpath("//div/span/a[@title = 'Все категории']/span[contains(text(), 'Все категории')]/../../../h1");
    public boolean isItSearchResultPage(){
        return isElementPresent(searchResultSign);
    }

    public String getSearchResultName(){
        return getText(searchResultName);
    }

}
