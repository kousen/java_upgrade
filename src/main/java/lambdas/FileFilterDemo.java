package lambdas;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.logging.Logger;

public class FileFilterDemo {
    public static void main(String[] args) {
        File srcDir = new File("src/main/java");
        File[] files = srcDir.listFiles();
        for (File f : files) {
            System.out.println(f);
        }

        System.out.println("Using a file filter to return directories only");
        files = srcDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        for (File f : files) {
            System.out.println(f);
        }

        // compiler: what are the listFiles overloads?
        // do any take functional interfaces as arguments?
        // if so, do any of the single abstract methods match the
        //   signature of the provided lambda expression?
        FileFilter filter = pathname -> pathname.isDirectory();
        files = srcDir.listFiles(filter);
        for (File f : files) {
            System.out.println(f);
        }

        filter = File::isDirectory;
        files = srcDir.listFiles(filter);
        for (File f : files) {
            System.out.println(f);
        }

        Logger log = Logger.getLogger(FileFilterDemo.class.getName());
        Consumer<String> printer = x -> System.out.println("The value of x is " + x);
        Consumer<String> logger = log::info;

        Consumer<String> printAndLog = printer.andThen(logger);
        List<String> strings = new ArrayList<>();
        strings.add("this");
        strings.add("is");
        strings.add("a");
        strings.add("list");
        strings.add("of");
        strings.add("strings");
//        List.of("this", "is", "a", "list", "of", "strings")
//                .forEach(printAndLog);

        strings.forEach(printAndLog);
        DoubleSupplier random = Math::random;

        strings.stream()
                .map(String::length)
                .forEach(System.out::println);
    }
}
