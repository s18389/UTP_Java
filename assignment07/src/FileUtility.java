import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtility {

    private static final int pathDept = 50;

    private FileUtility(){

    }

    public static List<File> searchByName(File dir, String name)   {
        try {
            return search(dir, name, FileUtility::searchByNamePredicate);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
            return null;
        }
    }

    public static List<File> searchByContent(File dir, String content)   {
        try {
            return search(dir, content, FileUtility::searchByContentPredicate);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
            return null;
        }
    }

    private static List<File> search(File dir, String toFind, FilePredicate predicate) {
        if(dir.isDirectory() ||
                dir.exists() ||
                dir.canRead()) {
            try {
                Path path = dir.toPath();
                List<File> files = Files.find(path, pathDept, (p, attributes) -> predicate.test(p, toFind)).map(p -> p.toFile()).filter(File::isFile).collect(Collectors.toList());
                return files;
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error");
                return null;
            }
        }
        else return null;
    }

    private static boolean searchByNamePredicate(Path path, String name){
        File file = path.toFile();
        if (file.isDirectory()) return true;
        String fileName = file.getName();
        return fileName.contains(name);
    }

    private static boolean searchByContentPredicate(Path path, String content)   {
        try {
            File file = path.toFile();
            if(file.isDirectory()) return true;
            InputStream input = new FileInputStream(path.toFile());
            return FileContentUtility.contains(input, content, file.length());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
            return false;
        }
    }
}
