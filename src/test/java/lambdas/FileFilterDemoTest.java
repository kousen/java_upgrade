package lambdas;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FileFilterDemoTest {
    private FileFilterDemo demo = new FileFilterDemo();

    @Test
    public void listDirectories() {
        File[] directories = demo.listDirectories("src/main/java");
        assertThat(8, is(equalTo(directories.length)));
    }
}