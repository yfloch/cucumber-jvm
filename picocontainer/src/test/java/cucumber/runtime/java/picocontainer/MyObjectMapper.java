package cucumber.runtime.java.picocontainer;

import cucumber.runtime.converters.LocalizedXStreams;
import cucumber.runtime.java.ObjectMapper;

public class MyObjectMapper implements ObjectMapper {
    @Override
    public void configure(LocalizedXStreams localizedXStreams) {
        localizedXStreams.registerConverter(new ThingConverter());
    }
}
