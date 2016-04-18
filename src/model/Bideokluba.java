package model;


import java.sql.ResultSet;
import java.sql.SQLException;

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


}
