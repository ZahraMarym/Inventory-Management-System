/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inventorysystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField txtFirst;
    @FXML
    private TextField txtLast;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void Back(ActionEvent event) throws IOException {
         Parent root2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene3 = new Scene(root2 );
            stage2.setScene(scene3);
            stage2.show();
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
    private void Add()  {
        String s = "INSERT INTO `inventory`.`users` (`firstName`, `lastName`, `email`, `userPassword`) VALUES ( '" +txtFirst.getText()+"' , '"+txtLast.getText() +"' , '"+ txtEmail.getText()+"', '"+txtPass.getText()+"');";
        executeQuerey(s);
        
    }
    @FXML
    private void Register(ActionEvent event) throws IOException {
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
        Parent root2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene3 = new Scene(root2 );
            stage2.setScene(scene3);
            stage2.show();
    }
    
}
