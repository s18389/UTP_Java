package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public final class HumanResourcesStatistics {


	public static List<BigDecimal> payroll(List<Employee> employees) {
		BigDecimal sum = BigDecimal.valueOf(0);
		List<BigDecimal> payrollenties = new ArrayList<>();
		for(Employee temp : employees) {
			System.out.println(temp.getFirstName() + " " + temp.getSurname() + " " + temp.getSalary());
			sum = sum.add(temp.getSalary());
			payrollenties.add(temp.getSalary());
			//    System.out.println(temp.getSalary());
		}
		System.out.println("Payroll of all: " + sum);
		System.out.println(payrollenties);
		return payrollenties;
	}

	public static List<BigDecimal> subordinatesPayroll(Manager manager) {
		List<BigDecimal> payrollenties_of_manager = new ArrayList<>();
		for(Employee temp : manager.getSubordinates()) {
			System.out.println(temp.getFirstName() + " " + temp.getSurname() + " " + temp.getSalary());
			payrollenties_of_manager.add(temp.getSalary());
		}
		System.out.println(payrollenties_of_manager);
		return payrollenties_of_manager;
	}

	public static BigDecimal bonusTotal(List<Worker> employees) {
		BigDecimal sum = BigDecimal.valueOf(0);
		for (Worker temp : employees) {
			System.out.println(temp.getFirstName() + " " + temp.getSurname() + " " + temp.get_bonus());
			sum = sum.add(temp.get_bonus());
		}
		System.out.println("Total: " + sum);
		return sum;
	}

	public static Worker LongestSeniority(List<Worker> list){
		int thelongest = 0;
		int days = 0;
		Worker result_worker = null;
		LocalDate now = LocalDate.now();
		for (Worker temp : list) {
			Period difference = Period.between(temp.get_emplymentDate(), now);
			days = difference.getDays();
			if(days > thelongest) {
				thelongest = days;
				result_worker = temp;
			}
			System.out.println(temp.getFirstName() + " " + temp.getSurname() + " " + temp.get_emplymentDate());
		}
		System.out.println("Worker: " + result_worker.getFirstName() + " " + result_worker.getSurname() + " has the longest seniority");
		return result_worker;
	}

	public static double BigestSalaryNoBonus(List<Worker> list){
		double thebiggest = 0;
		for (Worker temp : list) {
			if (temp.getSalary().doubleValue() - temp.get_bonus().doubleValue() > thebiggest) thebiggest = temp.getSalary().doubleValue() - temp.get_bonus().doubleValue();
			System.out.println(temp.getFirstName() + " " + temp.getSurname() + " " + temp.get_bonus() + " " + temp.getSalary());
		}
		System.out.println("The biggest salary without bonus: " + thebiggest);
		return thebiggest;
	}

	public static double BigestSalaryWithBonus(List<Worker> list){
		double thebiggest = 0;
		for (Worker temp : list) {
			if (temp.getSalary().doubleValue() + temp.get_bonus().doubleValue() > thebiggest) thebiggest = temp.getSalary().doubleValue() + temp.get_bonus().doubleValue();
			System.out.println(temp.getFirstName() + " " + temp.getSurname() + " " + temp.get_bonus() + " " + temp.getSalary());
		}
		System.out.println("The biggest salary with bonus: " + thebiggest);
		return thebiggest;
	}

	public static List<Employee> SubordinatiesWithA(Manager manager) {
		List<Employee> subordinatiesWithAList = new ArrayList<>();
		for(Employee temp : manager.getSubordinates()) {
			if (temp.getSurname().startsWith("A")) {
				subordinatiesWithAList.add(temp);
				System.out.println(temp.getFirstName() + " " + temp.getSurname());
			}
		}
		return subordinatiesWithAList;
	}

	public static List<Employee> SalaryFromEmp(List<Employee> list, double from) {
		List<Employee> list_of_sal = new ArrayList<>();
		for (Employee temp : list) {
			if (temp.getSalary().doubleValue() > from) {
				list_of_sal.add(temp);
				System.out.println(temp.getFirstName() + " " + temp.getSurname() + " " + temp.getSalary());
			}
		}
		return list_of_sal;
	}

	// (assignment 03)
	// methods:
	//
	// * search for Employees older than given employee and earning less than him
	//   wyszukaj osoby zatrudnione (Employee), kt??re s?? starsze od podanej innej zatrudnionej osoby oraz zarabiaj?? mniej od niej
	public static List<Employee> olderThenAndEarnMore(List<Employee> allEmployees, Employee employee) {
		List<Employee> list_of_older_and_better = new ArrayList<>();
		for (Employee temp : allEmployees) {
		//	System.out.println(temp.getAge());
			if ((temp.getAge() > employee.getAge()) && (temp.getSalary().doubleValue() < employee.getSalary().doubleValue())) {
				list_of_older_and_better.add(temp);
				System.out.println(temp.getFirstName() + " " + temp.getSurname() + " " + temp.getSalary());
			}
		}
		return list_of_older_and_better;
	}

	//
	// * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
	//   wyszukaj praktykant??w (Trainee), kt??rych praktyka jest d??u??sza od podanej liczby dni,
	//   a nast??pnie podnie?? ich uposa??enie o 5%
	public static List<Trainee> practiceLengthLongerThan(List<Trainee> allEmployees, int daysCount) {
		List<Trainee> list_practicale_length_longer_then = new ArrayList<>();
		for (Trainee temp : allEmployees) {
			if (temp.get_practiceLength()>daysCount) {
				list_practicale_length_longer_then.add(temp);
				System.out.println(temp.getFirstName() + " " + temp.getSurname() + " " + temp.get_practiceLength());
			}
		}
		return list_practicale_length_longer_then;
	}

	//
	// * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
	//   wyszukaj pracownik??w o sta??u d??u??szym ni?? podana liczba miesi??cy,
	//   a nast??pnie przyznaj im premi?? w wysoko??ci 300 je??li ich premia jest ni??sza
	public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
		return null;
	}

	//
	// * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
	//   wyszukaj pracownik??w o sta??u pomi??dzy 1 a 3 lata i przyznaj im podwy??k?? w wysoko??ci 10%
	public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
		return null;
	}

	//
	// * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
	//   wyszukaj pracownik??w o sta??u d??u??szym ni?? sta?? podanego pracownika i kt??rzy zarabiaj?? mniej od niego,
	//   nast??pnie zr??wnaj ich wynagrodzenie z wynagrodzeniem danego pracownika
	public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {
		return null;
	}

	//
	// * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
	//   wyszukaj pracownik??w o sta??u pomi??dzy 2 i 4 lata i starszych ni?? podana liczba lat
	public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
		return null;
	}
}