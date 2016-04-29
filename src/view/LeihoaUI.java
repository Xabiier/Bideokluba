package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LeihoaUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final LeihoaUI nireLeihoaUI = new LeihoaUI();

	private LeihoaUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Bideokluba");
	}

	public static LeihoaUI getNireLeihoa() {
		return nireLeihoaUI;
	}
	
	public void aldatuPanela(JPanel pPanel) {
		getContentPane().removeAll();
		add(pPanel);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void sortuDialog(String s){
		JOptionPane.showMessageDialog(null, s);
	}

}
