package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Manager extends Worker {

	// attributes
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (a list of subordinates in all hierarchy)

	private List<Employee> subordinates;
	private final ArrayList _AllSubordinates = new ArrayList();
	
	public Manager(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Object manager, LocalDate employmentDate, BigDecimal bonus ) {
		super(firstName, surname, birthDate, salary, manager, employmentDate, bonus);
	//	_subordinates = subordinates;
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public ArrayList getAllSubordinates(){return null;};
}