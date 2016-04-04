package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Konexioa {

    private String erabiltzailea = "root";
    
    private String pasahitza = ""; 

    private String host = "localhost";

    private String izena_BD = "Bideokluba"; 

    private Connection con = null;

	public Connection getMYSQLKonexioa() {
		 try{
	            Class.forName("com.mysql.jdbc.Driver").newInstance( );
	            String zerbitzaria = "jdbc:mysql://"+host+"/"+izena_BD;
	            con = DriverManager.getConnection(zerbitzaria, erabiltzailea, pasahitza);
	            return con;
	        }catch(Exception e){
	            e.printStackTrace();
	            return con;
	        }
	}

}
