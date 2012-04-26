package cucumber.runtime.java.picocontainer;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;

import static org.junit.Assert.assertEquals;

public class ThingSteps {
    private Thing thing;

    @Given("^I have a thing named \"([^\"]*)\"$")
    public void I_have_a_thing_named(Thing thing) {
        this.thing = thing;
    }

    @Then("^its name should be \"([^\"]*)\"$")
    public void its_name_should_be(String name) {
        assertEquals(name, thing.name);
    }
}
