package payroll;
//This interface contains all the calculations that are used to calculate gross, incomeTax, nett and ytd
//The calculations may differ slightly between hourly and salary employees hence this interface is implemented
//by both SalaryCalculations and HourlyCalculations
//Author: Maggie Ngo
public abstract class Calculations {
	public abstract double grossAmount(double hours, double rate);
	
	public double incomeTax(double gross, double rate) {
		//for a salary employee, income tax depends on the rate
		//which holds their yearly income, for an hourly employee
		//it is their gross * 52

		
		//the if statements decide how the incomeTax is calculated
		//by taking the tax from the right amounts depending on which
		//tax bracket the employee is in
		double incomeTax;
		if (rate <= 14000) {
		incomeTax = rate	* .105;
		} else if (rate > 14000 && rate <= 48000) {
			incomeTax = 14000 * .105 + (rate - 14000) * .175;
		} else if (rate > 48000 && rate <= 70000) {
			incomeTax= 14000 * .105 + 34000 * .175 + (rate - 48000) * .3;
		} else {
			incomeTax= 14000 * .105 + 34000 * .175 + 22000 * .3 + (rate - 70000) * .33;
		}
		incomeTax = incomeTax / 52;
		incomeTax = Math.round(incomeTax * 100);
		incomeTax = incomeTax / 100;
		return incomeTax;
		
	}
	
	//This calculates the nett income the employee has after all deductions including tax
	//union membership and Superannuation
	public double nettDeductions(double gross, double deductions, double incomeTax) {
		double nett;
		nett = gross - deductions - incomeTax;
		return nett;
	}
	
	//This calculates the ytd which is the income to date for each employee
	public double ytdCalculation(double gross, double ytd) {
		ytd = ytd + gross;
		return ytd;
	}
	
	
}
