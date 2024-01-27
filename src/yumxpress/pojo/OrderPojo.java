/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yumxpress.pojo;

import java.awt.Image;

/**
 *
 * @author Prashant yaduvanshi
 */
public class OrderPojo {
    private String orderId;
    private String companyId;
    private String companyName;
    private String companyEmailId;
    private String productName;
    private double productPrice;
    private Image  productImage;
    private String customerName;
    private String customerPhoneNo;
    private String customerAddress;
    private String deliveryStaffName;
    private String status;
    private String review;
    private int otp;

    public OrderPojo(String orderId, String companyId, String companyName, String companyEmailId, String productName, double productPrice, Image productImage, String customerName, String customerPhoneNo, String customerAddress, String deliveryStaffName, String status, String review, int otp) {
        this.orderId = orderId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyEmailId = companyEmailId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.customerName = customerName;
        this.customerPhoneNo = customerPhoneNo;
        this.customerAddress = customerAddress;
        this.deliveryStaffName = deliveryStaffName;
        this.status = status;
        this.review = review;
        this.otp = otp;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyEmailId() {
        return companyEmailId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public Image getProductImage() {
        return productImage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getDeliveryStaffName() {
        return deliveryStaffName;
    }

    public String getStatus() {
        return status;
    }

    public String getReview() {
        return review;
    }

    public int getOtp() {
        return otp;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyEmailId(String companyEmailId) {
        this.companyEmailId = companyEmailId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductImage(Image productImage) {
        this.productImage = productImage;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setDeliveryStaffName(String deliveryStaffName) {
        this.deliveryStaffName = deliveryStaffName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "OrderPojo{" + "orderId=" + orderId + ", companyId=" + companyId + ", companyName=" + companyName + ", companyEmailId=" + companyEmailId + ", productName=" + productName + ", productPrice=" + productPrice + ", productImage=" + productImage + ", customerName=" + customerName + ", customerPhoneNo=" + customerPhoneNo + ", customerAddress=" + customerAddress + ", deliveryStaffName=" + deliveryStaffName + ", status=" + status + ", review=" + review + ", otp=" + otp + '}';
    }

    public OrderPojo() {
    }
    
   
    
     
}
