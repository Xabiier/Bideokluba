package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import model.Bideokluba;

public class PelikulakUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<String> modelPelikulak = new DefaultListModel<String>();
	private JList<String> pelikulak = new JList<String>(modelPelikulak);
	private ArrayList<String> pelikulakKey = new ArrayList<String>();
	
	private DefaultListModel<String> modelAzkenak = new DefaultListModel<String>();
	private JList<String> azkenak = new JList<String>(modelAzkenak);
	private ArrayList<String> azkenakKey = new ArrayList<String>();
	
	private JTextPane pelikulaInfo = new JTextPane();
	
	public PelikulakUI() {
		setLayout(new BorderLayout());
		pelikulak.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panelaEraiki();
		pelikulakIkusi();
		estreinaldiakIkusi();
	}
	
	public void pelikulakIkusi() {
		ResultSet pelikulaZerrenda = Bideokluba.getBideokluba().katalogoaIkusi();
		
		try {
			while (pelikulaZerrenda.next()) {
				modelPelikulak.addElement(pelikulaZerrenda.getString(2));
				pelikulakKey.add(pelikulaZerrenda.getString(1));
			}
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void estreinaldiakIkusi() {
		ResultSet pelikulaZerrenda = Bideokluba.getBideokluba().estreinaldiakIkusi();
		try {
			while (pelikulaZerrenda.next()) {
				modelAzkenak.addElement(pelikulaZerrenda.getString(2));
				azkenakKey.add(pelikulaZerrenda.getString(1));
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
		
		JPanel p7 = new JPanel(new BorderLayout());
		JPanel p8 = new JPanel();
		JButton atzera = new JButton("Itzuli");
		p8.add(atzera);
		//pelikulaInfo.setHorizontalAlignment(JLabel.CENTER);
		//pelikulaInfo.setVerticalAlignment(JLabel.CENTER);
		pelikulaInfo.setEditable(false);
		pelikulaInfo.setBackground(null);
		pelikulaInfo.setBorder(null);
		p7.add(pelikulaInfo, BorderLayout.NORTH);
		p7.add(p8, BorderLayout.SOUTH);
		
		add(p3, BorderLayout.WEST);
		add(p6, BorderLayout.EAST);
		add(p7, BorderLayout.SOUTH);
		
		atzera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LeihoaUI.getNireLeihoa().aldatuPanela(new LoginUI());
				
			}
		});
		
		pelikulak.addMouseListener(new MouseAdapter() {
			 @Override
             public void mousePressed(MouseEvent e) {
					String kodeaAux = pelikulakKey.get(pelikulak.getSelectedIndex());
	            	pelikulaInfo.setText("Pelikula: "+pelikulak.getSelectedValue()+
	            			"\nKodea: "+kodeaAux+
	            			"\nPrezioa: "+Bideokluba.getBideokluba().getPelikulaPrezioa(kodeaAux)+
	            			"\nEgoera: "+Bideokluba.getBideokluba().getPelikulaEgoera(kodeaAux));
	            	LeihoaUI.getNireLeihoa().pack();
			 }
		});
		
		azkenak.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	pelikulaInfo.setText(azkenak.getSelectedValue());
            	
            	String kodeaAux = azkenakKey.get(azkenak.getSelectedIndex());
            	pelikulaInfo.setText("Pelikula: "+azkenak.getSelectedValue()+
            			"\nKodea: "+kodeaAux+
            			"\nPrezioa: "+Bideokluba.getBideokluba().getPelikulaPrezioa(kodeaAux)+
            			"\nEgoera: "+Bideokluba.getBideokluba().getPelikulaEgoera(kodeaAux));
            	LeihoaUI.getNireLeihoa().pack();
            }
        });
	}

}
