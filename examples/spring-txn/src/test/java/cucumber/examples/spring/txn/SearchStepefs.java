package cucumber.examples.spring.txn;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.selenium.CucumberDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchStepefs {

    @Autowired
    private CucumberDriver webDriver;

    @Given("^I enter \"([^\"]*)\" in the search field$")
    public void I_enter_something_in_the_search_field(String query) {
        webDriver.get("http://localhost:8080/search");
        WebElement element = webDriver.findElement(By.id("query"));
        element.sendKeys(query);
    }

    @When("^I search for \"([^\"]*)\"$")
    public void I_search_for(String query) throws Throwable {
        I_enter_something_in_the_search_field(query);
        webDriver.findElement(By.id("search")).submit();
    }
}
