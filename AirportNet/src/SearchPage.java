import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class SearchPage extends JFrame {
	private JPanel searchPanel;
	private JTextField findByCityText; 
	private JButton findByCityButton;
	private ArrayList<Airport> aList;
	private ArrayList<Flight> fList;
	
		
	public SearchPage(ArrayList<Airport> airportsList, ArrayList<Flight> flightsList) {	
		aList = airportsList;
		fList = flightsList;
//		create panel
		searchPanel = new JPanel();
//		create text field
		findByCityText = new JTextField("add a city here");
		findByCityText.setPreferredSize(new Dimension(150,20));
		searchPanel.add(findByCityText);
//		create button
		findByCityButton = new JButton("Find");
		findByCityButton.setPreferredSize(new Dimension(60,20));
		searchPanel.add(findByCityButton);
		
		ButtonListener listener = new ButtonListener();
		findByCityButton.addActionListener(listener);
		

		
//		Frame Properties
		this.setContentPane(searchPanel);
		this.setVisible(true);
		this.setSize(400, 170);
		this.setTitle("Find Airport");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==findByCityButton) {
				boolean check = false;
				String city = findByCityText.getText();
				for (Airport a: aList) {
					if(a.getCity().equals(city)) {
						check = true;
						AirportPage ap = new AirportPage(aList,fList,a);
					}
				}
				if(!check){
					CityErrorMessagePage cem= new CityErrorMessagePage(city);
				}
			}
			
			
		}
		
	}

}
