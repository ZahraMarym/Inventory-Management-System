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
public class AddProductController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TextField txtRPrice;
    @FXML
    private TextField txtSPrice;
    @FXML
    private TextField txtCaTId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
        if(txtName.getText().equals("") || txtQuantity.getText().equals("") || txtRPrice.getText().equals("") || txtSPrice.getText().equals("") || txtCaTId.getText().equals("")){
            Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Invalid Fields");
            alt.setContentText("Enter all data in Fields");
        alt.show();}
        else{
        Add();
        txtName.setText("");
        txtQuantity.setText("");
        txtRPrice.setText("");
        txtSPrice.setText("");
        txtCaTId.setText("");}
    }
    private void Add()  {
        String s = "INSERT INTO `inventory`.`products` (`catId`, `productName`, `productQty`, `retailPrice`, `salePrice`) VALUES ('"+txtCaTId.getText()+"', '"+txtName.getText()+"', '"+txtQuantity.getText()+"', '"+txtRPrice.getText()+"', '"+txtSPrice.getText()+"');";
        executeQuerey(s);
        inventorysystem.ProductsController.stage2.close();
        
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
    @FXML
    private void exit(ActionEvent event) {
                inventorysystem.ProductsController.stage2.close();
    }
    
}
