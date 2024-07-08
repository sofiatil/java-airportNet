import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;


public class AirportPage extends JFrame{
	private JSplitPane splitPanel1,splitPanel2, splitPanel3;
	private JPanel mainPanel, airportPanel1,airportPanel2,airportPanel3, airportPanel4;
	private JButton findFlightButton, backButton;
	private JTextField aName, aCode, aCity, aCountry, findFlightText;
	private JList<String> airComp;
	private JTextArea directFlights, indirectFlights;
	private ArrayList<Airport> aList;
	private ArrayList<Flight> fList;
	private Airport currAirport;
	
	public AirportPage(ArrayList<Airport> airportsList, ArrayList<Flight> flightsList, Airport currentAirport) {
		//creating the main panel 
		mainPanel = new JPanel();
		
		//we are using split panel to have better control of the location of the items
		splitPanel1 = new JSplitPane();
		splitPanel2 = new JSplitPane();
		splitPanel3 = new JSplitPane();
		//saving the values we get from the constructor 
		aList = airportsList;
		fList=flightsList;
		currAirport = currentAirport;
		
		//PANEL 1 - creation and setting of the items that will be used
		airportPanel1 = new JPanel();
		aName = new JTextField(currentAirport.getName());
		aName.setEditable(false);
		aName.setPreferredSize(new Dimension (110,25));
		aName.setBackground(Color.WHITE);
		aCode = new JTextField(currentAirport.getCode());
		aCode.setEditable(false);
		aCode.setPreferredSize(new Dimension (110,25));
		aCode.setBackground(Color.WHITE);
		aCity = new JTextField(currentAirport.getCity());
		aCity.setEditable(false);
		aCity.setPreferredSize(new Dimension (110,25));
		aCity.setBackground(Color.WHITE);
		aCountry = new JTextField(currentAirport.getCountry());
		aCountry.setEditable(false);
		aCountry.setPreferredSize(new Dimension (110,25));
		aCountry.setBackground(Color.WHITE);
		airComp = new JList<String>();
		
		//Sort the air-company list
		ArrayList<String> airCompList = new ArrayList<>();
		airCompList=currentAirport.getAirCompanyList();
		Collections.sort(airCompList);
		//List model creation
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String a: currentAirport.getAirCompanyList()) {
			if(!listModel.contains(a)) {
				listModel.addElement(a);
			}
		}
		airComp.setModel(listModel);
		
		//Adding the items in panel1
		airportPanel1.add(aName);
		airportPanel1.add(aCode);
		airportPanel1.add(aCity);
		airportPanel1.add(aCountry);
		airportPanel1.add(airComp);
		
		//PANEL 2 - creation and setting of the items that will be used
		airportPanel2 = new JPanel();
		findFlightText = new JTextField("add destination city");
		findFlightText.setPreferredSize(new Dimension(200,25));
		findFlightButton = new JButton("Find Flights");
		
		//Adding the items in panel2
		airportPanel2.add(findFlightText);
		airportPanel2.add(findFlightButton);
	
		//PANEL 3 - creation and setting of the items that will be used
		airportPanel3 = new JPanel();
		directFlights = new JTextArea("DIRECT FLIGHTS DETAILS");
		directFlights.setPreferredSize(new Dimension(300, 200));
		directFlights.setEditable(false);
		directFlights.setBackground(Color.WHITE);
		indirectFlights = new JTextArea("INDIRECT FLIGHTS DETAILS");
		indirectFlights.setPreferredSize(new Dimension(300,200));
		indirectFlights.setBackground(Color.WHITE);
		indirectFlights.setEditable(false);
		
		//Adding the items in panel3
		airportPanel3.add(directFlights);
		airportPanel3.add(indirectFlights);
		
		//PANEL 4 - creation and setting of the items that will be used
		airportPanel4 = new JPanel();
		backButton = new JButton("Back To Search Screen");
		
		//Adding the items in panel3
		airportPanel4.add(backButton);
				
		
		//configuring the split panel 1
		splitPanel1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPanel1.setTopComponent(airportPanel1);
		splitPanel1.setBottomComponent(splitPanel2);
		//configuring the split panel 2
		splitPanel2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPanel2.setTopComponent(airportPanel2);
		splitPanel2.setBottomComponent(splitPanel3);
		//configuring the split panel 3
		splitPanel3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPanel3.setTopComponent(airportPanel3);
		splitPanel3.setBottomComponent(airportPanel4);
		
		//Creating ButtonListener & adding listeners
		ButtonListener listener = new ButtonListener();
		findFlightButton.addActionListener(listener);
		backButton.addActionListener(listener);
			
		//final settings of the main panel
		mainPanel.add(splitPanel1);	
		
		this.setContentPane(mainPanel);			
		this.setVisible(true);
		this.setSize(900, 500);
		this.setTitle("Airport Page");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//checking which button the user clicked
			if(e.getSource()==findFlightButton) { //if the user clicked on the button "find flight"
				String city = findFlightText.getText();
				if(city.equals(currAirport.getCity())) { //checking if the user added the same city twice
					ArrDepErrorMessagePage err = new ArrDepErrorMessagePage();
				}else if(CentralRegistry.getAirport(city) == null){ //checking if the city that the user added has an airport
					CityErrorMessagePage cityErrorMessage = new CityErrorMessagePage(city);
				}else { //if the city that the user added has an airport continue here to print to the user the direct and indirect flights
						directFlights.setText(CentralRegistry.getDirectFlightsDetails(CentralRegistry.getAirport(city),currAirport));
						indirectFlights.setText(CentralRegistry.getIndirectFlightsDetails(CentralRegistry.getAirport(city), currAirport));
				}
			}else if(e.getSource()==backButton) { //if the user clicked on the "go back to search button" close the window
				dispose();
			}
		}
	}
	
	
}
