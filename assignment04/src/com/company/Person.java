package com.company;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Person implements Comparable<Person> {

	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	static AtomicInteger nextId = new AtomicInteger();
	private int _id;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
		_id = nextId.incrementAndGet();

	}

	public Date get_birthdate() { return _birthdate; }
	public String get_firstName() { return _firstName; }
	public String get_surname() { return _surname; }

	@Override
	public int compareTo(Person otherPerson) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return  _firstName + " " + _surname + " " + _birthdate + "\n";
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		else if(o== null || getClass()!=o.getClass()) return false;
		Person p1 = (Person) o;
		return 	_id == p1._id &&
				Objects.equals(_firstName, p1._firstName) &&
				Objects.equals(_surname, p1._surname) &&
				Objects.equals(_birthdate, p1._birthdate);
	}
}