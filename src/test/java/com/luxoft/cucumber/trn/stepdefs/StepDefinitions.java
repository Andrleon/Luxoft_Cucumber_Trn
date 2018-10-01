package com.luxoft.cucumber.trn.stepdefs;

import com.luxoft.cucumber.trn.pageobjects.EtsyComPageObject;
import com.luxoft.cucumber.trn.pageobjects.SearcResultsPage;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import cucumber.api.java.es.E;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StepDefinitions {

    WebDriver wd;
    EtsyComPageObject etsyPage;
    SearcResultsPage etsySearchResultsPage;

    @Before
    public void setUp(){
        wd = new ChromeDriver();
        etsyPage = new EtsyComPageObject(wd);
    }



    @Given("I am on the main page")
    public void i_am_on_the_main_page() {
        etsyPage.openHomePage();
    }

    @When("I accept terms and conditions")
    public void i_accept_terms_and_conditions() {
        System.out.println("It looks like This step is unnecessary");
    }

    @When("I search for \"(.*?)\" items")
    public void i_search_for_items(String searchQuery) {
        etsySearchResultsPage = etsyPage.searchFor(searchQuery);
    }

    @Then("I see search results")
    public void i_see_search_results() {
        Assertions.assertTrue(etsySearchResultsPage.isItSearchResultPage());
    }

    @Then("I get search results for \"(.*?)\" items")
    public void i_get_search_results_for_items(String searchResults) {
        Assertions.assertEquals(searchResults, etsySearchResultsPage.getSearchResultName());
    }


    @When("I search for items and apply filters:")
    public void i_search_for_items_and_apply_filters(DataTable dataTable) {
        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> el : values){
            etsySearchResultsPage = etsyPage.searchFor(el.get("items"));
            etsySearchResultsPage.applyFilterFromCategory(el.get("filter category"), el.get("filter"));
        }
    }

    /*@When("I search for items and apply filters:")
    public void i_search_for_items_and_apply_filters(DataTable dataTable) {
        List<List<String>> values = dataTable.asLists();
        String searchQuery = values.get(1).get(0);
        String category = values.get(1).get(1);
        String filter = values.get(1).get(2);
        etsySearchResultsPage = etsyPage.searchFor(searchQuery);
        etsySearchResultsPage.applyFilterFromCategory(category, filter);
    }
*/

    @Then("next filter tags are visible:")
    public void next_filter_tags_are_visible(DataTable dataTable) {
        List<String> expectedTags = dataTable.rows(1).asList();
        Assertions.assertLinesMatch(expectedTags, etsySearchResultsPage.getAppliedFilterTagsForSearchResults());
    }

    @When("I set page language to \"(.*?)\"")
    public void i_set_page_language_to(String language) {
        etsyPage = etsyPage.changeLanguageTo(language);

    }

    @When("I select \"(.*?)\" ship destination")
    public void i_select_ship_destination(String country) {
        etsySearchResultsPage = etsySearchResultsPage.selectShipToCountry(country);

    }

    @When("I set minimum price to \"(.*?)\"")
    public void i_set_minimum_price_to(String priceFrom) {
        etsySearchResultsPage.setPriceFilterFrom(priceFrom);
    }

    @When("I set maximum price to \"(.*?)\"")
    public void i_set_maximum_price_to(String priceTo) {
        etsySearchResultsPage.setPriceFilterTo(priceTo);
    }

    @When("I filter items by price")
    public void i_filter_items_by_price() {
        etsySearchResultsPage.applyFilterByPrice();
    }

    @Then("next price filtering tag \"(.*?)\" is visible")
    public void next_price_filtering_tag_is_visible(String filterByPriceTag) {
        Assertions.assertEquals(filterByPriceTag, etsySearchResultsPage.getAppliedFilterTagsForSearchResults().get(0));
    }


    @After
    public void tearDown(){
        wd.quit();
    }
   }
