/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

/**
 *
 * @author Zahra Maryam
 */
public class Categories {
    private int catId;
    private String catName;
    public Categories(int catId, String catName){
        this.catId = catId;
        this.catName = catName;
    }
    public int getCatId(){
        return this.catId;
    }
    public String getCatName(){
        return this.catName;
    }
    public void setCatId(int catId){
        this.catId = catId;
    }
    public void setCatName(String catName){
        this.catName = catName;
    }
}
