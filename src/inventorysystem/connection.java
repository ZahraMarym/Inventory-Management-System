/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import java.sql.DriverManager;

/**
 *
 * @author Zahra Maryam
 */
public class connection {
    public static java.sql.Connection getConnection(){
        java.sql.Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","Zahra@115");
            System.out.println("Connected Successfully");
            return  conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
}
