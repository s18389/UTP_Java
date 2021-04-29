import org.junit.Assert;
import org.junit.Before;
import java.io.File;
import java.util.List;

public final class Test {

    private final File dir = new File("dir");
    private final File fileTest = new File("dir/dir2/file.txt");
    private final File jarTest = new File("dir/jarfile.jar");
    private final File zipTest = new File("dir/fileZip.zip");

    @Before
    public void before(){
        Assert.assertTrue(dir.exists());
        Assert.assertTrue(fileTest.exists());
        Assert.assertTrue(jarTest.exists());
        Assert.assertTrue(zipTest.exists());
    }

    @org.junit.Test
    public void searchByNameFile() {
        List<File> files = FileUtility.searchByName(dir, "file.txt");
        System.out.println("FILE Search By Name");
        System.out.println("Files matches: " + files);
     //   System.out.println(files.get(0));
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("file.txt", file.getName());
        Assert.assertEquals(fileTest, file);
    }

    @org.junit.Test
    public void searchByContentFile(){
        List<File> files = FileUtility.searchByContent(dir, "contentTxt");
        System.out.println("FILE Content matches in " + files);
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("file.txt", file.getName());
        Assert.assertEquals(fileTest, file);
    }

    @org.junit.Test
    public void searchByNameJar() {
        List<File> files = FileUtility.searchByName(dir, "jarfile.jar");
        System.out.println("JAR Search By Name");
        System.out.println("Files matches: " + files);
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("jarfile.jar", file.getName());
        Assert.assertEquals(jarTest, file);
    }

    @org.junit.Test
    public void searchByContentJar(){
        List<File> files = FileUtility.searchByContent(dir, "dupaxD");
        System.out.println("JAR Content matches in " + files);
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("jarfile.jar", file.getName());
        Assert.assertEquals(jarTest, file);
    }

    @org.junit.Test
    public void searchByNameZip() {
        List<File> files = FileUtility.searchByName(dir, "fileZip.zip");
        System.out.println("ZIP Search By Name");
        System.out.println("Files matches: " + files);
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("fileZip.zip", file.getName());
        Assert.assertEquals(zipTest, file);
    }

    @org.junit.Test
    public void searchByContentZip(){
        List<File> files = FileUtility.searchByContent(dir, "contentInZip");
        System.out.println("ZIP Content matches in " + files);
        Assert.assertEquals(1, files.size());
        File file = files.get(0);
        Assert.assertEquals("fileZip.zip", file.getName());
        Assert.assertEquals(zipTest, file);
    }

}
