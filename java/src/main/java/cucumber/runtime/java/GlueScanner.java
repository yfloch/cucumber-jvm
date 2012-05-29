package cucumber.runtime.java;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.Order;
import cucumber.io.ClasspathResourceLoader;
import cucumber.runtime.CucumberException;
import cucumber.runtime.Utils;
import cucumber.runtime.converters.LocalizedXStreams;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import static cucumber.io.MultiLoader.packageName;

class GlueScanner {
    private final ClasspathResourceLoader classpathResourceLoader;
    private final Collection<Class<? extends Annotation>> cucumberAnnotationClasses;

    public GlueScanner(ClasspathResourceLoader classpathResourceLoader) {
        this.classpathResourceLoader = classpathResourceLoader;
        cucumberAnnotationClasses = findCucumberAnnotationClasses();
    }

    /**
     * Registers step definitions and hooks.
     *
     * @param javaBackend the backend where stepdefs and hooks will be registered
     * @param gluePaths   where to look
     */
    public void scan(JavaBackend javaBackend, List<String> gluePaths) {
        for (String gluePath : gluePaths) {
            for (Class<?> glueCodeClass : classpathResourceLoader.getDescendants(Object.class, packageName(gluePath))) {
                while (glueCodeClass != null && glueCodeClass != Object.class && !Utils.isInstantiable(glueCodeClass)) {
                    // those can't be instantiated without container class present.
                    glueCodeClass = glueCodeClass.getSuperclass();
                }
                if (glueCodeClass != null) {
                    for (Method method : glueCodeClass.getMethods()) {
                        scan(javaBackend, method, glueCodeClass);
                    }
                }
            }
        }
    }

    // TODO: Move this method to core somewhere.
    public void configureXStream(LocalizedXStreams localizedXStreams, List<String> gluePaths) {
        for (String gluePath : gluePaths) {
            for (CucumberConfig cucumberConfig : classpathResourceLoader.instantiateSubclasses(CucumberConfig.class, gluePath, new Class[0], new Object[0])) {
                try {
                    cucumberConfig.configure(localizedXStreams);
                } catch (CucumberException e) {
                    throw e;
                } catch (Throwable e) {
                    throw new CucumberException(e);
                }
            }
        }
    }

    /**
     * Registers step definitions and hooks.
     *
     * @param javaBackend   the backend where stepdefs and hooks will be registered
     * @param method        a candidate for being a stepdef or hook
     * @param glueCodeClass
     */
    public void scan(JavaBackend javaBackend, Method method, Class<?> glueCodeClass) {
        for (Class<? extends Annotation> cucumberAnnotationClass : cucumberAnnotationClasses) {
            Annotation annotation = method.getAnnotation(cucumberAnnotationClass);
            if (annotation != null && !annotation.annotationType().equals(Order.class)) {
                if (!method.getDeclaringClass().isAssignableFrom(glueCodeClass)) {
                    throw new CucumberException(String.format("%s isn't assignable from %s", method.getDeclaringClass(), glueCodeClass));
                }
                if (!glueCodeClass.equals(method.getDeclaringClass())) {
                    throw new CucumberException(String.format("You're not allowed to extend classes that define Step Definitions or hooks. %s extends %s", glueCodeClass, method.getDeclaringClass()));
                }
                if (isHookAnnotation(annotation)) {
                    javaBackend.addHook(annotation, method);
                } else if (isStepdefAnnotation(annotation)) {
                    javaBackend.addStepDefinition(annotation, method);
                }
            }
        }
    }

    private Collection<Class<? extends Annotation>> findCucumberAnnotationClasses() {
        return classpathResourceLoader.getAnnotations("cucumber.annotation");
    }

    private boolean isHookAnnotation(Annotation annotation) {
        Class<? extends Annotation> annotationClass = annotation.annotationType();
        return annotationClass.equals(Before.class) || annotationClass.equals(After.class);
    }

    private boolean isStepdefAnnotation(Annotation annotation) {
        Class<? extends Annotation> annotationClass = annotation.annotationType();
        return annotationClass.getAnnotation(StepDefAnnotation.class) != null;
    }
}
