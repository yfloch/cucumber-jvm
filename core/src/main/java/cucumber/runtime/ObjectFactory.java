package cucumber.runtime;

import cucumber.io.ClasspathResourceLoader;

public interface ObjectFactory {
    void start();

    void stop();

    void addClass(Class<?> clazz);

    <T> T getInstance(Class<T> type);

    public class Factory {
        public static ObjectFactory newInstance(ClasspathResourceLoader classpathResourceLoader) {
            try {
                return classpathResourceLoader.instantiateExactlyOneSubclass(ObjectFactory.class, "cucumber.runtime", new Class[0], new Object[0]);
            } catch (CucumberException ce) {
                return new DefaultJavaObjectFactory();
            }
        }
    }
}
