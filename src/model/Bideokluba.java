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

	}
	
	/*
	 * ADMIN METODOAK
	 */
	
	public void adminKonektatu() {
		/*
		System.out.println("\nAdministratzaile menua:\n");
		
		System.out.println("\nZer egin nahiko zenuke?\n"
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
		sc.close();
		switch(aukera){
		case(1):{
			bazkideGehitu();
			break;
		}
		case(2):{
			bazkideAltaBaja();
			break;
		}
		case(3):{
			pelikulaGehitu();
			break;
		}
		case(4):{
			bajaEmanPelikula();
			break;
		}
		case(5):{
			System.out.println("Agur");
		
			break;
		}
		}
		*/
	}

	public void bazkideGehitu(String pKodea, String pPasahitza){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = df.format(Calendar.getInstance().getTime()); //Gaurko data jarriko da
		
		String query = "INSERT INTO Bazkide"
			  + "(`Kodea`, `Pasahitza`, `Noiztik` "
		      + "VALUES('"+ pKodea +"', '"+ pPasahitza +"','"+ data +"'";
		
		con.aldatu(query);
	}

	public void bazkideAltaBaja(String pKodea, String pEgoera) {
		String query;
		Scanner sc = new Scanner(System.in);
		
		query =	 "SELECT *"
				+"FROM `BAZKIDE`"
				+"WHERE `Kodea`='" + pKodea + "';";
		
		ResultSet rs = con.kontsultatu(query);
		try {
			if(!rs.isBeforeFirst()){
				System.out.println("Ez da erabiltzaile hori existitzen");
			}
			else{
				String aukera;
				rs.next();
				if(rs.getString(7).equalsIgnoreCase("alta") && pEgoera.equalsIgnoreCase("baja")){
					System.out.println("Erabiltzaile hori altan dago, ziur baja eman nahi diozula? (B/E) ");
					aukera = sc.next();
					if(aukera.equalsIgnoreCase("B")){
						query = "UPDATE `BAZKIDE`"
							  + "SET `Egoera`='" + pEgoera + "'"
							  + "WHERE `Kodea`=" + pKodea + ";";
						
					//GARRANTZITSUA, BAZKIDE HORI ALOKATUTA DAUZKAN PELIKULAK ITZULI BEHAR AL DIRA?
					}
				}
				else{
					query = "UPDATE `BAZKIDE`"
						  + "SET `Egoera`='" + pEgoera + "'"
						  + "WHERE `Kodea`=" + pKodea + ";";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.close();
	}

	public void pelikulaGehitu(String pKodea, String pIzena, int pPrezioa) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = df.format(Calendar.getInstance().getTime()); //Gaurko data jarriko da
		
		String query = "INSERT INTO `BAZKIDE`"
				 	 + "(`Kodea`, `Izena`, `Prezioa`, `Sartze_data` "
				 	 + "VALUES('"+ pKodea +"', '"+ pIzena +" ', "+ pPrezioa +", '"+ data +"'";
		
		con.aldatu(query);
	}
	
	public void bajaEmanPelikula(String pKodea) {
		
		String query = "DELETE FROM `PELIKULA"+
					   "WHERE `Kodea`='" + pKodea + "';";
		con.aldatu(query);
		
		
	}
	
	/*
	 * ERABILTZAILE METODOAK
	 */
	
	public void erabiltzaileaKonektatu(String pErabiltzailea, String pPasahitza){
		
		
		String query =	 "SELECT *"+
						 "FROM `BAZKIDE` "+
						 "WHERE `Kodea`='" + pErabiltzailea + "' and `Pasahitza`='"+ pPasahitza + "';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(!rs.isBeforeFirst()){
				System.out.println("Ez da erabiltzaile hori existitzen");
			}
			else{
				rs.next();
				System.out.println("Ongi etorri, " + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void pasahitzaAldatu(String pKodea, String pPasahitza) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Pasahitza`="+pPasahitza+" "+
							"WHERE `Kodea`="+pKodea+";";
		con.aldatu(kontsulta);
	}
	
	public void helbideaAldatu(String pKodea, String pHelbidea) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Helbidea`="+pHelbidea+" "+
							"WHERE `Kodea`="+pKodea+";";
		con.aldatu(kontsulta);
	}
	
	public void izenaAldatu(String pKodea, String pIzena) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Izena`="+pIzena+" "+
							"WHERE `Kodea`="+pKodea+";";
		con.aldatu(kontsulta);		
	}
	
	public void abizenaAldatu(String pKodea, String pAbizena) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Abizena`="+pAbizena+" "+
							"WHERE `Kodea`="+pKodea+";";
		con.aldatu(kontsulta);		
	}
	
	public void kredituaGehitu(String pKodea, int pKreditua) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Abizena`="+pKreditua+" "+
							"WHERE `Kodea`="+pKodea+";";
		con.aldatu(kontsulta);		
	}
	
	public void pelikulaAlokatuItzuli(String pKodea) {
		
	}
	
	/*
	 * ZATI LIBREKO METODOAK
	 */
	
	public void katalogoaIkusi() {
		String kontsulta =	"SELECT * "+
							"FROM `PELIKULA`;";
		ResultSet rs = con.kontsultatu(kontsulta);
	}
	
	public void estreinaldiakIkusi() {
		String kontsulta =	"SELECT * "+
						    "FROM `PELIKULA`"+
						    "ORDER BY `Sartze_data` DESC LIMIT 10"; //Uste dut limit 10 jarrita lehenengo 10ak hartuko direla
		ResultSet rs = con.kontsultatu(kontsulta);
	}


}
