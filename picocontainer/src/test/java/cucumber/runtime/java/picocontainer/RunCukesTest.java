package cucumber.runtime.java.picocontainer;

import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//@Cucumber.Options(features = "classpath:cucumber/runtime/java/picocontainer/dates.feature:3:11")
@Cucumber.Options(tags="@gh210", format = "json-pretty")
public class RunCukesTest {
}
