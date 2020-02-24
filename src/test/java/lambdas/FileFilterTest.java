package lambdas;

import org.junit.Test;

import java.io.File;

public class FileFilterTest {
    @Test
    public void fileFilterNoArgs() {
        File root = new File("src/main/java");
        for (File file : root.listFiles()) {
            System.out.println(file.getName());
        }
    }
}
