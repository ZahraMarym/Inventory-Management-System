/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package inventorysystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Zahra Maryam
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private BorderPane Border;
    @FXML
    private Label btnLabel;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnCtg;
    @FXML
    private Button btnPdt;
    @FXML
    private Button btnPur;
    @FXML
    private Button btnSale;
    @FXML
    private Button btnCust;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnPayment;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Border.setCenter(root);
    }    


      @FXML
    private void dashboard(MouseEvent event) throws IOException {
        loadPage("dashboard");
    }
    private void loadPage(String s) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(s+".fxml"));
        Border.setCenter(root);
        
    }

    @FXML
    private void categories(MouseEvent event) throws IOException {
        loadPage("catogeries");
    }

    @FXML
    private void products(MouseEvent event) throws IOException {
        loadPage("products");
    }

    @FXML
    private void purchases(MouseEvent event) throws IOException {
        loadPage("purchases");
    }

    @FXML
    private void sales(MouseEvent event) throws IOException {
        loadPage("sales");
    }

    @FXML
    private void Customer(MouseEvent event) throws IOException {
        loadPage("Customer");
    }

    @FXML
    private void User(MouseEvent event) throws IOException {
        loadPage("User");
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene3 = new Scene(root2 );
            stage2.setScene(scene3);
            stage2.show();
    }

    @FXML
    private void Payment(MouseEvent event) throws IOException {
        loadPage("payment");
    }
}
