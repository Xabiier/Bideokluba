package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Bideokluba;

public class BezeroUI extends JPanel{

	public String kodea;
	private JPanel kudeaketa;
	private JButton r1, r2, r3, r4;
	
	private static final long serialVersionUID = 1L;

	public BezeroUI(String pKodea) {
		setLayout(new BorderLayout());
		this.kodea = pKodea;
		
		panelaEraiki();
	}
	
	private void panelaEraiki() {
		JPanel goikoa = new JPanel();
		
		JLabel admin = new JLabel("ERABILTZAILE AUKERAK        ");
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
		r1 = new JButton("Datuak Aldatu");
		pr1.add(r1);
		JPanel pr2 = new JPanel();
		r2 = new JButton("Kredituak Gehitu");
		pr2.add(r2);
		JPanel pr3 = new JPanel();
		r3 = new JButton("Pelikulak Alokatu");
		pr3.add(r3);
		JPanel pr4 = new JPanel();
		r4 = new JButton("Pelikulak Itzuli");
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
				datuakAldatu();
			}
		});
		r2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kredituakGehitu();
			}
		});
		r3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pelikulakAlokatu();
			}
		});
		r4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pelikulakItzuli();				
			}
		});
		itzuli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LeihoaUI.getNireLeihoa().aldatuPanela(new LoginUI());			
			}
		});
	}
	
	private void datuakAldatu() {
		remove(kudeaketa);
		kudeaketa.removeAll();
		revalidate();
		
		JLabel pasahitza = new JLabel("Pasahitza:");
		JLabel izena = new JLabel("Izena:");
		JLabel abizena = new JLabel("Abizena:");
		JLabel helbidea = new JLabel("Helbidea:");
		
		JTextField pasahitzaField = new JPasswordField(15);
		JTextField izenaField = new JTextField(15);
		JTextField abizenaField = new JTextField(15);
		JTextField helbideaField = new JTextField(15);
		
		JButton aldatu1 = new JButton("Aldatu");
		JButton aldatu2 = new JButton("Aldatu");
		JButton aldatu3 = new JButton("Aldatu");
		JButton aldatu4 = new JButton("Aldatu");
		
		JPanel p1 = new JPanel();
		p1.add(pasahitza);
		p1.add(pasahitzaField);
		p1.add(aldatu1);
		
		JPanel p2 = new JPanel();
		p2.add(izena);
		p2.add(izenaField);
		p2.add(aldatu2);
		
		JPanel p3 = new JPanel();
		p3.add(abizena);
		p3.add(abizenaField);
		p3.add(aldatu3);
		
		JPanel p4 = new JPanel();
		p4.add(helbidea);
		p4.add(helbideaField);
		p4.add(aldatu4);
		
		JPanel p5 = new JPanel(new GridLayout(4, 1));
		p5.add(p1);
		p5.add(p2);
		p5.add(p3);
		p5.add(p4);
		
		kudeaketa.add(p5);
		add(kudeaketa, BorderLayout.SOUTH);
		
		aldatu1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bideokluba.getBideokluba().pasahitzaAldatu(kodea, pasahitzaField.getText().trim());
			}
		});
		aldatu2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bideokluba.getBideokluba().izenaAldatu(kodea, izenaField.getText().trim());
			}
		});
		aldatu3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bideokluba.getBideokluba().abizenaAldatu(kodea, abizenaField.getText().trim());
			}
		});
		aldatu4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bideokluba.getBideokluba().helbideaAldatu(kodea, helbideaField.getText().trim());		
			}
		});
		
		LeihoaUI.getNireLeihoa().pack();
	}
	
	private void kredituakGehitu() {
		remove(kudeaketa);
		kudeaketa.removeAll();
		revalidate();
		
		JLabel pelikula = new JLabel("Kreditu-Kopurua:");
		JTextField pelikulaField = new JTextField(15);
		
		JPanel p1 = new JPanel();
	    p1.add(pelikula);
	    p1.add(pelikulaField);
	    
	    JPanel ok = new JPanel();
	    JButton okB = new JButton("Sartu");
	    ok.add(okB);
	    
	    kudeaketa.add(p1, BorderLayout.NORTH);
	    kudeaketa.add(ok, BorderLayout.SOUTH);
	    add(kudeaketa, BorderLayout.SOUTH);
	    
	    LeihoaUI.getNireLeihoa().pack();
	}
	
	private void pelikulakAlokatu() {
		
	}
	
	private void pelikulakItzuli() {

	}
}
