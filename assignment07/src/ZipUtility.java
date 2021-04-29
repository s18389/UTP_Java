import java.io.File;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipUtility {

    private static List<String> search(ZipFile zipFile, String name, Predicate<? super ZipEntry> predicate){
        try{
            List<String> fileNames = zipFile.stream().filter(predicate).map(ZipEntry::getName).collect(Collectors.toList());
            zipFile.close();
            return fileNames;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
            return null;
        }
    }

    private static boolean searchByNamePredicate(ZipEntry entryZip, String name){
        return entryZip.getName().contains(name);
    }

    private static boolean searchByContentPredicate( ZipEntry entryZip, ZipFile fileZip, String content){
        try{
            ZipInputStream input = (ZipInputStream) fileZip.getInputStream(entryZip);
         //   ZipInputStream input = fileZip.getInputStream(entryZip);
            return FileContentUtility.contains(input, content, entryZip.getSize());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
            return false;
        }
    }

    public static List<String> searchByName(ZipFile directory, String name){

        File file = new File(directory.getName());
        ZipFile fileZip = null;
        try {
            fileZip = new ZipFile(file);
            return search(fileZip, name, zipEntry -> searchByNamePredicate(zipEntry, name));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
            return null;
        }finally {
            if (fileZip != null){
                try {
                    fileZip.close();
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Error");
                }
            }
        }
    }



    public static List<String> searchByContent(File file, String content){
        if(file.isDirectory()){
            try{
                ZipFile fileZip = new ZipFile(file);
                List<String> fileNames = search(fileZip, content, zipEntry -> searchByContentPredicate(zipEntry, fileZip, content));
                fileZip.close();
                return fileNames;
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error");
                return null;
            }
        }
        else{
            System.out.println("There is no such a file in the direcotry");
            return null;
        }
    }

}

