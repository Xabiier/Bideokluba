package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executor;


public class DBKonexioa {
	
	private Connection konexioa = null;

    private String erabiltzaileaDB = "bideokluba";
    
    private String pasahitzaDB = "bideokluba"; 

    private String hostDB = "127.0.0.1:3306";

    private String izenaBD = "bideokluba";
    
    public DBKonexioa() {
		
	}
	
	public void konektatu() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String datuBasea = "jdbc:mysql://"+hostDB+"/"+izenaBD;
            try{
            	//((DriverManager) konexioa).setLoginTimeout(5000);
            	konexioa = DriverManager.getConnection(datuBasea, erabiltzaileaDB, pasahitzaDB);
            }catch(SQLException e){
            	e.printStackTrace();
            }
            
            if(this.konexioa != null){
            	try{
            		Statement statement = konexioa.createStatement();
            	}catch(SQLException e){
            		System.out.println("Unable to create statement");
            	}
                System.out.println("Konektatu da");
            }else{
                System.out.println("Ez da konektatu");                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public ResultSet kontsultatu(String pKontsulta) {
		ResultSet emaitza;
		try {
			Statement kontsulta = this.konexioa.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			emaitza = kontsulta.executeQuery(pKontsulta);
		} catch (SQLException e) {
            e.printStackTrace();
            return null;
		}
		return emaitza;
	}
	
	public boolean aldatu(String pAldaketa) {
		try {
			Statement aldaketa =  this.konexioa.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            aldaketa.executeUpdate(pAldaketa);
            aldaketa.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
