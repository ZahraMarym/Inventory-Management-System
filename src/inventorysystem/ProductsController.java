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
public class ProductsController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnRem;
    @FXML
    private TableColumn<products, Integer> ProductId;
    @FXML
    private TableColumn<products, String> ProductName;
    @FXML
    private TableColumn<products, Integer> Quantity;
    @FXML
    private TableColumn<products, Float> RetailPrice;
    @FXML
    private TableColumn<products, Float> SalePrice;
    @FXML
    private TableColumn<products, Integer> CategoryID;
    @FXML
    private TableView<products> tblProd;
        public static Stage stage2;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    private ObservableList<products> getList() throws SQLException{
            ObservableList<products> oblist = FXCollections.observableArrayList();
            Connection con = connection.getConnection();
            String querey = "SELECT * FROM inventory.products;";
            Statement ps;
            ResultSet rs;
            ps = con.createStatement();
            rs = ps.executeQuery(querey);
            products mc ;
            while(rs.next()){
               
                    mc = new products(rs.getInt("productId"), rs.getInt("catId"),rs.getInt("productQty"), rs.getString("productName"), rs.getFloat("retailPrice"), rs.getFloat("salePrice"));
                    oblist.add(mc);
                } 
            return oblist;
        }
    private void showTable() throws SQLException{
        ObservableList<products> oblist = getList();
        ProductId.setCellValueFactory(new PropertyValueFactory<> ("productId"));
        ProductName.setCellValueFactory(new PropertyValueFactory<> ("productName"));
        Quantity.setCellValueFactory(new PropertyValueFactory<> ("productQty"));
        RetailPrice.setCellValueFactory(new PropertyValueFactory<> ("retailPrice"));
        SalePrice.setCellValueFactory(new PropertyValueFactory<> ("salePrice"));
        CategoryID.setCellValueFactory(new PropertyValueFactory<> ("catId"));
        tblProd.setItems(oblist);
         FilteredList<products> filterData = new FilteredList<>(oblist, b->true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filterData.setPredicate(products -> {
                String searchWord = newValue.toLowerCase();
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(products.getProductName().toLowerCase().indexOf(searchWord)> -1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<products> sorted = new SortedList<>(filterData);
        sorted.comparatorProperty().bind(tblProd.comparatorProperty());
        tblProd.setItems(sorted);
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
    private void refresh(ActionEvent event) throws SQLException {
        showTable();
    }

    @FXML
    private void Add(MouseEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        stage2 = new Stage();
        Scene scene3 = new Scene(root2 );
        stage2.setScene(scene3);
        stage2.show();
    }
    
}
