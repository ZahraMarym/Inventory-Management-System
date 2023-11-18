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
public class Payment {
    private int paymentNo, orderId, paymentAmount, accNo, remainingAmount;
    private Date paymentDate;

    public Payment(int paymentNo, int orderId, int paymentAmount, int accNo, int remainingAmount, Date paymentDate) {
        this.paymentNo = paymentNo;
        this.orderId = orderId;
        this.paymentAmount = paymentAmount;
        this.accNo = accNo;
        this.remainingAmount = remainingAmount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentNo() {
        return this.paymentNo;
    }

    public void setPaymentNo(int paymentNo) {
        this.paymentNo = paymentNo;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getAccNo() {
        return this.accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public int getRemainingAmount() {
        return this.remainingAmount;
    }

    public void setRemainingAmount(int remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    
}
