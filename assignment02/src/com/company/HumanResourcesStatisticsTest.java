package com.company;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class HumanResourcesStatisticsTest {
	
	// Create a HR structure which resembles the below one:
	//
	// Director (an instance of Manager class (Director does not have a manager)
	//   |- Manager01
	//        |- Worker
	//        |- Worker
	//        |- Trainee
	//        |- ...
	//   |- Manager02
	//        |- ...
	//   |- ...
	//   |- Worker
	//   |- Worker
	//   |- Trainee

	Manager director = new Manager("Mr", "Boss", LocalDate.of(1969, Month.JUNE, 2), BigDecimal.valueOf(10000), null, LocalDate.of(1990, Month.APRIL, 1), BigDecimal.valueOf(0));
	Manager m1 = new Manager("Frank", "Smallboss", LocalDate.of(1970, Month.JUNE, 2), BigDecimal.valueOf(6000), director, LocalDate.of(1995, Month.APRIL, 1), BigDecimal.valueOf(500));
	Worker w1 = new Worker("Kuba", "WorkerOne", LocalDate.of(1985, Month.JUNE, 2), BigDecimal.valueOf(3000), m1, LocalDate.of(2005, Month.APRIL, 1), BigDecimal.valueOf(400) );
	Worker w2 = new Worker("Jacek", "WorkerTwo", LocalDate.of(1990, Month.JUNE, 2), BigDecimal.valueOf(3000), m1, LocalDate.of(2010, Month.APRIL, 1), BigDecimal.valueOf(300) );
	Trainee t1 = new Trainee("Fokuś", "TreineeOne", LocalDate.of(1997, Month.JUNE, 2), BigDecimal.valueOf(1500), m1, LocalDate.of(2019, Month.APRIL, 1), 90);

	Manager m2 = new Manager("Something", "Smallboss2", LocalDate.of(1980, Month.JUNE, 2), BigDecimal.valueOf(6000), director, LocalDate.of(2000, Month.APRIL, 1), BigDecimal.valueOf(500));
	Worker w3 = new Worker("Patryk", "WorkerThree", LocalDate.of(1995, Month.JUNE, 2), BigDecimal.valueOf(3000), m2, LocalDate.of(2009, Month.APRIL, 1), BigDecimal.valueOf(350) );
	Worker w4 = new Worker("Michał", "WorkerFour", LocalDate.of(1996, Month.JUNE, 2), BigDecimal.valueOf(3000), m2, LocalDate.of(2007, Month.APRIL, 1), BigDecimal.valueOf(450) );
	Trainee t2 = new Trainee("Karol", "TreineeTwo", LocalDate.of(1999, Month.JUNE, 2), BigDecimal.valueOf(1500), m2, LocalDate.of(2019, Month.APRIL, 1), 60);

	private ArrayList<Employee> _allEmployees = new ArrayList<Employee>();

; // all employees ---  i.e. Workers, Trainees and their Managers and top Director (also an instance of Manager class)

	@Test
	public void payroll() {
		HumanResourcesStatistics.payroll(_allEmployees);
	}

	public void subordinatesPayroll() {
		HumanResourcesStatistics.subordinatesPayroll(null);
	}

	public void bonusTotal() {
//		BigDecimal total = HumanResourcesStatistics.bonusTotal(_allEmployees);
//		Assert.assertEquals(new BigDecimal("100"), total);
	}

	/// ...
	// rest of the methods specified in the assignment description
}