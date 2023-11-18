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
public class SalesController implements Initializable {

    @FXML
    private TableView<Sales> tblSales;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableColumn<Sales, Integer> saleNo;
    @FXML
    private TableColumn<Sales, Float> profit;
    @FXML
    private TableColumn<Sales, Date> produstSaleDate;
    public static Stage stage2;
    @FXML
    private Button refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
private ObservableList<Sales> getList() throws SQLException{
            ObservableList<Sales> oblist = FXCollections.observableArrayList();
            Connection con = connection.getConnection();
            String querey = "SELECT * FROM inventory.sales;";
            Statement ps;
            ResultSet rs;
            ps = con.createStatement();
            rs = ps.executeQuery(querey);
            Sales mc ;
            while(rs.next()){
               
                    mc = new Sales(rs.getInt("saleNo"), rs.getFloat("profit"), rs.getDate("produstSaleDate"));
                    oblist.add(mc);
                } 
            return oblist;
        }
    private void showTable() throws SQLException{
        ObservableList<Sales> oblist = getList();
        saleNo.setCellValueFactory(new PropertyValueFactory<> ("saleNo"));
        profit.setCellValueFactory(new PropertyValueFactory<> ("profit"));
        produstSaleDate.setCellValueFactory(new PropertyValueFactory<> ("produstSaleDate"));
        tblSales.setItems(oblist);
         FilteredList<Sales> filterData = new FilteredList<>(oblist, b->true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filterData.setPredicate(Sales -> {
                String searchWord = newValue.toLowerCase();
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(Sales.getProdustSaleDate().toString().indexOf(searchWord)>-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<Sales> sorted = new SortedList<>(filterData);
        sorted.comparatorProperty().bind(tblSales.comparatorProperty());
        tblSales.setItems(sorted);
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
        Parent root2 = FXMLLoader.load(getClass().getResource("AddSale.fxml"));
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
