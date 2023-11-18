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
public class CatogeriesController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnRem;
    @FXML
    private TableColumn<Categories, Integer> CategoryId;
    @FXML
    private TableColumn<Categories, String> CategoryName;
    @FXML
    private TableView<Categories> tblCat;
        public static Stage stage2;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(CatogeriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Add(MouseEvent event) throws IOException {
         Parent root2 = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));
        stage2 = new Stage();
        Scene scene3 = new Scene(root2 );
        stage2.setScene(scene3);
        stage2.show();
    }

    private ObservableList<Categories> getList() throws SQLException{
            ObservableList<Categories> oblist = FXCollections.observableArrayList();
            Connection con = connection.getConnection();
            String querey = "SELECT * FROM inventory.categories;";
            Statement ps;
            ResultSet rs;
            ps = con.createStatement();
            rs = ps.executeQuery(querey);
            Categories mc ;
            while(rs.next()){
               
                    mc = new Categories(rs.getInt("catId"), rs.getString("catName"));
                    oblist.add(mc);
                } 
            return oblist;
        }
    private void showTable() throws SQLException{
        ObservableList<Categories> oblist = getList();
        CategoryId.setCellValueFactory(new PropertyValueFactory<> ("catId"));
        CategoryName.setCellValueFactory(new PropertyValueFactory<> ("catName"));
        tblCat.setItems(oblist);
         FilteredList<Categories> filterData = new FilteredList<>(oblist, b->true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filterData.setPredicate(Categories -> {
                String searchWord = newValue.toLowerCase();
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(Categories.getCatName().toLowerCase().indexOf(searchWord)> -1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<Categories> sorted = new SortedList<>(filterData);
        sorted.comparatorProperty().bind(tblCat.comparatorProperty());
        tblCat.setItems(sorted);
    }
    private void executeQuerey(String querey) {
       Connection conn = connection.getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(querey);
        }catch(Exception ex){
           Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Error");
            alt.setContentText("Data cannot be Deleted");
            alt.show();
        }
    }   
    int index, id;
    @FXML
    private void remove(ActionEvent event) throws SQLException {
        index = tblCat.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tblCat.getItems().get(index).getCatId()));
       
        String s = "DELETE FROM `inventory`.`categories` WHERE (`catId` = '"+id+"');";
        executeQuerey(s);
   showTable();
    }
    

    @FXML
    private void refresh(ActionEvent event) throws SQLException {
     showTable();
    }
   
}
