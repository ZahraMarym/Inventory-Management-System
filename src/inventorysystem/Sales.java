/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import java.util.Date;

/**
 *
 * @author Zahra Maryam
 */
public class Sales {
    private int saleNo;
    private float profit;
    private Date produstSaleDate;

    public Sales(int saleNo, float profit, Date produstSaleDate) {
        this.saleNo = saleNo;
        this.profit = profit;
        this.produstSaleDate = produstSaleDate;
    }

    public int getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(int saleNo) {
        this.saleNo = saleNo;
    }
    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public Date getProdustSaleDate() {
        return produstSaleDate;
    }

    public void setProdustSaleDate(float saleAmount) {
        this.produstSaleDate = produstSaleDate;
    }
    
}
