package model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import view.BideoKlubaUI;

public class Bideokluba {
	
	private static Bideokluba nBideokluba = null;
	private static DBKonexioa con = new DBKonexioa();
			
	private Bideokluba(){}
	
	public static Bideokluba getBideokluba(){
		if(nBideokluba == null){
			nBideokluba = new Bideokluba();
		}
		return nBideokluba;
	}

	public static void main(String[] args) {

		new BideoKlubaUI();
		con.konektatu();
		String query;
		
		/*
		//Kontsulta edo eguneraketa egin
		//if kontsulta
			System.out.println("Idatzi egin nahi duzun kontsulta: ");
			query = null;//Teklatutik jaso
			con.kontsultatu(query);
		//else if eguneraketa
			System.out.println("Idatzi egin nahi duzun aldaketa: ");
			query = null;//Teklatutik jaso
			con.aldatu(query);
			*/
		
		

	}
	
	public void erabiltzaileaKonektatu(String pErabiltzailea, String pPasahitza){
		/*String a = "INSERT INTO Bazkide"
				+ "(`Kodea`, `Pasahitza`, `Izena`, `Abizena`, `Helbidea`, `Kreditua`, `Egoera`, `Noiztik` "
				+ "VALUES('Holi', 'Holi', 'Holi', 'Holi', 'Holi kalea', 10, 'Holi', '1111-11-11'";
		con.aldatu(a);*/
		
		String query =	 "SELECT *"
						+"FROM `BAZKIDE` "
						+"WHERE `Kodea`='" + pErabiltzailea + "' and `Pasahitza`='"+ pPasahitza + "';";
		ResultSet rs = con.kontsultatu(query);
		boolean admin = false;
		try {
			if(!rs.isBeforeFirst()){
				System.out.println("Ez da erabiltzaile hori existitzen");
			}
			else{
				rs.next();
				if(rs.getInt(9) == 1){
					admin = true;
				}
				System.out.println("Ongi etorri, " + rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void administratzaileaKonektatu() {
		System.out.println("Administratzaile bezala sartu zara\n");
		System.out.println("Zer egin nahiko zenuke?\n"
				+ "1 - Bazkide berria sartu\n"
				+ "2 - Bazkide bati alta/baja eman\n"
				+ "3 - Pelikula berria sartu\n"
				+ "4 - Pelikula bati baja eman\n"
				+ "5 - Sistematik atera");
		Scanner sc = new Scanner(System.in);
		Integer aukera = Integer.parseInt(sc.next());
		while(aukera<1 || aukera>5){
			System.out.println("Aukera ez egokia, aukeratu zenbaki egoki bat");
			aukera = Integer.parseInt(sc.next());
		}
		String query;
		switch(aukera){
		case(1):{
			bazkideGehitu();
			break;
		}
		case(2):{
			
			break;
		}
		case(3):{
				
			break;
		}
		case(4):{
			
			break;
		}
		case(5):{
			System.out.println();
		
			break;
		}
		}
		
	}
	
	private void bazkideGehitu(){
		
		String query;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Sartu bazkidearen kodea: ");
		String kodea = sc.next();
		
		System.out.print("Sartu bazkidearen pasahitza: ");
		String pasahitza = sc.next();
		
		System.out.print("Sartu bazkidearen izena: ");
		String izena = sc.next();
		
		System.out.print("Sartu bazkidearen abizena: ");
		String abizena = sc.next();
		
		System.out.print("Sartu bazkidearen helbidea: ");
		String helbidea = sc.next();
		
		String kreditua = "0"; //Hasieran guztiak 0 krediturekin hasten dira
		
		String egoera = "alta"; //alta edo baja
		
		String data;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		data = df.format(cal.getTime()); //Gaurko data jarriko da
		
		query = "INSERT INTO Bazkide"
		+ "(`Kodea`, `Pasahitza`, `Izena`, `Abizena`, `Helbidea`, `Kreditua`, `Egoera`, `Noiztik` "
		+ "VALUES('"+ kodea +"', '"+ pasahitza +"', '"+ izena +"', '"+ abizena +"', '"+ helbidea +"', "+ kreditua +", '"+ egoera +"', '"+ data +"'";
		
		con.aldatu(query);
	}


}
