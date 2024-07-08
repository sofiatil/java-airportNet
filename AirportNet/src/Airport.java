import java.util.ArrayList;
import java.util.HashSet;

public class Airport {
	
	private String name;
	private String code;
	private String city;
	private String country;
	private ArrayList<String> airCompanyList = new ArrayList<>();
	

	public Airport(String name, String code, String city, String country) {
		this.name = name;
		this.code = code;
		this.city = city;
		this.country = country;
	}

	//δέχεται ως παράμετρο ένα άλλο αεροδρόμιο και επιστρέφει 
	//true ή false ανάλογα με το αν είναι απευθείας συνδεδεμένα
	public boolean isDirectlyConnectedTo(Airport a4) {

		for (String a : airCompanyList) {
			if(a.equals(a4.getName())) {
				return true;
			}
		}
		return false;
	}

	//δέχεται ως παράμετρο ένα άλλο αεροδρόμιο και επιστρέφει true ή false ανάλογα 
	//με το αν είναι συνδεδεμένα μέσω ενός ενδιάμεσου αεροδρομίου.
	public boolean isInDirectlyConnectedTo(Airport anAirport) {
				
		for (String a : airCompanyList) {
			for (String b : anAirport.airCompanyList) {
				if(a.equals(b)) {
					return true;
				}
			}
		}
		
		return false;
	}

	public String getName() {
		return this.name;
	}

	//δέχεται ως παράμετρο ένα άλλο αεροδρόμιο και επιστρέφει μια λίστα με τα 
	//κοινά αεροδρόμια προς τα οποία υπάρχει απευθείας σύνδεση.
	public ArrayList<Airport> getCommonConnections(Airport anAirport) {
		
		ArrayList<Airport> a1 = new ArrayList<>();
		ArrayList<Airport> a2 = new ArrayList<>();
		//gathering the connections of each flight
		for(Flight f: CentralRegistry.getFlightsList()) {
			if(f.getAirportA().equals(this)&& f.getAirportB()!=anAirport) {
				a1.add(f.getAirportB());
			}else if (f.getAirportB().equals(this)&& f.getAirportA()!=anAirport) {
				a1.add(f.getAirportA());
			}else if (f.getAirportA().equals(anAirport)&& f.getAirportB()!=this) {
				a2.add(f.getAirportB());
			}else if (f.getAirportB().equals(anAirport)&& f.getAirportA()!=this) {
				a2.add(f.getAirportA());
			}			
		}
		HashSet<Airport> uniqueSet = new HashSet<>();
		for(Airport b: a1) {
			for(Airport c : a2) {
				if(b.equals(c)) {
					uniqueSet.add(c);
				}
			}
		}		
		ArrayList <Airport> resultList = new ArrayList<>(uniqueSet);
		return resultList;	
	}

	public void printCompanies() {
		for(String a : airCompanyList) {
			System.out.println(a);
		}
		
	}

	public ArrayList<String> getAirCompanyList() {
		return airCompanyList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String countrty) {
		this.country = countrty;
	}
	
	//a setter to add companies in the list of the airport
	public void setAirCompanyInList(String aCompany) {
		airCompanyList.add(aCompany);
	}

	public String getCity() {
		return city;
	}

	public String getCode() {
		return code;
	}
	
	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "Airport [name=" + name + ", code=" + code + ", city=" + city + ", country=" + country + "]";
	}
	
	
	
	
	
	
	
	
	

	
			
		
	
	

}
