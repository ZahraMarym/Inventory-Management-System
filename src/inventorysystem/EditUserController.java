/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inventorysystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class EditUserController implements Initializable {

    @FXML
    private TextField txtUserId;
    @FXML
    private Button editBtn;
    @FXML
    private TextField txtPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void edit(ActionEvent event) {
        edit();
        txtPass.setText("");
        txtUserId.setText("");
        
    }
    private void edit(){
        String s = "UPDATE `inventory`.`users` SET `userPassword` = '"+txtPass.getText()+"' WHERE (`userId` = '"+txtUserId.getText()+"');";
        executeQuerey(s);
        inventorysystem.UserController.stage3.close();
    }
private void executeQuerey(String querey) {
       Connection conn = connection.getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(querey);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }  
    @FXML
    private void Exit(ActionEvent event) {
        inventorysystem.UserController.stage3.close();
    }
    
}
