package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person {

	//
	// attributes:
	// * salary (use BigDecimal type for representing currency values)
	// * manager (empty if at top of hierarchy)

	private  BigDecimal _salary;
	private final Object _manager;

	protected Employee(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Object manager) {
		super(firstName, surname, birthDate);
		_salary = salary;
		_manager = manager;
	}

	public void set_salary(BigDecimal salary){this._salary = salary;}

	public BigDecimal getSalary(){return _salary;}
	public Object get_manager() { return _manager; }


}