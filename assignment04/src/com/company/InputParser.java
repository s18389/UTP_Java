package com.company;

import com.sun.deploy.jardiff.Patcher;
import jdk.nashorn.internal.ir.Assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputParser {
	private static final DateFormat DateFormat = new SimpleDateFormat("yyyy-mm-dd");
	private static final String FirstNameGroup = "firstName";
	private static final String SurnameGroup = "surname";
	private static final String BirthDateGroup = "birthDate";

	private static final String PatternName = "(?:[A-Ż]([a-z])+)";
	private static final String PatternFirstName = "(?<" + FirstNameGroup + ">" + PatternName + ")";
	private static final String PatternSurname = "(?<" + SurnameGroup + ">" + PatternName + ")";

	private static final String separator = "-";
	private static final String PatternBirthDate = "(?<" + BirthDateGroup + ">" + "(?!0000)[0-9]{4}" + separator + "(0?[1-9]|1[012])\\" + separator + "(0?[1-9]|[12][0-9]|3[01])" + ")";
	private static final String PatternSpace = "(?:[ \0]+)";
	private static final String PatternAllLine = PatternFirstName + PatternSpace + PatternSurname + PatternSpace + PatternBirthDate;

	private static final Pattern LineRegex = Pattern.compile(PatternAllLine);




	private static Person parse(String line){
		Matcher is_good = LineRegex.matcher(line);
		if (is_good.matches() == false) return null;
		String name = is_good.group(FirstNameGroup);
		String surname = is_good.group(SurnameGroup);
		Date birthDate = birthDate(is_good);
		Person resultPerson = new Person(name, surname, birthDate);
	//	System.out.println(resultPerson);
		return resultPerson;
	}

	private static Date birthDate(Matcher is_good){
		try {
			String in = is_good.group(BirthDateGroup);
			return DateFormat.parse(in);
		} catch (ParseException e){
			System.out.print("Sth wrong with date format");
			return null;
		}
	}

	public static List<Person> parse(File file) {
		BufferedReader reader;
		List<Person> finalList= new ArrayList<Person>();
		try{
			reader = new BufferedReader(new FileReader(file.getName()));
			String line = reader.readLine();
			while (line != null) {
				if(parse(line) != null) finalList.add(parse(line));
				//	if (line.matches(PatternAllLine)) System.out.println(line);
				//	System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Problem with file path!!");
			e.printStackTrace();}
		return finalList;
	}
}