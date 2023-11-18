/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inventorysystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSp;
    @FXML
    private Button Exit;
    @FXML
    private PasswordField txtPass;
    @FXML
    private AnchorPane Anchor;
    @FXML
    private Label label;
    PreparedStatement ps;
    Connection conn;
    ResultSet rs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) throws IOException, SQLException {
       if(txtUser.getText().equals("") || txtPass.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Enter the fields properly");
       }
       else if((txtUser.getText().equals("admin") && txtPass.getText().equals("123"))) {
           JOptionPane.showMessageDialog(null, "Login Successful!");
           Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene3 = new Scene(root2 );
            stage2.setScene(scene3);
            stage2.show();
       }
       else{
           conn = connection.getConnection();
            ps = conn.prepareStatement("select * from inventory.users where userName=? and userPassword =?;");
            ps.setString(1, txtUser.getText());
            ps.setString(2, txtPass.getText());
            rs = ps.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Login Successful!");
                Parent root2 = FXMLLoader.load(getClass().getResource("UserFXML.fxml"));
            Stage stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene3 = new Scene(root2 );
            stage2.setScene(scene3);
            stage2.show();
            }
        else 
        {
            label.setText("Incorrect Username or Password");
        }
        
    }
    }

    @FXML
    private void SignUp(ActionEvent event) throws IOException {
         Parent root2 = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Stage stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene3 = new Scene(root2 );
            stage2.setScene(scene3);
            stage2.show();
    }
    
        
    

    @FXML
    private void Exit(ActionEvent event) {
        System.exit(0);
    }
    
}
