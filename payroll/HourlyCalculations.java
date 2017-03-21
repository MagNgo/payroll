package payroll;
//This class calculates the gross, incomeTax(PAYE), nett and ytd for
//hourly employees. 
//Author: Maggie Ngo

//This method calculates the gross for hourly employees in which
//the gross is calculated using hours
public class HourlyCalculations extends Calculations {
	public double grossAmount(double hours, double rate) {
		double gross;
		if (hours <= 40) {
			gross = hours * rate;
		} else if (hours > 40 && hours <= 43) {
			gross = 40 * rate + (hours - 40) * 1.5 * rate;
		} else {
			gross = 40 * rate + 3 * 1.5 *rate + (hours - 43) * rate * 2;
		}
		gross = Math.round(gross * 100);
		gross = gross / 100;
		return gross;
	}
	
	//Income tax for hourly employees are calculated based on their 
	//gross for the week, times by 52
	public double incomeTax(double gross, double rate) {
		double income = gross * 50;
		return super.incomeTax(gross, income);
	}
	

	public double nettDeductions(double gross, double deductions, double incomeTax) {
		return super.nettDeductions(gross, deductions, incomeTax);
	}
	
	public double ytdCalculation(double gross, double ytd) {
		return super.ytdCalculation(gross, ytd);
	}
	
}
