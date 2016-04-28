package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Bideokluba;

public class AdminUI extends JPanel{
	
	private JPanel kudeaketa;
	private JButton r1, r2, r3, r4;

	private static final long serialVersionUID = 1L;
	
	public AdminUI() {
		setLayout(new BorderLayout());
		
		panelaEraiki();
	}
	
	private void panelaEraiki() {
		
		JPanel goikoa = new JPanel();
		
		JLabel admin = new JLabel("ADMINISTRAZIO AUKERAK        ");
		admin.setHorizontalAlignment(JLabel.CENTER);
		admin.setVerticalAlignment(JLabel.CENTER);
		
		JPanel itzuliPanel = new JPanel();
		JButton itzuli = new JButton("Itzuli");
		itzuliPanel.add(itzuli);
		
		goikoa.add(admin);
		goikoa.add(itzuliPanel);
		add(goikoa, BorderLayout.NORTH);
		
		JPanel aukerak = new JPanel(new GridLayout(2, 1));
		
		JPanel pr1 = new JPanel();
		r1 = new JButton("Bazkide Berria");
		pr1.add(r1);
		JPanel pr2 = new JPanel();
		r2 = new JButton("Bazkide baten egoera aldatu");
		pr2.add(r2);
		JPanel pr3 = new JPanel();
		r3 = new JButton("Pelikula Berria");
		pr3.add(r3);
		JPanel pr4 = new JPanel();
		r4 = new JButton("Pelikula bati baja eman");
		pr4.add(r4);
		
		aukerak.add(pr1);
		aukerak.add(pr2);
		aukerak.add(pr3);
		aukerak.add(pr4);
		
		add(aukerak, BorderLayout.CENTER);
		kudeaketa = new JPanel(new BorderLayout());
		add(kudeaketa, BorderLayout.SOUTH);
		
		r1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bazkideBerria();
			}
		});
		r2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bazkideAldatu();
			}
		});
		r3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pelikulaBerria();
			}
		});
		r4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pelikulaAldatu();				
			}
		});
		itzuli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LeihoaUI.getNireLeihoa().aldatuPanela(new LoginUI());			
			}
		});
	}
	
	private void bazkideBerria() {
		remove(kudeaketa);
		kudeaketa.removeAll();
		revalidate();
		
		JLabel erabiltzailea = new JLabel("Erabiltzaile-Kodea:");
		JLabel pasahitza = new JLabel("Pasahitza:");
		
		JTextField erabiltzaileField = new JTextField(15);
	    JTextField pasahitzaField = new JPasswordField(15);
	    
	    JPanel p1 = new JPanel();
	    p1.add(erabiltzailea);
	    p1.add(erabiltzaileField);
	    
	    JPanel p2 = new JPanel();
	    p2.add(pasahitza);
	    p2.add(pasahitzaField);
	    
	    JPanel ok = new JPanel();
	    JButton okB = new JButton("Sartu");
	    ok.add(okB);
	    
	    kudeaketa.add(p1, BorderLayout.NORTH);
	    kudeaketa.add(p2, BorderLayout.CENTER);
	    kudeaketa.add(ok, BorderLayout.SOUTH);
	    add(kudeaketa, BorderLayout.SOUTH);
	    
	    okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bideokluba.getBideokluba().bazkideGehitu(erabiltzaileField.getText().trim(), pasahitzaField.getText().trim());			
			}
		});
	    
	    LeihoaUI.getNireLeihoa().pack();
	}
	
	private void bazkideAldatu() {
		remove(kudeaketa);
		kudeaketa.removeAll();
		revalidate();
		
		JLabel erabiltzailea = new JLabel("Erabiltzaile-Kodea:");
		JTextField erabiltzaileField = new JTextField(15);
		
		JPanel p1 = new JPanel();
	    p1.add(erabiltzailea);
	    p1.add(erabiltzaileField);
	    
	    ButtonGroup bg = new ButtonGroup();
	    JRadioButton rb1 = new JRadioButton("Alta");
	    JRadioButton rb2 = new JRadioButton("Baja");
	    
	    bg.add(rb1);
	    bg.add(rb2);
	    
	    JPanel p2 = new JPanel();
	    p2.add(rb1);
	    p2.add(rb2);
	    
	    JPanel ok = new JPanel();
	    JButton okB = new JButton("Aldatu");
	    ok.add(okB);
	    
	    kudeaketa.add(p1, BorderLayout.NORTH);
	    kudeaketa.add(p2, BorderLayout.CENTER);
	    kudeaketa.add(ok, BorderLayout.SOUTH);
	    add(kudeaketa, BorderLayout.SOUTH);
	    
	    LeihoaUI.getNireLeihoa().pack();
	}
	
	private void pelikulaBerria() {
		remove(kudeaketa);
		kudeaketa.removeAll();
		revalidate();
		
		JLabel kodea = new JLabel("Pelikula-Kodea:");
		JLabel izena = new JLabel("Izena:");
		JLabel prezioa = new JLabel("Prezioa:");
		
		JTextField kodeaField = new JTextField(15);
	    JTextField izenaField = new JTextField(15);
	    JTextField prezioaField = new JTextField(15);
	    
	    JPanel p1 = new JPanel();
	    p1.add(kodea);
	    p1.add(kodeaField);
	    
	    JPanel p2 = new JPanel();
	    p2.add(izena);
	    p2.add(izenaField);
	    
	    JPanel p3 = new JPanel();
	    p3.add(prezioa);
	    p3.add(prezioaField);
	    
	    JPanel p4 = new JPanel(new BorderLayout());
	    p4.add(p1, BorderLayout.NORTH);
	    p4.add(p2, BorderLayout.CENTER);
	    p4.add(p3, BorderLayout.SOUTH);
	    
	    JPanel ok = new JPanel();
	    JButton okB = new JButton("Sartu");
	    ok.add(okB);
	    
	    kudeaketa.add(p4, BorderLayout.NORTH);
	    kudeaketa.add(ok, BorderLayout.SOUTH);
	    add(kudeaketa, BorderLayout.SOUTH);
	    
	    okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bideokluba.getBideokluba().pelikulaGehitu(kodeaField.getText().trim(), izenaField.getText().trim(), Integer.parseInt(prezioaField.getText().trim()));			
			}
		});
	    
	    LeihoaUI.getNireLeihoa().pack();
	}
	
	private void pelikulaAldatu() {
		remove(kudeaketa);
		kudeaketa.removeAll();
		revalidate();
		
		JLabel pelikula = new JLabel("Pelikula-Kodea:");
		JTextField pelikulaField = new JTextField(15);
		
		JPanel p1 = new JPanel();
	    p1.add(pelikula);
	    p1.add(pelikulaField);
	    
	    JPanel ok = new JPanel();
	    JButton okB = new JButton("Aldatu");
	    ok.add(okB);
	    
	    kudeaketa.add(p1, BorderLayout.NORTH);
	    kudeaketa.add(ok, BorderLayout.SOUTH);
	    add(kudeaketa, BorderLayout.SOUTH);
	    
	    LeihoaUI.getNireLeihoa().pack();
	}
}
