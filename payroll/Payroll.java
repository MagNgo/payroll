package payroll;

import java.util.Scanner;

public class Payroll {
//This class contains my main method which calls all the methods needed
//to do the processing
//Author: Maggie Ngo
	public static void main (String[] args) {

		EmployeeList employeeList = new EmployeeList();
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Enter a process:");
		String process = scanner.nextLine(); 
		//This method contains the scanner and the fileReader. It breaks down the info
		//into the respective categories of the employee object.
		employeeList.createEmployeeList(args[0]);
		
		if (process=="Payslips") {
			employeeList.calculatePayslipsProcessing();
		} else if (process=="Employees") {
			employeeList.calculateEmployeeProcessing();
		} else if (process=="Burden") {
			employeeList.calculateBurdenProcessing();
		} else if (process=="PAYE") {
			employeeList.calculatePAYEProcessing();
		}

	}


}
