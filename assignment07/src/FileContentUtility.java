import java.io.InputStream;
import java.util.Scanner;

public final class FileContentUtility {

    public static boolean contains(InputStream in, String content, long size){
        Scanner scanner = new Scanner(in);
        String result = scanner.findWithinHorizon(content, (int) size);
        scanner.close();
        return result != null;
    }
}
