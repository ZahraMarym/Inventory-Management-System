/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

/**
 *
 * @author Zahra Maryam
 */
public class Customer {
    private String custName,custAddress;
    private int custId, custPhone,custAccNo,userId;
    public Customer(int custId, String custName,String custAddress,int custPhone,int custAccNo,int userId){
        this.custId = custId;
        this.custName  = custName;
        this.custAddress = custAddress;
        this.custPhone = custPhone;
        this.custAccNo = custAccNo;
        this.userId = userId;
    }
    public void setCustId(int custId){
         this.custId = custId;
    }
    public void setCustName(String custName){
         this.custName  = custName;    }
    public void setCustAddress(String custAddress){
       this.custAddress = custAddress;
    }
    public void setCustPhone(int custPhone){
         this.custPhone = custPhone;
    }
    
    public void setCustAccNo(int custAccNo){
              this.custAccNo = custAccNo;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return this.userId;
    }
    public int getCustId(){
        return this.custId;
    }
    public int getCustAccNo(){
        return this.custAccNo;
    }
    public int getCustPhone(){
        return this.custPhone;
    }
    public String getCustName(){
        return this.custName;
    }
    public String getCustAddress(){
        return this.custAddress;
    }
}
