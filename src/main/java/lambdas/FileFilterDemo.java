package lambdas;

import java.io.File;
import java.io.FileFilter;

public class FileFilterDemo {

    public File[] listDirectories(String dirName) {
        File myDir = new File(dirName);

//        // Anonymous inner class implementation
//        return myDir.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.isDirectory();
//            }
//        });

        // Lambda expression equivalent to above
        return myDir.listFiles(file -> file.isDirectory());

        // Method reference implementation
//        return myDir.listFiles(File::isDirectory);
    }
}
