package payroll;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeList {
//This class holds the employeeList as an arraylist and calls method in the
//employee class and calculation classes to	process the employee records 
//Author: Maggie Ngo
	private ArrayList<Employee> _employeeList;

	public EmployeeList() {
		_employeeList = new ArrayList<Employee>();
	}
	public void createEmployeeList(String args) {
		try {
			Scanner employeeInfo = new Scanner(new FileReader(args));
			int taxID;
			double rate, ytd, hours, deductions, gross, incomeTax, nett;

			while (employeeInfo.hasNextLine()) {

				String line = employeeInfo.nextLine();
				if(!(line.startsWith("#"))) {
					//the lines that are unlikely to contain important information are
					//eliminated, leaving only the information to do with the employees

					String[] split = line.split(("\t|, "));
					//To separate all the parts of the String into the important components,
					//the tabs and the comas must be removed. The dollar signs are also removed
					//using the replace method later on

					taxID = Integer.parseInt(split[0]);

					split[4] = split[4].replace("$","");
					rate = Double.parseDouble(split[4]);

					split[5] = split[5].replace("$","");
					ytd = Double.parseDouble(split[5]);

					hours = Double.parseDouble(split[8]);

					split[9] = split[9].replace("$","");
					deductions = Double.parseDouble(split[9]);

					//This if statement decides which version of the calculation methods
					//to use to calculate the remaining values to be processed. The values
					//are then calculated before all the information is stored in the employee
					//object
					if (split[3].equals("Salaried")) {

						SalaryCalculations salary = new SalaryCalculations();
						gross = salary.grossAmount(hours, rate);
						incomeTax = salary.incomeTax(gross, rate);
						nett = salary.nettDeductions(gross, deductions, incomeTax);
						ytd = salary.ytdCalculation(gross, ytd);

					} else {

						HourlyCalculations hourly = new HourlyCalculations();
						gross = hourly.grossAmount(hours, rate);
						incomeTax = hourly.incomeTax(gross, rate);
						nett = hourly.nettDeductions(gross, deductions, incomeTax);
						ytd = hourly.ytdCalculation(gross, ytd);
					}
					//All the values calculated and found within the file are then put into
					//an employee object. This is repeated for every employee, creating a list
					//that holds all the 
					Employee employee = new Employee(nett, incomeTax, gross, taxID, split[2], split[1], split[3], rate, ytd, split[6], split[7], hours, deductions);
					_employeeList.add(employee);

				}

			}
			employeeInfo.close();
		} catch (FileNotFoundException e) {
			//in case of file not found, default catch happens
			e.printStackTrace();

		}



	}
	//This method calls a method in employee to print the required
	//Strings that have the right values for PayslipsProcessing
	public void calculatePayslipsProcessing (){
		currentDate(); //prints current time
		taxIDSort();
		//This calls the sort method to sort the list in order of
		//tax ID number

		for (int i = 0; i < _employeeList.size(); i++) {
			//goes through each employee object to print all needed values for
			//payslips
			Employee employ = _employeeList.get(i);
			employ.payslipsProcessing();

		}
	}

	//This method calls a method in the Employee class which
	//prints a string containing the information needed related
	//to Employee Processing
	public void calculateEmployeeProcessing(){
		currentDate();

		nameSort();

		for (int i= 0; i < _employeeList.size(); i++) {
			Employee employ = _employeeList.get(i);
			employ.employeesProcessing();
		}

	}

	//This method calculates the total burden (total gross) 
	//by adding the gross from each employee, one at a time
	public void calculateBurdenProcessing() {
		currentDate();
		double totalBurden = 0;

		//This prints the dates of the pay period by
		//accessing an employee records(doesn't) matter
		//which as they should all be the same
		Employee employee = _employeeList.get(0);
		employee.printDate();

		for (int i= 0; i < _employeeList.size(); i++) {
			Employee employ = _employeeList.get(i);
			totalBurden = employ.burdenProcessing(totalBurden);
		}
		String total = String.format("%.2f", totalBurden);
		System.out.println("Total Burden: $" + total);
	}

	//This calculates the total PAYE by adding the PAYE,
	//for each individual employee one at a time using a
	//for loop
	public void calculatePAYEProcessing() {
		currentDate();
		double totalPAYE = 0;

		Employee employee = _employeeList.get(0);
		employee.printDate();

		for (int i= 0; i < _employeeList.size(); i++) {
			Employee employ = _employeeList.get(i);
			totalPAYE = employ.pAYEProcessing(totalPAYE);
		}
		String total = String.format("%.2f", totalPAYE);
		System.out.println("Total PAYE: $" + total);
	}
	
	//This prints out the current date, put in this class as
	//it is used by all processing methods in this class
	public void currentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		System.out.println(dateFormat.format(currentDate));
	}
	
	//This uses the sort method in collections to sort the list by last
	//name
	public void nameSort() {
		Collections.sort(_employeeList, new Employee.EmployeeSortName());
	}

	//This uses the sort method in collections to sort the list by taxID number
	public void taxIDSort() {
		Collections.sort(_employeeList, new Employee.EmployeeSorttaxID());
	}
}
