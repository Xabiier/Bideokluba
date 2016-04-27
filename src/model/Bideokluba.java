package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import view.LeihoaUI;
import view.LoginUI;

public class Bideokluba {
	
	private static Bideokluba nBideokluba = null;
	private static DBKonexioa con = new DBKonexioa();
	private static String erabiltzaileKodea;
			
	private Bideokluba(){}
	
	public static Bideokluba getBideokluba(){
		if(nBideokluba == null){
			nBideokluba = new Bideokluba();
		}
		return nBideokluba;
	}

	public static void main(String[] args) {

		LeihoaUI.getNireLeihoa().aldatuPanela(new LoginUI());
		con.konektatu();
		
		//Bideokluba.getBideokluba().bazkideGehitu("holi", "holi");
		
		//Bideokluba.getBideokluba().estreinaldiakIkusi();

	}
	
	public void erabiltzaileaKonektatu(String pErabiltzailea, String pPasahitza){
		
		String query =	"SELECT * "+
						"FROM `BAZKIDE` "+
						"WHERE `Kodea`='" + pErabiltzailea + "' and `Pasahitza`='"+ pPasahitza + "';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(!rs.isBeforeFirst()){
				System.out.println("Ez da erabiltzaile hori existitzen");
			}
			else{
				rs.next();
				erabiltzaileKodea = rs.getString(1);
				System.out.println("Ongi etorri, " + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		
		String query = 	"INSERT INTO `BAZKIDE` "+
						"(`Kodea`, `Pasahitza`, `Noiztik`) "+
						"VALUES('"+ pKodea +"', '"+ pPasahitza +"','"+ data +"');";
		
		con.aldatu(query);
	}

	public void bazkideAlta(String pKodea) {
		String query;
		query =	"SELECT * "+
				"FROM `BAZKIDE` "+
				"WHERE `Kodea`='" + pKodea + "';";
		
		ResultSet rs = con.kontsultatu(query);
		try {
			if(!rs.isBeforeFirst()){
				System.out.println("Ez da erabiltzaile hori existitzen");
			}
			else{
				rs.next();
				if(rs.getString(7).equalsIgnoreCase("Baja")){
					query = "UPDATE `BAZKIDE` "+
							"SET `Egoera`='Alta' "+
							"WHERE `Kodea`=" + pKodea + ";";
					con.aldatu(query);
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void bazkideBaja(String pKodea) {
		String query;
		
		query =	"SELECT * "+
				"FROM `BAZKIDE` "+
				"WHERE `Kodea`='" + pKodea + "';";
		
		ResultSet rs = con.kontsultatu(query);
		try {
			if(!rs.isBeforeFirst()){
				System.out.println("Ez da erabiltzaile hori existitzen");
			}
			else{
				String aukera;
				rs.next();
				if(rs.getString(7).equalsIgnoreCase("Alta")){
					query = "UPDATE `BAZKIDE` " +
							"SET `Egoera`='Baja' " +
							"WHERE `Kodea`=" + pKodea + ";";
					
					con.aldatu(query);
					
					query = "UPDATE `PELIKULA` " +
							"SET `Egoera`='Deskatalogatuta' " +
							"WHERE `ALOKAIRUAK`.`Bazkide_kodea`='" + pKodea + "' and " +
								   "`ALOKAIRUAK`.`Pelikula_kodea` = `PELIKULA`.`Kodea`;";
					
					con.aldatu(query);
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.aldatu(query);
	}

	public void pelikulaGehitu(String pKodea, String pIzena, int pPrezioa) {
		
		String kontsulta =	"SELECT * "+
							"FROM `PELIKULA` "+
							"WHERE `Kodea`='"+pKodea+"';";
		ResultSet rs = con.kontsultatu(kontsulta);
		try {
			if(!rs.isBeforeFirst()) {
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String data = df.format(Calendar.getInstance().getTime()); //Gaurko data jarriko da
				
				String query = 	"INSERT INTO `PELIKULA` "+
						 	 	"(`Kodea`, `Izena`, `Prezioa`, `Sartze_data`) "+
						 	 	"VALUES('"+ pKodea +"', '"+ pIzena +" ', "+ pPrezioa +", '"+ data +"');";
				
				con.aldatu(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void bajaEmanPelikula(String pKodea) {
		
		String query = "DELETE FROM `PELIKULA` "+
					   "WHERE `Kodea`='" + pKodea + "';";
		con.aldatu(query);
	}
	
	/*
	 * ERABILTZAILE METODOAK
	 */
	
	public void pasahitzaAldatu(String pKodea, String pPasahitza) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Pasahitza`='"+pPasahitza+"' "+
							"WHERE `Kodea`='"+pKodea+"';";
		con.aldatu(kontsulta);
	}
	
	public void helbideaAldatu(String pKodea, String pHelbidea) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Helbidea`='"+pHelbidea+"' "+
							"WHERE `Kodea`='"+pKodea+"';";
		con.aldatu(kontsulta);
	}
	
	public void izenaAldatu(String pKodea, String pIzena) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Izena`='"+pIzena+"' "+
							"WHERE `Kodea`='"+pKodea+"';";
		con.aldatu(kontsulta);		
	}
	
	public void abizenaAldatu(String pKodea, String pAbizena) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Abizena`='"+pAbizena+"' "+
							"WHERE `Kodea`='"+pKodea+"';";
		con.aldatu(kontsulta);		
	}
	
	public void kredituaGehitu(String pKodea, int pKreditua) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Kreditua`='"+pKreditua+"' "+
							"WHERE `Kodea`='"+pKodea+"';";
		con.aldatu(kontsulta);		
	}
	
	public void pelikulaAlokatu(String pBezKodea, String pPeliKodea) {
		
		
		String query1 =	"SELECT * "+
						"FROM `BAZKIDE` "+
						"WHERE `Kodea`='" + pBezKodea + "';";
		ResultSet rs1 = con.kontsultatu(query1);
		
		String query2 =	"SELECT * "+
						"FROM `PELIKULA` "+
						"WHERE `Kodea`='" + pPeliKodea + "';";
		ResultSet rs2 = con.kontsultatu(query2);

		try {
			rs1.next();
			String egoera = rs1.getString(7);
			if(egoera.equalsIgnoreCase("alta") ){
				if(rs2.getString(4).equalsIgnoreCase("libre")){
					if(Integer.parseInt(rs1.getString(6)) >= Integer.parseInt(rs2.getString(3))){ //Kreditu nahikoa
						
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String data = df.format(Calendar.getInstance().getTime());
						
						String kontsulta = 	"INSERT INTO `ALOKAIRUAK` "+
						 	 				"(`Bazkide_kodea`, `Pelikula_kodea`, `Hartze_data`, `Itzultze_data`) "+
						 	 				"VALUES('"+ pBezKodea +"', '"+ pPeliKodea +" ', "+ data +", NULL);";
						con.aldatu(kontsulta);
						
						kontsulta = "UPDATE `PELIKULA` "+
									"SET `Egoera`='alokatuta' "+
									"WHERE `Kodea`='" + pPeliKodea + "';";
						con.aldatu(kontsulta);
						
						kontsulta = "UPDATE `BAZKIDE` "+
									"SET `Kreditua`='`Kreditua` -"+ Integer.parseInt(rs2.getString(3))+"' "+
									"WHERE `Kodea`='" + pBezKodea + "';";
						con.aldatu(kontsulta);
					}
					else{
						System.out.println("Ez duzu kreditu nahikorik pelikula hori alokatzeko");
					}
				}
				else if (rs2.getString(4).equalsIgnoreCase("alokatuta")){
					System.out.println("Pelikula alokatuta dago");
				}
				else{
					System.out.println("Pelikula deskatalogatuta dago");
				}
			}
			else{
				System.out.println("Erabiltzaile hau bajan dago");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

				
	}
	
	public void pelikulaItzuli(String pBezKodea, String pPeliKodea) {
		String query1 =	"SELECT * "+
						"FROM `ALOKAIRUAK` "+
						"WHERE `Bazkide_kodea`='" + pBezKodea + "' and `Pelikula_kodea`='" + pPeliKodea + "';";
		ResultSet rs1 = con.kontsultatu(query1);
		
		String query2 =	"SELECT * "+
						"FROM `PELIKULA` "+
						"WHERE `Kodea`='" + pPeliKodea + "';";
		ResultSet rs2 = con.kontsultatu(query2);
		
		try {
			rs1.next();
			rs2.next();
			if(rs1.isBeforeFirst() && rs2.getString(4).equalsIgnoreCase("alokatuta")){
				
				String kontsulta = 	"UPDATE `PELIKULA` "+
									"SET `Egoera`='libre' "+
									"WHERE `Kodea`='" + pPeliKodea + "';";
				con.aldatu(kontsulta);
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String data = df.format(Calendar.getInstance().getTime());
				
				kontsulta = "UPDATE `ALOKAIRUAK` "+
							"SET `Itzultze_data`='" + data + "' "+
							"WHERE `Bazkide_kodea`='" + pBezKodea + "' and `Pelikula_kodea`='" + pPeliKodea + "' and `Itzultze_data` IS NULL;";
				
				//Baliteke erabiltzaile batek pelikula bat alokatzea eta itzultzea behin, eta beste egun batean berriro alokatzea nahi izatea.
				//Aurreko baldintzetan  <`Itzultze_data` IS NULL> baldintza jarriko ez bagenu, baliteke BESTE pelikula baten alokairuaren
				//itzultze-data aldatzea
				
				con.aldatu(kontsulta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ZATI LIBREKO METODOAK
	 */
	
	public ResultSet katalogoaIkusi() {
		String kontsulta =	"SELECT * "+
							"FROM `PELIKULA`;";
		return con.kontsultatu(kontsulta);
	}
	
	public ResultSet estreinaldiakIkusi() {
		String kontsulta =	"SELECT * "+
						    "FROM `PELIKULA` "+
						    "ORDER BY `Sartze_data` DESC LIMIT 10"; //Uste dut limit 10 jarrita lehenengo 10ak hartuko direla
		return con.kontsultatu(kontsulta);
		
		/*try {
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


}
