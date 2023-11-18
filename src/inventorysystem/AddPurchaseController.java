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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class AddPurchaseController implements Initializable {

    @FXML
    private TextField txtPid;
    @FXML
    private TextField txtPQuantity;
    @FXML
    private TextField txtCustId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
         if(txtPid.getText().equals("") || txtPQuantity.getText().equals("") || txtCustId.getText().equals("")){
            Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Invalid Fields");
            alt.setContentText("Enter all data in Fields");
        alt.show();}
        else{
        Add();
        txtPid.setText("");
        txtPQuantity.setText("");
        txtCustId.setText("");}
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
        String s = "INSERT INTO `inventory`.`cust_prod` (`custId`, `prodId`, `pQauantity`) VALUES ('"+txtCustId.getText()+"', '"+txtPid.getText()+"', '"+txtPQuantity.getText()+"');";
        executeQuerey(s);
        inventorysystem.PurchasesController.stage2.close();
        
    }
    @FXML
    private void exit(ActionEvent event) {
                inventorysystem.PurchasesController.stage2.close();

    }
    
}
