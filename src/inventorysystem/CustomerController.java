/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inventorysystem;

import static inventorysystem.UserController.stage2;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class CustomerController implements Initializable {

    @FXML
    private TableView<Customer> tblCust;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableColumn<Customer, Integer> CustId;
    @FXML
    private TableColumn<Customer, String> CustName;
    @FXML
    private TableColumn<Customer, String> CustAddress;
    @FXML
    private TableColumn<Customer, Integer> CustPhone;
    @FXML
    private TableColumn<Customer, Integer> CustomerAccNo;
    @FXML
    private TableColumn<Customer, Integer> CustomerUserId;
   public static Stage stage2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private ObservableList<Customer> getList() throws SQLException{
            ObservableList<Customer> oblist = FXCollections.observableArrayList();
            Connection con = connection.getConnection();
            String querey = "SELECT * FROM inventory.customers;";
            Statement ps;
            ResultSet rs;
            ps = con.createStatement();
            rs = ps.executeQuery(querey);
            Customer mc ;
            while(rs.next()){
               
                    mc = new Customer(rs.getInt("custId"), rs.getString("custName"),rs.getString("custAddress"), rs.getInt("custPhone"), rs.getInt("custAccNo"), rs.getInt("userId"));
                    oblist.add(mc);
                } 
            return oblist;
        }
    private void showTable() throws SQLException{
        ObservableList<Customer> oblist = getList();
        CustId.setCellValueFactory(new PropertyValueFactory<> ("custId"));
        CustName.setCellValueFactory(new PropertyValueFactory<> ("custName"));
        CustAddress.setCellValueFactory(new PropertyValueFactory<> ("custAddress"));
        CustPhone.setCellValueFactory(new PropertyValueFactory<> ("custPhone"));
        CustomerAccNo.setCellValueFactory(new PropertyValueFactory<> ("custAccNo"));
        CustomerUserId.setCellValueFactory(new PropertyValueFactory<> ("userId"));
        tblCust.setItems(oblist);
        FilteredList<Customer> filterData = new FilteredList<>(oblist, b->true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filterData.setPredicate(Customer -> {
                String searchWord = newValue.toLowerCase();
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(Customer.getCustName().toLowerCase().indexOf(searchWord)> -1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<Customer> sorted = new SortedList<>(filterData);
        sorted.comparatorProperty().bind(tblCust.comparatorProperty());
        tblCust.setItems(sorted);
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
    private void Add(MouseEvent event) throws IOException {
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("AddCust.fxml"));
            stage2 = new Stage();
            Scene scene3 = new Scene(root2 );
            stage2.setScene(scene3);
            stage2.show();
            showTable();
        } catch (SQLException ex) {
             Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Invalid");
            alt.setContentText("Invalid");
        alt.show();
        }
        
    }

    @FXML
    private void refresh(ActionEvent event) throws SQLException {
        showTable();
    }

    
}
