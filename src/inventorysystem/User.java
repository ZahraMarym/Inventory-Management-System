/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

/**
 *
 * @author Zahra Maryam
 */
public class User {
    private String firstName,lastName,email,userName,userPassword;
    private int userId;
    public User(int userId, String firstName,String lastName,String email,String userName,String userPassword){
        this.userId = userId;
        this.firstName  = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.userPassword = userPassword;
    }
    public void setUserId(int userId){
         this.userId = userId;
    }
    public void setFirstName(String firstName){
         this.firstName  = firstName;    }
    public void setLastName(String lastName){
       this.lastName = lastName;
    }
    public void setEmail(String email){
         this.email = email;
    }
    
    public void setUserName(String userName){
              this.userName = userName;
    }
    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }
    public int getUserId(){
        return this.userId;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getUserPassword(){
        return this.userPassword;
    }
}
