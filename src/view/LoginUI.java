package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Bideokluba;

public class LoginUI extends JPanel {
	
	//Atributoak
	private static final long serialVersionUID = 1L;

	//Eraikitzailea
	public LoginUI() {
		
		JLabel ongiEtorri = new JLabel("Ongi etorri Bideoklubera!");
		ongiEtorri.setHorizontalAlignment(JLabel.CENTER);
	    ongiEtorri.setVerticalAlignment(JLabel.CENTER);
	    
		JLabel erabiltzailea = new JLabel("Erabiltzailea:");
		JLabel pasahitza = new JLabel("Pasahitza:    ");
		
		JButton sartu = new JButton("Sartu");
		JButton gonbidatuak = new JButton("Gonbidatua");
		
	    final JTextField erabiltzaileField = new JTextField(15);
	    final JTextField pasahitzaField = new JPasswordField(15);
		
	    JPanel p1 = new JPanel();
	    p1.add(erabiltzailea);
	    p1.add(erabiltzaileField);
	    
	    JPanel p2 = new JPanel();
	    p2.add(pasahitza);
	    p2.add(pasahitzaField);
	    
	    JPanel pSartu = new JPanel();
	    pSartu.add(sartu);
	    
	    JPanel pGonbidatua = new JPanel();
	    pGonbidatua.add(gonbidatuak);
	    
	    JPanel p3 = new JPanel(new BorderLayout());
	    p3.add(p1, BorderLayout.NORTH);
	    p3.add(p2, BorderLayout.SOUTH);
	    
	    JPanel p4 = new JPanel(new BorderLayout());
	    p4.add(ongiEtorri, BorderLayout.CENTER);
	    p4.add(p3, BorderLayout.SOUTH);
	    
	    JPanel p5 = new JPanel(new GridLayout(1, 3));
	    p5.add(pSartu);
	    p5.add(pGonbidatua);
	    
	    JPanel p6 = new JPanel(new BorderLayout());
	    p6.add(p4, BorderLayout.NORTH);
	    p6.add(p5, BorderLayout.CENTER);
		
	    add(p6);
	    
	    sartu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(erabiltzaileField.getText().trim().equals("root") && pasahitzaField.getText().trim().equals("root"))
					LeihoaUI.getNireLeihoa().aldatuPanela(new AdminUI());
				else {
					String kodea = Bideokluba.getBideokluba().erabiltzaileaKonektatu(erabiltzaileField.getText().trim(), pasahitzaField.getText().trim());
					if ( kodea != null)
						LeihoaUI.getNireLeihoa().aldatuPanela(new BezeroUI(kodea));
					else 
						JOptionPane.showMessageDialog(null, "Erabiltzaile edo pasahitz okerrak");
					
				}
			}
		});
	    
	    gonbidatuak.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LeihoaUI.getNireLeihoa().aldatuPanela(new PelikulakUI());
				
			}
		});
	    
	    LeihoaUI.getNireLeihoa().getRootPane().setDefaultButton(sartu);
	}

}
