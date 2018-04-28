package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

public class FileFilterTest {
    @Test
    public void fileFilterAnonInnerClass() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(); // list everything
        for (File file : files) {
            System.out.println(file);
        }

        files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        System.out.println("Only list directories: ");
        System.out.println(files.length);
        for (File file : files) {
            System.out.println(file);
        }
    }
}
