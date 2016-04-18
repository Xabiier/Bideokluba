package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Bideokluba;

public class BideoKlubaUI extends JFrame {
	
	//Atributoak
	private static final long serialVersionUID = 1L;

	//Eraikitzailea
	public BideoKlubaUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		JLabel ongiEtorri = new JLabel("Ongi etorri Bideoklubera!");
		ongiEtorri.setHorizontalAlignment(JLabel.CENTER);
	    ongiEtorri.setVerticalAlignment(JLabel.CENTER);
	    
		JLabel erabiltzailea = new JLabel("Erabiltzailea:");
		JLabel pasahitza = new JLabel("Pasahitza:    ");
		
		JButton sartuBotoia = new JButton("Sartu");
		JButton gonbidatuak = new JButton("Gonbidatua");
		JButton admin = new JButton("Administratzailea");
		
	    JTextField erabiltzaileField = new JTextField(15);
	    JTextField pasahitzaField = new JPasswordField(15);
		
	    JPanel p1 = new JPanel();
	    p1.add(erabiltzailea);
	    p1.add(erabiltzaileField);
	    
	    JPanel p2 = new JPanel();
	    p2.add(pasahitza);
	    p2.add(pasahitzaField);
	    
	    JPanel p3 = new JPanel(new BorderLayout(0, 0));
	    p3.add(p1, BorderLayout.NORTH);
	    p3.add(p2, BorderLayout.SOUTH);
	    
	    JPanel p4 = new JPanel(new BorderLayout(0, 0));
	    p4.add(ongiEtorri, BorderLayout.CENTER);
	    p4.add(p3, BorderLayout.SOUTH);
	    
	    JPanel p5 = new JPanel(new BorderLayout(0, 0));
	    p5.add(sartuBotoia, BorderLayout.WEST);
	    p5.add(gonbidatuak, BorderLayout.EAST);
	    p5.add(admin, BorderLayout.CENTER);
	    
	    JPanel p6 = new JPanel(new BorderLayout(0, 0));
	    p6.add(p4, BorderLayout.NORTH);
	    p6.add(p5, BorderLayout.CENTER);
		
	    getContentPane().add(p6);
	    
	    sartuBotoia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Bideokluba.getBideokluba().erabiltzaileaKonektatu(erabiltzaileField.getText().trim(), 
						pasahitzaField.getText().trim());
				
			}
		});
	    
	    admin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Bideokluba.getBideokluba().administratzaileaKonektatu();
				
			}
		});
		
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	//Beste metodoak

}
