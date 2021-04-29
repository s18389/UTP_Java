package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class Main {

    public static void main(String[] args) {

        Manager director = new Manager("Mr", "Boss", LocalDate.of(1969, Month.JUNE, 2), BigDecimal.valueOf(10000), null, LocalDate.of(1990, Month.APRIL, 1), BigDecimal.valueOf(0));
        Manager m1 = new Manager("Frank", "Smallboss", LocalDate.of(1970, Month.JUNE, 2), BigDecimal.valueOf(6000), director, LocalDate.of(1995, Month.APRIL, 1), BigDecimal.valueOf(500));
        Worker w1 = new Worker("Adam", "AWorkerOne", LocalDate.of(1985, Month.JUNE, 2), BigDecimal.valueOf(2000), m1, LocalDate.of(2005, Month.APRIL, 1), BigDecimal.valueOf(400) );
        Worker w2 = new Worker("Jacek", "WorkerTwo", LocalDate.of(1990, Month.JUNE, 2), BigDecimal.valueOf(3000), m1, LocalDate.of(2010, Month.APRIL, 1), BigDecimal.valueOf(300) );
        Trainee t1 = new Trainee("Fokuś", "TreineeOne", LocalDate.of(1997, Month.JUNE, 2), BigDecimal.valueOf(1500), m1, LocalDate.of(2019, Month.APRIL, 1), 90);

        Manager m2 = new Manager("Something", "Smallboss2", LocalDate.of(1980, Month.JUNE, 2), BigDecimal.valueOf(6000), director, LocalDate.of(2000, Month.APRIL, 1), BigDecimal.valueOf(500));
        Worker w3 = new Worker("Patryk", "WorkerThree", LocalDate.of(1995, Month.JUNE, 2), BigDecimal.valueOf(3000), m2, LocalDate.of(2009, Month.APRIL, 1), BigDecimal.valueOf(350) );
        Worker w4 = new Worker("Michał", "WorkerFour", LocalDate.of(1996, Month.JUNE, 2), BigDecimal.valueOf(3000), m2, LocalDate.of(2007, Month.APRIL, 1), BigDecimal.valueOf(450) );
        Trainee t2 = new Trainee("Karol", "TreineeTwo", LocalDate.of(1999, Month.JUNE, 2), BigDecimal.valueOf(1500), m2, LocalDate.of(2019, Month.APRIL, 1), 60);
        List<Employee> director_sub = new ArrayList<>();
        director_sub.add(m1);
        director_sub.add(m2);
        director.setSubordinates(director_sub);
        List<Employee> m1_sub= new ArrayList<>();
        m1_sub.add(w1);
        m1_sub.add(w2);
        m1_sub.add(t1);
        m1.setSubordinates(m1_sub);
        List<Employee> m2_sub= new ArrayList<>();
        m2_sub.add(w3);
        m2_sub.add(w4);
        m2_sub.add(t2);
        m2.setSubordinates(m2_sub);

        ArrayList<Employee> _allEmployees = new ArrayList<Employee>();
        _allEmployees.add(director);
        _allEmployees.add(m1);
        _allEmployees.add(w1);
        _allEmployees.add(w2);
        _allEmployees.add(t1);
        _allEmployees.add(m2);
        _allEmployees.add(w3);
        _allEmployees.add(w4);
        _allEmployees.add(t1);

        ArrayList<Worker> _allWorkers = new ArrayList<Worker>();
        _allWorkers.add(director);
        _allWorkers.add(m1);
        _allWorkers.add(w1);
        _allWorkers.add(w2);
        _allWorkers.add(m2);
        _allWorkers.add(w3);
        _allWorkers.add(w4);

        ArrayList<Trainee> _Trainee = new ArrayList<Trainee>();
        _Trainee.add(t1);
        _Trainee.add(t2);

        //Assigment 2
   //   HumanResourcesStatistics.payroll(_allEmployees);
     // HumanResourcesStatistics.subordinatesPayroll(m1);
    //  HumanResourcesStatistics.bonusTotal(_allWorkers);
      //HumanResourcesStatistics.LongestSeniority(_allWorkers);
   //   HumanResourcesStatistics.BigestSalaryNoBonus(_allWorkers);
     // HumanResourcesStatistics.BigestSalaryWithBonus(_allWorkers);
     // HumanResourcesStatistics.SubordinatiesWithA(m1);
        //  HumanResourcesStatistics.SalaryFromEmp(_allEmployees, 5000);

        //Assigment 3
      //  HumanResourcesStatistics.olderThenAndEarnMore(_allEmployees, w2);
        ArrayList<Trainee> _Trainee2 = (ArrayList<Trainee>) HumanResourcesStatistics.practiceLengthLongerThan(_Trainee, 70);
        HumanResourcesStatistics.practiceLengthLongerThan(_Trainee, 70);

    }


}
