package cucumber.runtime.java;

import cucumber.runtime.converters.LocalizedXStreams;

public interface CucumberConfig {
    void configure(LocalizedXStreams localizedXStreams) throws Throwable;
}
