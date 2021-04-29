package com.company;

import javax.jws.Oneway;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class PersonDatabase {

	private static final Comparator<Person> SortByFirstNameComparator = new FirstNameComparator();
	private static final Comparator<Person> SortByBirthDateComparator = new BirthdateComparator();
	private static final Comparator<Person> SortBySurnameFirstNameBirthDateComparator = Comparator.naturalOrder();

	private final List<Person> SurnameFirstNameBirthDate;
	private final List<Person> BirthDate;
	private final List<Person> FirstName;


	private final Map<Date, List<Person>> SearchForBirthDate;

	public PersonDatabase(File file) throws IOException {
		this(InputParser.parse(file));
	}

	public PersonDatabase(List<Person> list){
		SurnameFirstNameBirthDate = list;
		SurnameFirstNameBirthDate.sort(SortBySurnameFirstNameBirthDateComparator);

		FirstName = new ArrayList<>(list);
		FirstName.sort(SortByFirstNameComparator);

		BirthDate = new ArrayList<>(list);
		BirthDate.sort(SortByBirthDateComparator);

		SearchForBirthDate = list.stream().collect(Collectors.groupingBy(Person::get_birthdate));
	}

	public List<Person> sortedByFirstName() { return FirstName; }
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() { return SurnameFirstNameBirthDate; }
	
	public List<Person> sortedByBirthdate() { return BirthDate; }
	
	public List<Person> bornOnDay(Date date) {
	//	List<Person> PersonListByDate = new ArrayList<Person>();
			if (SearchForBirthDate != null){
				if (SearchForBirthDate.containsKey(date)){
				//	System.out.println(SearchForBirthDate.get(date));
					return new ArrayList<Person>(SearchForBirthDate.get(date));
				}
				else System.out.println("There is no people born at this date!");
			}
			else System.out.println("The list is empty!");
			//System.out.println(PersonListByDate);
	//	if(SearchForBirthDate.containsKey(date)) PersonListByDate.add(SearchForBirthDate.get(Person));
	//	return PersonListByDate;
		return null;
	}

}