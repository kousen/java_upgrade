package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(21, files.length);
        }
    }

    @Test
    public void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(file -> file.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void testListFilesOnAFile() {
        File thisFile = new File("src/test/java/lambdas/FileFilterTest.java");
        File[] files = thisFile.listFiles();
        // Probably don't want assertAll here, because if the files ref is not null,
        // do want to skip the other test
        assertAll( // Executable..., implemented as lambdas
                () -> assertNull(files),
                () -> assertThrows(NullPointerException.class, () -> System.out.println(files.length))
        );
    }

    @Test
    public void listDirectories_blockLambda() {
        File[] directories = root.listFiles(file -> {
            return file.isDirectory();
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listDirectories_assignToVariable() {
        FileFilter filter = file -> file.isDirectory();
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listJavaSourceFiles_fileNameFilter() {
        // In FilenameFilter, the single abstract method is:
        // boolean accept(File dir, String name)
        File[] directories = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (directories != null) {
            assertEquals(8, directories.length);
        }
    }

    @Test
    public void listDirectories_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void printStrings() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(str -> System.out.println(str));  // default method in Iterable
        // strings.forEach(System.out::println);
    }

    private String getMessage() {
        System.out.println("Inside getMessage");
        return "Argument should be false";
    }

    @Test
    void useMessageSupplier() {
        // assertTrue(2 + 2 == 4, getMessage()); // Last arg is String
        assertTrue(2 + 2 == 4, () -> getMessage());  // Last arg is Supplier<String>
    }

    @Test
    void retrieveAValueFromArrayArg() {
        List<String> strings = Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace");
        strings.stream()
                .map(name -> name.split("\\s+"))
                .map(array -> array[1])
                .forEach(System.out::println);
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "This is my error message";
    }

    @SuppressWarnings({"ConstantConditions", "SimplifiableAssertion"})
    @Test
    void assertWithLazySupplier() {
        String abc = "abc";
        // assertTrue("abc".length() == 3, getErrorMessage()); // always calls the error method
        assertTrue("abc".length() == 3, () -> getErrorMessage()); // only calls error message if there's an error
        // assertTrue("abc".length() == 3, this::getErrorMessage);
    }
}
