package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Bideokluba;

public class PelikulakUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<String> modelPelikulak = new DefaultListModel<String>();
	private JList<String> pelikulak = new JList<String>(modelPelikulak);
	
	private DefaultListModel<String> modelAzkenak = new DefaultListModel<String>();
	private JList<String> azkenak = new JList<String>(modelAzkenak);
	

	public PelikulakUI() {
		setLayout(new BorderLayout());
		pelikulak.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panelaEraiki();
		pelikulakIkusi();
		estreinaldiakIkusi();
	}
	
	public void pelikulakIkusi() {
		ResultSet pelikulak = Bideokluba.getBideokluba().katalogoaIkusi();
		try {
			while (pelikulak.next()) {
				modelPelikulak.addElement(pelikulak.getString(2));
			}
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void estreinaldiakIkusi() {
		ResultSet pelikulak = Bideokluba.getBideokluba().estreinaldiakIkusi();
		try {
			while (pelikulak.next()) {
				modelAzkenak.addElement(pelikulak.getString(2));
			}
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	private void panelaEraiki() {
		JLabel pelikula = new JLabel("          Pelikulak          ");
		
		JPanel p1 = new JPanel();
		p1.add(pelikula);
		
		JScrollPane p2 = new JScrollPane();
		p2.setViewportView(pelikulak);
		
		JPanel p3 = new JPanel(new BorderLayout());
		
		p3.add(p1, BorderLayout.NORTH);
		p3.add(new JPanel(), BorderLayout.WEST);
		p3.add(p2, BorderLayout.CENTER);
		p3.add(new JPanel(), BorderLayout.EAST);
		p3.add(new JPanel(), BorderLayout.SOUTH);
		
		JLabel azkena = new JLabel("          Nobedadeak          ");
		
		JPanel p4 = new JPanel();
		p4.add(azkena);
		
		JScrollPane p5 = new JScrollPane();
		p5.setViewportView(azkenak);
		
		JPanel p6 = new JPanel(new BorderLayout());
		
		p6.add(p4, BorderLayout.NORTH);
		p6.add(new JPanel(), BorderLayout.WEST);
		p6.add(p5, BorderLayout.CENTER);
		p6.add(new JPanel(), BorderLayout.EAST);
		p6.add(new JPanel(), BorderLayout.SOUTH);
		
		JPanel p7 = new JPanel();
		JButton atzera = new JButton("Itzuli");
		p7.add(atzera);
		
		add(p3, BorderLayout.WEST);
		add(p6, BorderLayout.EAST);
		add(p7, BorderLayout.SOUTH);
		
		atzera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LeihoaUI.getNireLeihoa().aldatuPanela(new LoginUI());
				
			}
		});
		
	}

}
