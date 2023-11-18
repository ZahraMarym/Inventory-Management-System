/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inventorysystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class UserController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnRem;
    @FXML
    private Button btnEdit;
    @FXML
    private TableColumn<User, Integer> UserId;
    @FXML
    private TableColumn<User, String> FirstName;
    @FXML
    private TableColumn<User, String> LastName;
    @FXML
    private TableColumn<User, String> Username;
    @FXML
    private TableColumn<User, String> Email;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableView<User> tblUser;
    public static Stage stage2;
        public static Stage stage3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Add(MouseEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
        stage2 = new Stage();
        Scene scene3 = new Scene(root2 );
        stage2.setScene(scene3);
        stage2.show();
        
    }

    private ObservableList<User> getList() throws SQLException{
            ObservableList<User> oblist = FXCollections.observableArrayList();
            Connection con = connection.getConnection();
            String querey = "SELECT * FROM inventory.users;";
            Statement ps;
            ResultSet rs;
            ps = con.createStatement();
            rs = ps.executeQuery(querey);
            User mc ;
            while(rs.next()){
               
                    mc = new User(rs.getInt("userId"), rs.getString("firstName"),rs.getString("lastName"), rs.getString("email"), rs.getString("userName"), rs.getString("userPassword"));
                    oblist.add(mc);
                } 
            return oblist;
        }
    public void showTable() throws SQLException{
        ObservableList<User> oblist = getList();
        UserId.setCellValueFactory(new PropertyValueFactory<> ("userId"));
        FirstName.setCellValueFactory(new PropertyValueFactory<> ("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<> ("lastName"));
        Username.setCellValueFactory(new PropertyValueFactory<> ("userName"));
        Email.setCellValueFactory(new PropertyValueFactory<> ("email"));
        password.setCellValueFactory(new PropertyValueFactory<> ("userPassword"));
        tblUser.setItems(oblist);
        FilteredList<User> filterData = new FilteredList<>(oblist, b->true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filterData.setPredicate(User -> {
                String searchWord = newValue.toLowerCase();
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(User.getFirstName().toLowerCase().indexOf(searchWord)> -1)
                    return true;
                else if(User.getUserName().toLowerCase().indexOf(searchWord)> -1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<User> sorted = new SortedList<>(filterData);
        sorted.comparatorProperty().bind(tblUser.comparatorProperty());
        tblUser.setItems(sorted);
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
    int id, index;
    @FXML
    private void remove(ActionEvent event) throws SQLException {
        
        index = tblUser.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tblUser.getItems().get(index).getUserId()));
       
        String s = "DELETE FROM `inventory`.`users` WHERE (`userId` = '"+id+"');";
        executeQuerey(s);
   showTable();
    }


    @FXML
    private void refresh(ActionEvent event) throws SQLException {
        showTable();
    }
    @FXML
    private void edit(ActionEvent event) throws SQLException, IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("editUser.fxml"));
        stage3 = new Stage();
        Scene scene3 = new Scene(root2 );
        stage3.setScene(scene3);
        stage3.show();
    }
   
    
}
