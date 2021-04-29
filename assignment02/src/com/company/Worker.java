package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

	// attributes
	// * employment date
	// * bonus
	private final LocalDate _employmentDate;
	private final BigDecimal _bonus;

	public Worker(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Object manager, LocalDate employmentDate, BigDecimal bonus) {
		super(firstName, surname, birthDate, salary, manager);
		_employmentDate = employmentDate;
		_bonus = bonus;
	}

	public LocalDate get_emplymentDate() { return _employmentDate; }
	public BigDecimal get_bonus() { return _bonus; }
}