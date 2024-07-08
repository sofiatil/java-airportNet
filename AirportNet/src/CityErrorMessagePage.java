import javax.swing.*;

public class CityErrorMessagePage extends JFrame {
	private JLabel errorLabel;
	private JPanel errorPanel;
	
	
	
	public CityErrorMessagePage(String city) {
		
		errorPanel = new JPanel();	
		errorLabel = new JLabel(city+ " does not have an airport.");
		
		errorPanel.add(errorLabel);
				
		JOptionPane.showMessageDialog(null, errorPanel);
		
	}
	

}
