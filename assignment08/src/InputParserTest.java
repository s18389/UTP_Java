import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class InputParserTest {

    public static void main(String[] args) {
        File listFile = new File("list.txt");
        System.out.println(InputParser.parse(listFile));
       /* try{
            File listFile = new File("list.txt");
            System.out.println(InputParser.parse(listFile));
        } catch (Exception ex) {
            System.out.println("Problem with file path!!");
            ex.printStackTrace();
        }
*/
    }

}