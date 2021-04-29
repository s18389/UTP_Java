import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PersonDatabaseTest {

    private PersonDatabase test1;
    private File listFile;
    private List<Person> list1, list2;
    private final DateFormat testFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Comparator<Person> firstname = new FirstNameComparator();
    private final Comparator<Person> birthdate = new BirthdateComparator();

    @Before
    public void before() {
        listFile = new File("list.txt");
        int id = Person.getId();
        test1 = new PersonDatabase(InputParser.parse(listFile));
        try {
            test1 = new PersonDatabase(listFile);
        } catch (IOException e) { e.printStackTrace(); }
        Person.set_id(id);

        try {
            list1 = Arrays.asList(
                    new Person("John", "Smith", testFormat.parse("1980-01-03")),
                    new Person("Mark", "Brown", testFormat.parse("1976-02-02")),
                    new Person("Adam", "Majowski", testFormat.parse("1000-10-10")),
                    new Person("Adam", "Majowski", testFormat.parse("1000-10-10")),
                    new Person("Robert", "Majowski", testFormat.parse("1000-10-10"))
            );
        } catch (Exception e) { System.exit(1); }
    }

    @Test
    public void sortedByFirstName() {
        System.out.println("Sorted By First Name: ");
        list1.sort(firstname);
        //     System.out.println(list1);
        list2 = test1.sortedByFirstName();
        //      System.out.println(list2);
        System.out.println();
        for (int i = 0; i < list1.size(); i++) Assert.assertEquals(list1.get(i), list2.get(i));
    }

    @Test
    public void sortedByBirthDate() {
        System.out.println("Sorted By Birth Date: ");
        list1.sort(birthdate);
        list2 = test1.sortedByBirthdate();
        System.out.println();
        for (int i = 0; i < list1.size(); i++) Assert.assertEquals(list1.get(i), list2.get(i));
    }

    @Test
    public void sortedByurnameFirstNameAndBirthdate() {
        System.out.println("Sorted By Surname First Name and Birth Date: ");
        list1 = test1.sortedBySurnameFirstNameAndBirthdate();
        list2 = test1.sortedBySurnameFirstNameAndBirthdate();
        System.out.println();
        for (int i = 0; i < list1.size(); i++) Assert.assertEquals(list1.get(i), list2.get(i));
    }

    @Test
    public void searchPeopleByDate() {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse("1000-10-10");
        } catch (ParseException e) { System.out.println("Sth wrong with date format"); }

        List<Person> list_of_date = new ArrayList<>();
        for (Person p1 : list1)
            if (p1.get_birthdate().equals(date))
                list_of_date.add(p1);

        list2 = test1.bornOnDay(date);
        for (int i = 0; i < list2.size(); i++) {
            Assert.assertEquals(list_of_date.get(i), list2.get(i));
        }
    }

    //ASSIGNMENT 8
    private final File inputList = new File("inputList.txt");
    private final File outputList = new File("outputList.odt");

    @Test
    public void serializeAndDeserialize() throws Throwable {
        PersonDatabase database = new PersonDatabase(InputParser.parse(inputList));

        //serialization
        OutputStream output = new FileOutputStream(outputList);
        DataOutputStream outputContent = new DataOutputStream(output);
        database.serialize(outputContent);
        outputContent.close();
        System.out.println(database.toString());

        //deserialize
        InputStream input = new FileInputStream(outputList);
        DataInputStream inputContent = new DataInputStream(input);
        PersonDatabase deseialized = PersonDatabase.deserialize(inputContent);
        inputContent.close();
        System.out.println(deseialized.toString());

        Assert.assertNotNull(deseialized);
        Assert.assertEquals(database.size(), deseialized.size());
    }
}

