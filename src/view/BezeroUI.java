package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.Bideokluba;

public class BezeroUI extends JPanel{

	public String kodea;
	private JPanel kudeaketa;
	private JButton r1, r2, r3, r4;
	private JLabel labelPasahitza, labelIzena, labelAbizena, labelHelbidea;
	
	private JTextPane pelikulaInfo = new JTextPane();
	
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
		
		labelPasahitza = new JLabel("Pasahitza: "+Bideokluba.getBideokluba().getPasahitza(kodea));
		labelIzena = new JLabel("Izena: "+Bideokluba.getBideokluba().getIzena(kodea));
		labelAbizena = new JLabel("Abizena: "+Bideokluba.getBideokluba().getAbizena(kodea));
		labelHelbidea = new JLabel("Helbidea: "+Bideokluba.getBideokluba().getHelbidea(kodea));
		
		JButton aldatu1 = new JButton("Aldatu");
		JButton aldatu2 = new JButton("Aldatu");
		JButton aldatu3 = new JButton("Aldatu");
		JButton aldatu4 = new JButton("Aldatu");
		
		JPanel p1 = new JPanel();
		p1.add(labelPasahitza);
		p1.add(aldatu1);
		
		JPanel p2 = new JPanel();
		p2.add(labelIzena);
		p2.add(aldatu2);
		
		JPanel p3 = new JPanel();
		p3.add(labelAbizena);
		p3.add(aldatu3);
		
