package payroll;

//This class contains methods that caculate gross, incomeTax(PAYE), nett and ytd
//for Salary employees
//Author: Maggie Ngo
public class SalaryCalculations extends Calculations {
	
	public double grossAmount(double hours, double rate) {
		//for a salary employee, the rate must be divided by two
		//to find gross
		double gross;
		gross = rate/ 52;
		gross = Math.round(gross * 100);
		gross = gross / 100;
		return gross;
	}
	
	public double incomeTax(double gross, double rate) {
		return super.incomeTax(gross, rate);
	}

	
	public double nettDeductions(double gross, double deductions, double incomeTax) {
		return super.nettDeductions(gross, deductions, incomeTax);
	}
	

	public double ytdCalculation(double gross, double ytd) {
		return super.ytdCalculation(gross, ytd);
	}
	
}
