package cucumber.runtime.java.picocontainer;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

public class ThingConverter extends AbstractSingleValueConverter {
    @Override
    public boolean canConvert(Class type) {
        return Thing.class.equals(type);
    }

    @Override
    public Object fromString(String str) {
        return new Thing(str);
    }
}