		JPanel p4 = new JPanel();
		p4.add(labelHelbidea);
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
				aldatu("pasahitza");
			}
		});
		aldatu2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aldatu("izena");
			}
		});
		aldatu3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aldatu("abizena");
			}
		});
		aldatu4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aldatu("helbidea");
			}
		});
		LeihoaUI.getNireLeihoa().pack();
	}
	
	private void kredituakGehitu() {
		remove(kudeaketa);
		kudeaketa.removeAll();
		revalidate();
		
		JLabel kreditua = new JLabel("Kreditu-Kopurua:");
		final JTextField kredituaField = new JTextField(15);
		
		JPanel p1 = new JPanel();
	    p1.add(kreditua);
	    p1.add(kredituaField);
	    
	    JPanel ok = new JPanel();
	    JButton okB = new JButton("Sartu");
	    ok.add(okB);
	    
	    kudeaketa.add(p1, BorderLayout.NORTH);
	    kudeaketa.add(ok, BorderLayout.SOUTH);
	    add(kudeaketa, BorderLayout.SOUTH);
	    
	    okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kred = kredituaField.getText().trim();
				if (!kred.isEmpty() && LeihoaUI.getNireLeihoa().naturalaDa(kred)){
					Bideokluba.getBideokluba().kredituaGehitu(kodea, Integer.parseInt(kred));
					kredituaField.setText("");
				}
			}
		});

	    LeihoaUI.getNireLeihoa().pack();
	}
	
	private void pelikulakAlokatu() {
		remove(kudeaketa);
		kudeaketa.removeAll();
		revalidate();
		
		final DefaultListModel<String> modelPelikulak = new DefaultListModel<String>();
		final JList<String> pelikulak = new JList<String>(modelPelikulak);
		final ArrayList<String> pelikulakKey = new ArrayList<String>();
		
		ResultSet pelikulaZerrenda = Bideokluba.getBideokluba().pelikulaLibreakIkusi();
		try {
			while (pelikulaZerrenda.next()) {
				modelPelikulak.addElement(pelikulaZerrenda.getString(2));
				pelikulakKey.add(pelikulaZerrenda.getString(1));
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		JScrollPane p = new JScrollPane();
		p.setViewportView(pelikulak);
		
		JPanel goikoa = new JPanel(new BorderLayout());
		JLabel dirua = new JLabel("Daukazun dirua: "+Bideokluba.getBideokluba().getKredituak(kodea));
		goikoa.add(dirua, BorderLayout.NORTH);
		goikoa.add(p, BorderLayout.SOUTH);
		
		JPanel panelAlokatu = new JPanel();
		JButton buttonAlokatu = new JButton("Alokatu");
		panelAlokatu.add(buttonAlokatu);
		
		kudeaketa.add(goikoa, BorderLayout.NORTH);
		kudeaketa.add(pelikulaInfo, BorderLayout.CENTER);
		kudeaketa.add(panelAlokatu, BorderLayout.SOUTH);
		
		add(kudeaketa, BorderLayout.SOUTH);
		LeihoaUI.getNireLeihoa().pack();
		
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

		buttonAlokatu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = pelikulak.getSelectedIndex();
				if(i >= 0)
					Bideokluba.getBideokluba().pelikulaAlokatu(kodea, pelikulakKey.get(i));
					pelikulakKey.remove(i);
					modelPelikulak.remove(i);
					dirua.setText("Daukazun dirua: "+Bideokluba.getBideokluba().getKredituak(kodea));
					
			}
		});
		
	}
	
	private void pelikulakItzuli() {
		remove(kudeaketa);
		kudeaketa.removeAll();
		revalidate();
		
		final DefaultListModel<String> modelAlokatuak = new DefaultListModel<String>();
		final JList<String> alokatuak = new JList<String>(modelAlokatuak);
		final ArrayList<String> alokatuakKey = new ArrayList<String>();
		
		ResultSet alokatuZerrenda = Bideokluba.getBideokluba().erabiltzailearenPelikulak(kodea);
		try {
			while (alokatuZerrenda.next()) {
				modelAlokatuak.addElement(Bideokluba.getBideokluba().getPelikulaIzena(alokatuZerrenda.getString(1)));
				alokatuakKey.add(alokatuZerrenda.getString(1));
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		JScrollPane p = new JScrollPane();
		p.setViewportView(alokatuak);
		
		JPanel panelItzuli = new JPanel();
		JButton buttonItzuli = new JButton("Itzuli");
		panelItzuli.add(buttonItzuli);
		
		kudeaketa.add(p, BorderLayout.NORTH);
		kudeaketa.add(pelikulaInfo, BorderLayout.CENTER);
		kudeaketa.add(panelItzuli, BorderLayout.SOUTH);
		
		add(kudeaketa, BorderLayout.SOUTH);
		LeihoaUI.getNireLeihoa().pack();
		
		alokatuak.addMouseListener(new MouseAdapter() {
			@Override
            public void mousePressed(MouseEvent e) {
				int i = alokatuak.getSelectedIndex();
				if(i >= 0) {
					String kodeaAux = alokatuakKey.get(i);
	            	pelikulaInfo.setText("Pelikula: "+alokatuak.getSelectedValue()+
	            			"\nKodea: "+kodeaAux);
	            	LeihoaUI.getNireLeihoa().pack();
				}
			}
		});

		buttonItzuli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = alokatuak.getSelectedIndex();
				if(i >= 0) {
					Bideokluba.getBideokluba().pelikulaItzuli(kodea, alokatuakKey.get(i));
					alokatuakKey.remove(i);
					modelAlokatuak.remove(i);
				}
			}
		});
	}
	
	private void aldatu(final String pAldaketa) {
		
		JPanel p1 = new JPanel(new BorderLayout());
		JPanel p2 = new JPanel();
		JLabel testua = new JLabel();
		final JTextField field;
		
		if(pAldaketa.equals("pasahitza")) {
			testua.setText("Aldatu pasahitza:");
			field = new JPasswordField(15);
		}
		else {
			if(pAldaketa.equals("izena"))
				testua.setText("Aldatu izena:");
			else if(pAldaketa.equals("abizena"))
				testua.setText("Aldatu abizena:");
			else
				testua.setText("Aldatu helbidea:");
			field = new JTextField(15);
		}
		
		p2.add(testua);
		p2.add(field);
		
		JPanel p3 = new JPanel();
		JButton ok = new JButton("Aldatu");
		p3.add(ok);
		
		p1.add(p2, BorderLayout.NORTH);
		p1.add(p3, BorderLayout.SOUTH);
		
		final JFrame frame = new JFrame();
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (!field.getText().trim().isEmpty()){
					if(pAldaketa.equals("pasahitza")){
						Bideokluba.getBideokluba().pasahitzaAldatu(kodea, field.getText().trim());
						labelPasahitza.setText("Pasahitza: "+field.getText().trim());
					}
					else if(pAldaketa.equals("izena")){
						Bideokluba.getBideokluba().izenaAldatu(kodea, field.getText().trim());
						labelIzena.setText("Izena: "+field.getText().trim());
					}
					else if(pAldaketa.equals("abizena")){
						Bideokluba.getBideokluba().abizenaAldatu(kodea, field.getText().trim());
						labelAbizena.setText("Abizena: "+field.getText().trim());
					}
					else{
						Bideokluba.getBideokluba().helbideaAldatu(kodea, field.getText().trim());
						labelHelbidea.setText("Helbidea: "+field.getText().trim());
					}
				}
				frame.dispose();
			}
		});
		
        JOptionPane.showOptionDialog(frame, p1, "Aldaketa", JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		
	}
	
}
