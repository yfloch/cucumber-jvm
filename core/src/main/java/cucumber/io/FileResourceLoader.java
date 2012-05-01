package cucumber.io;

import java.io.File;
import java.util.Iterator;

public class FileResourceLoader implements ResourceLoader {
    @Override
    public Iterator<Resource> resources(String path, String suffix) {
        File root = new File(path);
        return new FileResourceIterator(root, root, suffix);
    }
}
