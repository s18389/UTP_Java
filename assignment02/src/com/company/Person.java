package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public abstract class Person {

	// To implement an attribute means that you provide a backing field and
	// getter or optionally setter for read-write properties/attributes
	// 
	// NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
	// THOSE SHOULD BE COMPUTED ON-LINE
	//
	// attributes:
	// * first name (read-only)
	// * surname (read-only)
	// * birth date (read-only) --- date MUST BE represented by an instance of
	// type designed for date representation (either Date or LocalDate)
	//
	// * age (derived --- computed based on birth date) --- implemented as a
	// getter calculating the difference between the current date and birth date

	private final String _firstName; // backing field
	private final String _surname;
	private final LocalDate _birthDate;

	protected Person(String firstName, String surname, LocalDate birthDate) {
		_firstName = firstName;
		_surname = surname;
		_birthDate = birthDate;
	}

	public String getFirstName() { // getter
		return _firstName;
	}
	public String getSurname() { return _surname; }
	public LocalDate getBirthDate() { return _birthDate; }
	public Integer getAge() {
		LocalDate now = LocalDate.now();
		Period difference = Period.between(getBirthDate(), now);
		return difference.getYears();
	}

//	@Override
//	public String toString() {
//		return _firstName + " " + _surname + " " + _birthDate.toString();
//	}
}