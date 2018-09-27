package com.luxoft.cucumber.trn.stepdefs;

import com.luxoft.cucumber.trn.pageobjects.EtsyComPageObject;
import com.luxoft.cucumber.trn.pageobjects.SearcResultsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepDefinitions {

    /////////как закрыть окно браузера???
    WebDriver wd = new ChromeDriver();
    EtsyComPageObject etsyPage = new EtsyComPageObject(wd);
    SearcResultsPage etsySearchResultsPage;


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


   }
