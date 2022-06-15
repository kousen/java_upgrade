package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void testListFiles() {
        File[] files = root.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(21, files.length);
    }

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    public void testListFiles_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void testListFiles_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(13, directories.length);
    }

    @SuppressWarnings({"Convert2MethodRef", "CodeBlock2Expr"})
    @Test
    public void testListFiles_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void testListFiles_assignedToVariable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    private FileFilter getDirectories() {
        return pathname -> pathname.isDirectory();
    }

    @Test
    public void testListFiles_getLambdaFromMethod() {
        File[] directories = root.listFiles(getDirectories());
        assertEquals(13, directories.length);
    }
}
