package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bideokluba {

public static void main(String[] args) {

		Konexioa con = new Konexioa();
        
        Connection konexioa = con.getMYSQLKonexioa();

        if(konexioa == null){
            System.out.println("Errorea konektatzean");
        }
        else{
            try {
                System.out.println("Konektatu zara :D");

                konexioa.close();
            } catch (SQLException ex) {
                Logger.getLogger(Konexioa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     


	}

}
