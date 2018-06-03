package lambdas;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class FilenameFilterTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void listOnlyFilesInRoot() {
        File root = new File("src/main/java");

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir.getAbsolutePath() + "/" + name).isFile();
            }
        };

        String[] fileNames = root.list(filter);
        for (String fileName : fileNames) {
            assertTrue(new File("src/main/java/" + fileName).isFile());
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void listOnlyFilesInRootUsingLambdas() {
        File root = new File("src/main/java");
        FilenameFilter filter = (directory, fileName) ->
                new File(directory.getAbsolutePath() + "/" + fileName).isFile();

        String[] fileNames = root.list(filter);
        Arrays.stream(fileNames)  // Stream<String>
              .map(fileName -> new File("src/main/java/" + fileName)) // Stream<File>
              .forEach(file -> assertTrue(file.isFile()));
    }

}
