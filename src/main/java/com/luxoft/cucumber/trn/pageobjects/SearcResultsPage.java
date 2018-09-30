package com.luxoft.cucumber.trn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.*;



public class SearcResultsPage extends BasePageObject{
    public SearcResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    private By searchResultSign = By.xpath("//*[@id='search-filter-reset-form']");
    private By searchResultName = By.xpath("//div/span/a[@title = 'All categories']/span[contains(text(), 'All categories')]/../../../h1");
    private By filteringTags = By.cssSelector(".tag");
    private By shipToMenu = By.xpath("//*[@id='ship_to_select']");


    private  String FILTER_FOR_CATEGORY_LINK = "//h5[text()='%s']/../..//a[contains(.,'%s')]";

    private WebElement filterCheckboxForCategorySection(String filter, String category) {
        String xpath = String.format(FILTER_FOR_CATEGORY_LINK, category, filter);
        return new WebDriverWait(webDriver,10).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))
        );
    }

    public void applyFilterFromCategory(String category, String filter) {
        WebElement filter_link = filterCheckboxForCategorySection(filter,category);
        filter_link.click();
    }

    public boolean isItSearchResultPage(){
        return isElementPresent(searchResultSign);
    }

    public String getSearchResultName(){
        return getText(searchResultName);
    }

    public List<String> getAppliedFilterTagsForSearchResults() {
        return findElementsWithWait(filteringTags).stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
    }

    public SearcResultsPage selectShipToCountry(String country){
        write(country + Keys.ENTER, shipToMenu);
        return this;
    }
}
