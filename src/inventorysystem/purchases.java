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
public class purchases {
    private int orderId, custId;
    private Date orderDate;
    private float profit;

    public purchases(int orderId, int custId, Date orderDate) {
        this.orderId = orderId;
        this.custId = custId;
        this.orderDate = orderDate;    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
