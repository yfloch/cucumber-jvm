package cucumber.runtime.java.picocontainer;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToStringConverter;
import cucumber.annotation.en.Given;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * tests various ways of mix and matching xstream converters on plain steps and data tables
 */
public class ConvertersSteps {

    @Given("^I have some fruit named \"([^\"]*)\"$")
    public void I_have_some_fruit_named(Fruit fruit) {
        assertEquals("Citrus", fruit.getName());
    }

    @Given("^I have some lower case person named \"([^\"]*)\"$")
    public void I_have_some_lower_case_person_named(LowercasePerson lowercasePerson) {
        assertEquals("charlie", lowercasePerson.getName());
    }

    @Given("^I have some city holder named \"([^\"]*)\"$")
    public void I_have_some_city_holder_named(CityHolder cityHolder) {
        assertEquals("Chicago", cityHolder.getCity().getName());
    }

    @Given("^I have some stuff in a data table:$")
    public void I_have_some_stuff_in_a_data_table(List<LowerCaseFruitPersonLowerCaseCity> lowerCaseFruitPersonLowerCaseCities) {
        for (LowerCaseFruitPersonLowerCaseCity lowerCaseFruitPersonLowerCaseCity : lowerCaseFruitPersonLowerCaseCities) {
            assertEquals("banana", lowerCaseFruitPersonLowerCaseCity.lowercaseFruit.getName());
            assertEquals("Joan", lowerCaseFruitPersonLowerCaseCity.person.getName());
            assertEquals("london", lowerCaseFruitPersonLowerCaseCity.lowercaseCity.getName());
        }
    }

    public static class LowerCaseFruitPersonLowerCaseCity {
        @XStreamConverter(LowerCaseToStringConverter.class)
        public Fruit lowercaseFruit;
        @XStreamConverter(ToStringConverter.class)
        public Person person;
        @XStreamConverter(LowerCaseToStringConverter.class)
        public City lowercaseCity;
    }

    @XStreamConverter(LowerCaseToStringConverter.class)
    public static class LowercasePerson extends Person {
        public LowercasePerson(String name) {
            super(name);
        }
    }

    @XStreamConverter(ToStringConverter.class)
    public static class CityHolder {
        private City city;

        public CityHolder(String name) {
            this.city = new City(name);
        }

        public City getCity() {
            return city;
        }
    }

    @XStreamConverter(ToStringConverter.class)
    public static class Fruit extends Named {
        public Fruit(String name) {
            super(name);
        }
    }

    public static class Person extends Named {
        public Person(String name) {
            super(name);
        }
    }

    public static final class City extends Named {
        public City(String name) {
            super(name);
        }
    }

    private static abstract class Named {
        private String name;

        public Named(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static class LowerCaseToStringConverter extends ToStringConverter {
        public LowerCaseToStringConverter(Class<?> clazz) throws NoSuchMethodException {
            super(clazz);
        }

        @Override
        public Object fromString(String str) {
            return super.fromString(str.toLowerCase());
        }
    }
}