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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class PurchasesController implements Initializable {

    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<purchases> tblPurchases;
 
    @FXML
    private TableColumn<purchases, Integer> orderId;
    @FXML
    private TableColumn<purchases, Integer> custId;
    @FXML
    private TableColumn<purchases, Date> orderDate;
    @FXML
    private AnchorPane add;
        public static Stage stage2;
    private Label lbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(PurchasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void Add(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddPurchase.fxml"));
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);
        stage1.setTitle("Add a new category");
        stage1.setScene(scene);
        stage1.show();
    }
    private ObservableList<purchases> getList() throws SQLException{
            ObservableList<purchases> oblist = FXCollections.observableArrayList();
            Connection con = connection.getConnection();
            String querey = "SELECT * FROM inventory.orders;";
            Statement ps;
            ResultSet rs;
            ps = con.createStatement();
            rs = ps.executeQuery(querey);
            purchases mc ;
            while(rs.next()){
               
                    mc = new purchases(rs.getInt("orderId"), rs.getInt("custId"), rs.getDate("orderDate"));
                    oblist.add(mc);
                } 
            return oblist;
        }
    private void showTable() throws SQLException{
        ObservableList<purchases> oblist = getList();
        orderId.setCellValueFactory(new PropertyValueFactory<> ("orderId"));
        custId.setCellValueFactory(new PropertyValueFactory<> ("custId"));
        orderDate.setCellValueFactory(new PropertyValueFactory<> ("orderDate"));
        tblPurchases.setItems(oblist);
         FilteredList<purchases> filterData = new FilteredList<>(oblist, b->true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filterData.setPredicate(purchases -> {
                String searchWord = newValue.toLowerCase();
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(purchases.getOrderDate().toString().indexOf(searchWord)>-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<purchases> sorted = new SortedList<>(filterData);
        sorted.comparatorProperty().bind(tblPurchases.comparatorProperty());
        tblPurchases.setItems(sorted);
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
    private void add(MouseEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AddPurchase.fxml"));
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
