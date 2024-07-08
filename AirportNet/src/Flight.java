
public class Flight {
	
	private Airport a1;
	private Airport a2;
	private int duration;
	private String airCompany;
	
	
	public Flight(Airport a1, Airport a2, int duration, String airCompany) {
		this.a1 = a1;
		this.a2 = a2;
		this.duration = duration;
		this.airCompany = airCompany;
		
		//inside the constructor we are allocating the company to the airports
		this.a1.setAirCompanyInList(this.airCompany);
		this.a2.setAirCompanyInList(this.airCompany);
	}
	

	public Airport getAirportA() {
		return a1;
	}


	public Airport getAirportB() {
		return a2;
	}


	public int getDuration() {
		return duration;
	}


	public String getAirCompany() {
		return airCompany;
	}


	@Override
	public String toString() {
		return "Flight operated by " + airCompany + ", duration " + duration + " minutes";
	}
	
	


}
