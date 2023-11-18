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
import java.util.Date;
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
public class PaymentController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableColumn<Payment, Integer> PayNo;
    @FXML
    private TableView<Payment> tblPayment;
        public static Stage stage2;
    @FXML
    private TableColumn<Payment, Float> paymentAmount;
    @FXML
    private TableColumn<Payment, Integer> accNo;
    @FXML
    private TableColumn<Payment, Date> paymentDate;
    @FXML
    private TableColumn<Payment, Float> remainingAmount;
    @FXML
    private TableColumn<Payment, Integer> OrderId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
private ObservableList<Payment> getList() throws SQLException{
            ObservableList<Payment> oblist = FXCollections.observableArrayList();
            Connection con = connection.getConnection();
            String querey = "SELECT * FROM inventory.payment;";
            Statement ps;
            ResultSet rs;
            ps = con.createStatement();
            rs = ps.executeQuery(querey);
            Payment mc ;
            while(rs.next()){
               
                    mc = new Payment(rs.getInt("paymentNo"), rs.getInt("orderId"),rs.getInt("paymentAmount"), rs.getInt("accNo"), rs.getInt("remainingAmount"), rs.getDate("paymentDate"));
                    oblist.add(mc);
                } 
            return oblist;
        }
    private void showTable() throws SQLException{
        ObservableList<Payment> oblist = getList();
        PayNo.setCellValueFactory(new PropertyValueFactory<> ("paymentNo"));
        OrderId.setCellValueFactory(new PropertyValueFactory<> ("orderId"));
        paymentAmount.setCellValueFactory(new PropertyValueFactory<> ("paymentAmount"));
        accNo.setCellValueFactory(new PropertyValueFactory<> ("accNo"));
        paymentDate.setCellValueFactory(new PropertyValueFactory<> ("remainingAmount"));
        remainingAmount.setCellValueFactory(new PropertyValueFactory<> ("paymentDate"));
        tblPayment.setItems(oblist);
        FilteredList<Payment> filterData = new FilteredList<>(oblist, b->true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filterData.setPredicate(Payment -> {
                String searchWord = newValue.toLowerCase();
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(Payment.getPaymentDate().toString().indexOf(searchWord)>-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<Payment> sorted = new SortedList<>(filterData);
        sorted.comparatorProperty().bind(tblPayment.comparatorProperty());
        tblPayment.setItems(sorted);
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
        Parent root2 = FXMLLoader.load(getClass().getResource("AddPayment.fxml"));
        stage2 = new Stage();
        Scene scene3 = new Scene(root2 );
        stage2.setScene(scene3);
        stage2.show();
    }

    @FXML
    private void refresh(ActionEvent event) throws SQLException {
        showTable();
    }
   
    
}
