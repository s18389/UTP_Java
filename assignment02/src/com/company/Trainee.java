package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trainee extends Employee {

	// attributes:
	// * practice start date
	// * practice length (in days)
	private final LocalDate _practiceStartDate;
	private final int _practiceLength;
	public Trainee(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Object manager, LocalDate practiceStartDate, int practiceLength) {
		super(firstName, surname, birthDate, salary, manager);
		_practiceStartDate = practiceStartDate;
		_practiceLength = practiceLength;
	}

	public LocalDate get_practiceStartDate() { return _practiceStartDate; }
	public int get_practiceLength() { return _practiceLength; }
}