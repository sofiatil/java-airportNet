import javax.swing.*;

public class ArrDepErrorMessagePage extends JFrame{
	private JLabel errorLabel;
	private JPanel errorPanel;
	
	public ArrDepErrorMessagePage() {
		
		errorPanel = new JPanel();
		errorLabel = new JLabel("Arrival and departure city cannot be the same!");
		
		errorPanel.add(errorLabel);
		
		JOptionPane.showMessageDialog(null,errorPanel);
	}

}
