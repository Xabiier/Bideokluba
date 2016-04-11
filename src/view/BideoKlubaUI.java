package view;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javafx.scene.layout.Border;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;

public class BideoKlubaUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BideoKlubaUI frame = new BideoKlubaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BideoKlubaUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel ongiEtorri = new JLabel("Ongi etorri Bideoklubera!");
		ongiEtorri.setHorizontalAlignment(JLabel.CENTER);
	    ongiEtorri.setVerticalAlignment(JLabel.CENTER);
	    
		JLabel erabiltzailea = new JLabel("Erabiltzailea:");
		JLabel pasahitza = new JLabel("Pasahitza:    ");
		
		JButton sartuBotoia = new JButton("Sartu");
		JButton gonbidatuak = new JButton("Gonbidatua");
		
	    JTextField erabiltzaileField = new JTextField(15);
	    JPasswordField pasahitzaField = new JPasswordField(15);
		
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
	    
	    JPanel p6 = new JPanel(new BorderLayout(0, 0));
	    p6.add(p4, BorderLayout.NORTH);
	    p6.add(p5, BorderLayout.CENTER);
		
	    getContentPane().add(p6);
		
		
		setVisible(true);
		pack();
	}

}
