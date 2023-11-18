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
public class AddPaymentController implements Initializable {

    @FXML
    private TextField txtOrderId;
    @FXML
    private TextField txtPayAM;
    @FXML
    private TextField txtAccNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
         if(txtOrderId.getText().equals("") || txtPayAM.getText().equals("") || txtAccNo.getText().equals("")){
            Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Invalid Fields");
            alt.setContentText("Enter all data in Fields");
        alt.show();}
        else{
        Add();
        txtOrderId.setText("");
        txtPayAM.setText("");
        txtAccNo.setText("");
    }
    }
    @FXML
    private void exit(ActionEvent event) {
        inventorysystem.PaymentController.stage2.close();

    }
     private void Add()  {
        String s = "INSERT INTO `inventory`.`payment` (`orderId`, `paymentAmount`, `accNo`) VALUES ('"+txtOrderId.getText()+"', '"+txtPayAM.getText()+"', '"+txtAccNo.getText()+"');";
        executeQuerey(s);
        inventorysystem.PaymentController.stage2.close();
        
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
}
