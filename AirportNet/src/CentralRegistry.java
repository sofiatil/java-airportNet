import java.util.ArrayList;

public class CentralRegistry {
	private static ArrayList<Airport> airportNameList= new ArrayList<>();
	private static ArrayList<Flight> flightsList = new ArrayList<>();
	
	
	//retutns the Airport that exists in a city or null if there in no airport in this city
	public static Airport getAirport(String cityName) {
		for(Airport a: airportNameList) {
			if(a.getCity().equals(cityName)) {
				return a;
			}
		}
		return null;		
	}
	
	//returns the details for every flight that connects the airports given (a,b)
	public static String getDirectFlightsDetails (Airport a, Airport b){
		int count=0;
		
		//using a string builder to add a new flight every time we find one 
		StringBuilder returnStr = new StringBuilder("DIRECT FLIGHTS DETAILS"+'\n');
		for(Flight f: flightsList) {
			if(f.getAirportA().equals(a) && f.getAirportB().equals(b)){
				count++;
				returnStr.append("["+count+"]" + f).append('\n');				
			}
			else if(f.getAirportA().equals(b) && f.getAirportB().equals(a)){
				count++;
				returnStr.append("["+count+"]" + f).append('\n');	
			}
		}
		return returnStr.toString();
	}
	
	//returns the flight details of the flights that connect the airports given indirectly 
	public static String getIndirectFlightsDetails( Airport a, Airport b) {
		ArrayList<Airport> connectedThroughList;
		connectedThroughList = a.getCommonConnections(b);
		int count=0;
		//using a string builder to add a new flight every time we find one 
		StringBuilder returnStr = new StringBuilder("INDIRECT FLIGHTS through..."+'\n');
		for(Airport connected: connectedThroughList) {
			count ++;
			returnStr.append("["+count+"] "+connected.getCity()+", "+connected.getCode()+" airport").append('\n');
		}
		return returnStr.toString();
	}
	
	
	//καταχώρησης ενός αεροδρομίου.
	public static void addAirport(Airport aAirport) {
		airportNameList.add(aAirport);
	}


	//καταχώρησης ενός δρομολογίου
	public static void addFlight(Flight aFlight) {
		flightsList.add(aFlight);
	}

	
	
	public static ArrayList<Airport> getAirportNameList() {
		return airportNameList;
	}



	public static ArrayList<Flight> getFlightsList() {
		return flightsList;
	}


	//επιστρέφει το δρομολόγιο (πτήση) με τη μεγαλύτερη διάρκεια.
	public static Flight getLongestFlight() {
		
		Flight longestFlight = null;
		int longestDuration=0;
		for(Flight a: flightsList) {
			if (a.getDuration() > longestDuration) {
				longestFlight = a;
				longestDuration = a.getDuration();
			}
		}
		return longestFlight;
	}
	
	//returns the airport with the most connections
	public static Airport getLargestHub() {
		Airport mostConnections = null;
		int numConnections=0;
		for(Airport a: airportNameList) {
			if(a.getAirCompanyList().size() > numConnections) {
				mostConnections = a;
				numConnections = a.getAirCompanyList().size();
			}
		}
		return mostConnections;
	}
	
}
