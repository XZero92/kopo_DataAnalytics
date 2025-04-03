import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class Ex3 {
    public static void main(String[] args) throws IOException {
        File file = new File("C:/Temp/writeFile.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        if(file.canWrite()) {
            Files.writeString(file.toPath(), "이것은 java의 Files 클래스를 이용하여 작성한 파일입니다.");
        }
    }
}
