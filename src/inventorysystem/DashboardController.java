/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inventorysystem;


import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;/**
 * FXML Controller class
 *
 * @author Zahra Maryam
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private Label TOTALPURCHASE;
    @FXML
    private Label TOTALSALES;
    @FXML
    private Label TOTALPROFIT;
    @FXML
    private Label CUSTOMERS;
    @FXML
    private Label SALESINVOICES;
    @FXML
    private Label purchase;
    @FXML
    private Label sales;
    @FXML
    private Label profit;
    @FXML
    private Label cust;
    @FXML
    private Label salesinv;

    /**
     * Initializes the controller class.
     */
  
     public void chart(){
        String chartSql="SELECT cMonth,SUM(cProfit) from chart GROUP BY cMonth ORDER BY TIMESTAMP(cMonth) ASC";
        Connection connect=connection.getConnection();
        try{
            XYChart.Series chartData = new XYChart.Series();
            PreparedStatement prepare = connect.prepareStatement(chartSql);
            ResultSet result=prepare.executeQuery();
            while (result.next())
            {
                chartData.getData().add(new XYChart.Data(result.getString(1),result.getDouble(2)) );
            }
            barChart.getData().add(chartData);
    }
    catch(Exception e){
    e.printStackTrace();
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {         
        try {
            chart();
            String querey1 = "select count(custId) as NoOfCust from customers;";
            Connection conn = connection.getConnection();
            Statement ps1;
            ResultSet rs1;
            ps1 = conn.createStatement();
            rs1 = ps1.executeQuery(querey1);
            while(rs1.next()){
                String s1 = String.valueOf(rs1.getInt("NoOfCust"));
                cust.setText(s1);
            }
            rs1.close();
            ps1.close();
            String querey2 = "select count(saleNo) from sales;";
            Statement ps2;
            ResultSet rs2;
            ps2 = conn.createStatement();
            rs2 = ps2.executeQuery(querey2);
            while(rs2.next()){
                String s2 = String.valueOf(rs2.getInt(1));
                salesinv.setText(s2);
            }
            System.out.println(querey2);
            rs2.close();
            ps2.close();
            String querey3 = "select sum(retailPrice) from products;";
            Statement ps3;
            ResultSet rs3;
            ps3 = conn.createStatement();
            rs3 = ps3.executeQuery(querey3);
            while(rs3.next()){
                String s3 = String.valueOf(rs3.getFloat(1));
                purchase.setText("$"+s3);
            }
            System.out.println(querey3);
            rs3.close();
            ps3.close();
            String querey4 = "select sum(pAmount) from cust_prod;";
            Statement ps4;
            ResultSet rs4;
            ps4 = conn.createStatement();
            rs4 = ps4.executeQuery(querey4);
            while(rs4.next()){
                String s4 = String.valueOf(rs4.getFloat(1));
                sales.setText("$"+s4);
            }
            System.out.println(querey4);
            rs4.close();
            ps4.close();
            String querey5 = "select sum(profit) from sales;";
            Statement ps5;
            ResultSet rs5;
            ps5 = conn.createStatement();
            rs5 = ps5.executeQuery(querey5);
            while(rs5.next()){
                String s5 = String.valueOf(rs5.getFloat(1));
                profit.setText("$"+s5);
            }
            System.out.println(querey5);
            rs5.close();
            ps5.close();
        } catch (SQLException ex) {
            System.out.println(ex);        }
    }    
}

