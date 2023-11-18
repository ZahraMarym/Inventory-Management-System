/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inventorysystem;

import static inventorysystem.UserController.stage2;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class AddUserController implements Initializable {

    @FXML
    private TextField txtFirst;
    @FXML
    private TextField txtLast;
    @FXML
    private TextField txtEmail;
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
    private void Add(ActionEvent event) {
         if(txtFirst.getText().equals("") || txtLast.getText().equals("") || txtEmail.getText().equals("") || txtPass.getText().equals("")){
            Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Invalid Fields");
            alt.setContentText("Enter all data in Fields");
        alt.show();}
        else{
        Add();
        txtFirst.setText("");
        txtLast.setText("");
        txtEmail.setText("");
        txtPass.setText("");}
    }
    private void executeQuerey(String querey) {
       Connection conn = connection.getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(querey);
        }catch(Exception ex){
             Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Invalid Data");
            alt.setContentText("Data cannot be inserted");
            alt.show();
        }
    }  
    private void Add()  {
        String s = "INSERT INTO `inventory`.`users` (`firstName`, `lastName`, `email`, `userPassword`) VALUES ( '" +txtFirst.getText()+"' , '"+txtLast.getText() +"' , '"+ txtEmail.getText()+"', '"+txtPass.getText()+"');";
        executeQuerey(s);
        inventorysystem.UserController.stage2.close();
        
    }

    @FXML
    private void Exit(ActionEvent event) throws IOException {
        inventorysystem.UserController.stage2.close();
    
}

}
