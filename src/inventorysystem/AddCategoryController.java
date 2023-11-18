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
public class AddCategoryController implements Initializable {

    @FXML
    private TextField txtName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
          if(txtName.getText().equals("")){
            Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Invalid Fields");
            alt.setContentText("Enter all data in Fields");
        alt.show();}
        else{
        Add();
        txtName.setText("");
        }
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
        String s = "INSERT INTO `inventory`.`categories` (`catName`) VALUES ('"+txtName.getText()+"');";
        executeQuerey(s);
        inventorysystem.CatogeriesController.stage2.close();
        
    }

    @FXML
    private void exit(ActionEvent event) {
                inventorysystem.CatogeriesController.stage2.close();

    }
    
}
