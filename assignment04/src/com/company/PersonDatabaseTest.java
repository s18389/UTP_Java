package com.company;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PersonDatabaseTest {


    public static void main(String[] args) {
        File listFile = new File("list.txt");
        PersonDatabase test1 = new PersonDatabase(InputParser.parse(listFile));

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date datetest = format.parse("1999-12-12");
            Person p1 = new Person("K", "M", datetest);
            Person p2 = new Person("K", "M", datetest);
            if (p1.equals(p2) == true) System.out.println(p1 + " and " + p2 + "ARE THE SAME PERSON");
            else System.out.println(p1 + " and " + p2 + "ARE DIFFERENT");

            if (p1 == p2) System.out.println(p1 + " and " + p2 + "ARE THE SAME PERSON");
            else System.out.println(p1 + " and " + p2 + "ARE DIFFERENT");

        }catch (ParseException e){System.out.println("Sth wrong with date format");}

        System.out.println(test1.sortedByFirstName());
        System.out.println(test1.sortedByBirthdate());
        System.out.println(test1.sortedBySurnameFirstNameAndBirthdate());

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date dateToSearchPeople = format.parse("1000-10-10");
             System.out.println("=====================");
             System.out.println(test1.bornOnDay(dateToSearchPeople));
        }
        catch (ParseException e) {System.out.println("Sth wrong with date format");}

    }


}