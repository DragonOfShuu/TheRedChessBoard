package theredchessboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class BuildResources {
    public static void main(String[] args) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("themes");
        String path = url.getPath();
        
        File directory = new File(path);
        System.out.println(directory);
        File[] folders = directory.listFiles();

        // "app/src/main/resources/themes/themes.txt"
        File file = new File(
            path+"/themes.txt"
        );
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for (File folder : folders) {
            bw.write(folder.toString());
            bw.newLine();
        }

        bw.close();
    }
}
