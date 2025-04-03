import java.io.File;
import java.io.IOException;

public class Ex2 {
    public static void main(String[] args) throws IOException {
        File dir = new File("C:/Temp/images");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file1 = new File("C:/Temp/file1.txt");
        File file2 = new File("C:/Temp/file2.txt");
        File file3 = new File("C:/Temp/file3.txt");

        if (!file1.exists()) {
            file1.createNewFile();
        }
        if (!file2.exists()) {
            file2.createNewFile();
        }
        if (!file3.exists()) {
            file3.createNewFile();
        }

        File rootDir = new File("C:/Temp/");

        String[] ls = rootDir.list();

        for (String s : ls) {
            System.out.println(s);
        }
    }
}
