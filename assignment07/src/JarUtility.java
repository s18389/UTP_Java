import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class JarUtility {

    private static List<String> search(JarFile jarFile, String name, Predicate<? super JarEntry> predicate){
        try{
            List<String> fileNames = jarFile.stream().filter(predicate).map(JarEntry::getName).collect(Collectors.toList());
            jarFile.close();
            return fileNames;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
            return null;
        }
    }

    private static boolean searchByNamePredicate(JarEntry entryJar,  String name){
        return entryJar.getName().contains(name);
    }

    private static boolean searchByContentPredicate( JarEntry entryJar,  JarFile fileJar, String content){
        try{
            InputStream input = fileJar.getInputStream(entryJar);
            return FileContentUtility.contains(input, content, entryJar.getSize());
        }catch (Exception e){
            e.printStackTrace();
           System.out.println("Error");
            return false;
        }
    }

    public static List<String> searchByName(JarFile directory, String name){

        File file = new File(directory.getName());
       JarFile fileJar = null;
       try {
           fileJar = new JarFile(file);
           return search(fileJar, name, jarEntry -> searchByNamePredicate(jarEntry, name));
       }catch (Exception e){
           e.printStackTrace();
           System.out.println("Error");
           return null;
       }finally {
           if (fileJar != null){
               try {
                   fileJar.close();
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
                JarFile fileJar = new JarFile(file);
                List<String> fileNames = search(fileJar, content, jarEntry -> searchByContentPredicate(jarEntry, fileJar, content));
                fileJar.close();
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

