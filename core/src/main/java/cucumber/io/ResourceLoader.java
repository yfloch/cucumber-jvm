package cucumber.io;

import java.util.Iterator;

public interface ResourceLoader {
    Iterator<Resource> resources(String path, String suffix);
}
