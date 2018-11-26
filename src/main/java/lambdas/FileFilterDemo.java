package lambdas;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

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
    }
}
