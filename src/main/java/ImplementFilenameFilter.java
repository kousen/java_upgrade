import java.io.File;
import java.io.FilenameFilter;

public class ImplementFilenameFilter {
    public static boolean javaFiles(File dir, String name) {
        return name.endsWith(".java");
    }

    public static void main(String[] args) {
        File directory = new File("src/main/java");

        // Anonymous inner class implementation
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        };

        // Lambda expression
        filter = (dir, name) -> name.endsWith(".java");

        String[] files = directory.list(filter); // Use FilenameFilter
        for (String name : files) {
            System.out.println(name);
        }

        files = directory.list(ImplementFilenameFilter::javaFiles); // Use FilenameFilter
        for (String name : files) {
            System.out.println(name);
        }
    }
}
