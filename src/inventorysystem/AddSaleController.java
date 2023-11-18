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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class AddSaleController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button exit;
    @FXML
    private TextField txtDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
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
        String s = "INSERT INTO `inventory`.`sales` (`profit`, `produstSaleDate`) VALUES ('43', '"+txtDate.getText()+"');;";
        executeQuerey(s);
        inventorysystem.SalesController.stage2.close();
        
    }

    @FXML
    private void exit(ActionEvent event) {
         inventorysystem.SalesController.stage2.close();
    }
    
}
