package cucumber.runtime.java.picocontainer;

import cucumber.runtime.xstream.converters.extended.ToStringConverter;
import cucumber.runtime.converters.LocalizedXStreams;
import cucumber.runtime.java.CucumberConfig;

public class MyCucumberConfig implements CucumberConfig {

    public MyCucumberConfig() {
        System.out.println("**** CUSTOM CUCUMBER CONFIGURATION ****");
    }

    @Override
    public void configure(LocalizedXStreams localizedXStreams) throws Exception {
        localizedXStreams.registerConverter(new ThingConverter());
        localizedXStreams.registerConverter(new ConvertersSteps.LowerCaseToStringConverter(ConvertersSteps.LowerCasePerson.class));
        localizedXStreams.registerConverter(new ToStringConverter(ConvertersSteps.CityHolder.class));
        localizedXStreams.registerConverter(new ToStringConverter(ConvertersSteps.Fruit.class));
    }
}
