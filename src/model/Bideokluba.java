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
	public static DBKonexioa con = new DBKonexioa();
			
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
	}
	
	public String erabiltzaileaKonektatu(String pErabiltzailea, String pPasahitza){
		String kodea = null;
		String query =	"SELECT `Kodea` "+
						"FROM `BAZKIDE` "+
						"WHERE `Kodea`='" + pErabiltzailea + "' and `Pasahitza`='"+ pPasahitza + "';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(rs.isBeforeFirst()){
				return pErabiltzailea;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kodea;
	}
	
	/*
	 * ADMIN METODOAK
	 */

	public void bazkideGehitu(String pKodea, String pPasahitza){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = df.format(Calendar.getInstance().getTime()); //Gaurko data jarriko da
		
		String query =	"SELECT * "+
						"FROM `BAZKIDE` "+
						"WHERE `Kodea`='" + pKodea + "';";
		
		ResultSet rs = con.kontsultatu(query);
		
		try {
			if(!rs.isBeforeFirst()){
				query = "INSERT INTO `BAZKIDE` "+
						"(`Kodea`, `Pasahitza`, `Noiztik`) "+
						"VALUES('"+ pKodea +"', '"+ pPasahitza +"','"+ data +"');";
				con.aldatu(query);
				LeihoaUI.getNireLeihoa().sortuDialog("Bazkidea sortu da");
			}
			else{
				LeihoaUI.getNireLeihoa().sortuDialog("Bazkidea datu basean dago jada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void bazkideAlta(String pKodea) {
		String query;
		query =	"SELECT * "+
				"FROM `BAZKIDE` "+
				"WHERE `Kodea`='" + pKodea + "';";
		
		ResultSet rs = con.kontsultatu(query);
		try {
			if(!rs.isBeforeFirst()){
				LeihoaUI.getNireLeihoa().sortuDialog("Ez da erabiltzaile hori existitzen");
			}
			else{
				rs.next();
				if(rs.getString(7).equalsIgnoreCase("Baja")){
					query = "UPDATE `BAZKIDE` "+
							"SET `Egoera`='Alta' "+
							"WHERE `Kodea`='" + pKodea + "';";
					con.aldatu(query);
					LeihoaUI.getNireLeihoa().sortuDialog("Bazkideari alta eman zaio");
				}
				else{
					LeihoaUI.getNireLeihoa().sortuDialog("Bazkidea altan dago jada");
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
				LeihoaUI.getNireLeihoa().sortuDialog("Ez da erabiltzaile hori existitzen");
			}
			else{
				rs.next();
				if(rs.getString(7).equalsIgnoreCase("Alta")){
					query = "UPDATE `BAZKIDE` " +
							"SET `Egoera`='Baja' " +
							"WHERE `Kodea`='" + pKodea + "';";
					
					con.aldatu(query);
					
					ResultSet pelikulak = erabiltzailearenPelikulak(pKodea);
					if(pelikulak.isBeforeFirst()) {
						while (pelikulak.next()) {
							query = "UPDATE `PELIKULA` "+
									"SET `Egoera`='deskatalogatuta' "+
									"WHERE `Kodea`='"+pelikulak.getString(1)+"'";
							con.aldatu(query);
							pelikulaItzuli(pKodea, pelikulak.getString(1));
						}
					}
			
					LeihoaUI.getNireLeihoa().sortuDialog("Bazkideari baja eman zaio");
				}
				else{
					LeihoaUI.getNireLeihoa().sortuDialog("Bazkidea bajan dago jada");
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
				LeihoaUI.getNireLeihoa().sortuDialog("Pelikula gehitu da");
			}
			else{
				LeihoaUI.getNireLeihoa().sortuDialog("Pelikula hori existitzen da jada");
			}
		} catch (SQLException e) {e.printStackTrace();}

		
	}
	
	public void bajaEmanPelikula(String pKodea) {
		
		String kontsulta =	"SELECT * "+
				"FROM `PELIKULA` "+
				"WHERE `Kodea`='"+pKodea+"';";
		ResultSet rs = con.kontsultatu(kontsulta);
		try {
			if(rs.isBeforeFirst()) {
				String query = "DELETE FROM `PELIKULA` "+
							   "WHERE `Kodea`='" + pKodea + "';";
				con.aldatu(query);
				LeihoaUI.getNireLeihoa().sortuDialog("Pelikula ezabatu da");
			}
			else{
				LeihoaUI.getNireLeihoa().sortuDialog("Ez da pelikula hori existitzen");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	/*
	 * ERABILTZAILE METODOAK
	 */
	
	public void pasahitzaAldatu(String pKodea, String pPasahitza) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Pasahitza`='"+pPasahitza+"' "+
							"WHERE `Kodea`='"+pKodea+"';";
		con.aldatu(kontsulta);
		LeihoaUI.getNireLeihoa().sortuDialog("Pasahitza aldatu da");
	}
	
	public String getPasahitza(String pKodea) {
		String kodea = null;
		String query =	"SELECT `Pasahitza` "+
						"FROM `BAZKIDE` "+
						"WHERE `Kodea`='"+pKodea+"';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(rs.isBeforeFirst()){
				rs.next();
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kodea;
	}
	
	public void helbideaAldatu(String pKodea, String pHelbidea) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Helbidea`='"+pHelbidea+"' "+
							"WHERE `Kodea`='"+pKodea+"';";
		con.aldatu(kontsulta);
		LeihoaUI.getNireLeihoa().sortuDialog("Helbidea aldatu da");
	}
	
	public String getHelbidea(String pKodea) {
		String query =	"SELECT `Helbidea` "+
						"FROM `BAZKIDE` "+
						"WHERE `Kodea`='" + pKodea +"';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(rs.isBeforeFirst()){
				rs.next();
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void izenaAldatu(String pKodea, String pIzena) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Izena`='"+pIzena+"' "+
							"WHERE `Kodea`='"+pKodea+"';";
		con.aldatu(kontsulta);		
		LeihoaUI.getNireLeihoa().sortuDialog("Izena aldatu da");
	}
	
	public String getIzena(String pKodea) {
		String query =	"SELECT `Izena` "+
						"FROM `BAZKIDE` "+
						"WHERE `Kodea`='" + pKodea +"';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(rs.isBeforeFirst()){
				rs.next();
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void abizenaAldatu(String pKodea, String pAbizena) {
		String kontsulta = 	"UPDATE `BAZKIDE` "+
							"SET `Abizena`='"+pAbizena+"' "+
							"WHERE `Kodea`='"+pKodea+"';";
		con.aldatu(kontsulta);		
		LeihoaUI.getNireLeihoa().sortuDialog("Abizena aldatu da");
	}
	
	public String getAbizena(String pKodea) {
		String query =	"SELECT `Abizena` "+
						"FROM `BAZKIDE` "+
						"WHERE `Kodea`='" + pKodea +"';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(rs.isBeforeFirst()){
				rs.next();
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void kredituaGehitu(String pKodea, int pKreditua) {
		int kred = getKredituak(pKodea);
		if ((kred + pKreditua)<=65000){
			String kontsulta = 	"UPDATE `BAZKIDE` "+
								"SET `Kreditua`='"+(kred +pKreditua)+"' "+
								"WHERE `Kodea`='"+pKodea+"';";
			con.aldatu(kontsulta);	
			LeihoaUI.getNireLeihoa().sortuDialog("Kreditua gehitu da");
		}
		else{
			LeihoaUI.getNireLeihoa().sortuDialog("Ezin da hainbeste kreditu gehitu.\nKreditu maximoa 65000 da eta zure kreditua "+kred+" da.");
		}
	}
	
	public int getKredituak(String pKodea) {
		int kodea = 0;
		String query =	"SELECT `Kreditua` "+
						"FROM `BAZKIDE` "+
						"WHERE `Kodea`='" + pKodea +"';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(rs.isBeforeFirst()){
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kodea;
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
			rs2.next();
			String egoera = rs1.getString(7);
			if(egoera.equalsIgnoreCase("alta") ){
				if(rs2.getString(4).equalsIgnoreCase("libre")){
					if(Integer.parseInt(rs1.getString(6)) >= Integer.parseInt(rs2.getString(3))){ //Kreditu nahikoa
						
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String data = df.format(Calendar.getInstance().getTime());
						
						String kontsulta = 	"INSERT INTO `ALOKAIRUAK` "+
						 	 				"(`Bazkide_kodea`, `Pelikula_kodea`, `Hartze_data`, `Itzultze_data`) "+
						 	 				"VALUES('"+ pBezKodea +"', '"+ pPeliKodea +" ', '"+ data +"', NULL);";
						con.aldatu(kontsulta);
						
						kontsulta = "UPDATE `PELIKULA` "+
									"SET `Egoera`='alokatuta' "+
									"WHERE `Kodea`='" + pPeliKodea + "';";
						con.aldatu(kontsulta);
						
						kontsulta = "UPDATE `BAZKIDE` "+
									"SET `Kreditua`='"+(Integer.parseInt(rs1.getString(6)) - Integer.parseInt(rs2.getString(3)))+"' "+
									"WHERE `Kodea`='" + pBezKodea + "';";
						con.aldatu(kontsulta);
						LeihoaUI.getNireLeihoa().sortuDialog("Pelikula alokatu duzu");
					}
					else{
						LeihoaUI.getNireLeihoa().sortuDialog("Ez duzu kreditu nahikorik pelikula hori alokatzeko");
					}
				}
				else if (rs2.getString(4).equalsIgnoreCase("alokatuta")){
					LeihoaUI.getNireLeihoa().sortuDialog("Pelikula hori alokatuta dago jada");
				}
				else{
					LeihoaUI.getNireLeihoa().sortuDialog("Pelikula hori deskatalogatuta dago");
				}
			}
			else{
				LeihoaUI.getNireLeihoa().sortuDialog("Erabiltzaile hau bajan dago, ezin duzu pelikularik alokatu");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public ResultSet erabiltzailearenPelikulak(String pBezKodea) {
		String query = 	"SELECT `Pelikula_kodea` "+
						"FROM `ALOKAIRUAK` "+
						"WHERE `Bazkide_kodea`='" + pBezKodea+"' AND `Itzultze_data` IS NULL;";
		return con.kontsultatu(query);
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
			rs2.next();
			if(rs1.isBeforeFirst() && (rs2.getString(4).equalsIgnoreCase("alokatuta") || rs2.getString(4).equalsIgnoreCase("deskatalogatuta"))){
				rs1.next();
				
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
				if(!rs2.getString(4).equalsIgnoreCase("deskatalogatuta"))
					LeihoaUI.getNireLeihoa().sortuDialog("Pelikula itzuli da");
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
							"FROM `PELIKULA`"+
							"ORDER BY `Izena`;";
		return con.kontsultatu(kontsulta);
	}
	
	public ResultSet estreinaldiakIkusi() {
		String kontsulta =	"SELECT * "+
						    "FROM `PELIKULA` "+
						    "ORDER BY `Sartze_data` DESC LIMIT 10;"; //Uste dut limit 10 jarrita lehenengo 10ak hartuko direla
		return con.kontsultatu(kontsulta);
	}

	public String getPelikulaIzena(String pKodea) {
		String kodea = null;
		String query =	"SELECT `Izena` "+
						"FROM `PELIKULA` "+
						"WHERE `Kodea`='" + pKodea +"';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(rs.isBeforeFirst()){
				rs.next();
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kodea;
	}
	
	public int getPelikulaPrezioa(String pKodea) {
		int kodea = 0;
		String query =	"SELECT `Prezioa` "+
						"FROM `PELIKULA` "+
						"WHERE `Kodea`='" + pKodea +"';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(rs.isBeforeFirst()){
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kodea;
	}
	
	public String getPelikulaEgoera(String pKodea) {
		String kodea = null;
		String query =	"SELECT `Egoera` "+
						"FROM `PELIKULA` "+
						"WHERE `Kodea`='" + pKodea +"';";
		ResultSet rs = con.kontsultatu(query);
		try {
			if(rs.isBeforeFirst()){
				rs.next();
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kodea;
	}
}
