/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yumxpress.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import yumxpress.dbutill.DBConnection;
import yumxpress.pojo.CustomerPojo;
import yumxpress.pojo.OrderPojo;
import yumxpress.pojo.PlaceOrderPojo;
import yumxpress.pojo.StaffPojo;

/**
 *
 * @author Prashant yaduvanshi
 *
 */
public class OrderDAO {
    public static String getNewId()throws SQLException{
       Connection conn= DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(order_id) from orders");
        rs.next();
        String id=rs.getString(1);  
         String orderId="";
         if(id!=null){   
         id=id.substring(4);
         orderId ="ORD-"+(Integer.parseInt(id)+1);
         }else{
         orderId="ORD-101";       
         }
         return orderId;
    }
    
      public static String placeOrder(PlaceOrderPojo placeOrder )throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
        placeOrder.setOrderId(getNewId());
        ps.setString(1,placeOrder.getOrderId());
        ps.setString(2,placeOrder.getProductId());
        ps.setString(3, placeOrder.getCustomerId());
        ps.setString(4,placeOrder.getDeliveryStaffId());
        ps.setString(5,"");
        ps.setString(6,"ORDERED");
        ps.setString(7, placeOrder.getCompanyId());
        Random ran=new Random();
       int otp= ran.nextInt(1000);
       ps.setInt(8, otp);
     if (ps.executeUpdate() == 1) {
            return placeOrder.getOrderId();
        }
        return null;
    }
      
       public static OrderPojo getOrderDetailsByOrderId(String orderId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "SELECT c.customer_name, c.address, s.staff_name, c.mobile_no, co.company_name,co.email_id, p.product_name, p.product_price, o.otp "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN companies co ON o.company_id = co.company_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "JOIN staff s ON o.staff_id = s.staff_id "
                + "WHERE o.order_id = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, orderId);
        ResultSet rs = ps.executeQuery();
        OrderPojo order = null;
        if (rs.next()) {
            order = new OrderPojo();
            order.setOrderId(orderId);
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setCustomerPhoneNo(rs.getString("mobile_no"));
            order.setCompanyName(rs.getString("company_name"));
            order.setCompanyEmailId(rs.getString("email_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setOtp(rs.getInt("otp"));

        }
        return order;
    }
       
        public static List<OrderPojo> getNewOrdersForStaff(String staffId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "SELECT o.order_id, o.otp, p.product_name, p.product_price, c.customer_name, c.address, c.mobile_no "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "WHERE o.staff_id = ? "
                + "  AND o.status = 'ORDERED' "
                + "ORDER BY o.order_id DESC";

        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, staffId);
        ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order=null;
        while (rs.next()) {
            order = new OrderPojo();
            order.setOrderId(rs.getString("order_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setCustomerPhoneNo(rs.getString("mobile_no"));
            order.setOtp(rs.getInt("otp"));
            orderList.add(order);

        }
        return orderList;
    }
       
       public static boolean confirmOrder(String orderId)throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update orders set status='DELIVERED' where order_id=?");
        ps.setString(1, orderId);
        return ps.executeUpdate()==1;
       }
       
      
       
       
       
       
       
       
       
       
       public static List<OrderPojo> getOrdersInCartForCustomer(String custId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "SELECT o.order_id,o.company_id, o.otp, p.product_name, p.product_price, c.customer_name, c.address, c.mobile_no "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "WHERE o.customer_id = ? "
                + "  AND o.status = 'CART' "
                + "ORDER BY o.order_id DESC";

        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, custId);
        ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order=null;
        while (rs.next()) {
            order = new OrderPojo();
            order.setOrderId(rs.getString("order_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setCustomerPhoneNo(rs.getString("mobile_no"));
            order.setCompanyId(rs.getString("company_id"));
            order.setOtp(rs.getInt("otp"));
             //System.out.println(order);
            orderList.add(order);

        }
       
        return orderList;
    }
     
     public static String placeOrderInCart(PlaceOrderPojo placeOrder) throws SQLException {

        Connection conn = DBConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
        placeOrder.setOrderId(getNewId());
        ps.setString(1, placeOrder.getOrderId());
        ps.setString(2, placeOrder.getProductId());
        ps.setString(3, placeOrder.getCustomerId());
        ps.setString(4, "");
        ps.setString(5, "");
        ps.setString(6, "CART");
        ps.setString(7, placeOrder.getCompanyId());
        ps.setInt(8, 0);
        if (ps.executeUpdate() == 1) {
            return placeOrder.getOrderId();
        }
        return null;
    }
     public static int orderInCart(String orderId,String staffId)throws SQLException
     {
         Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update orders set status='ORDERED',staff_id=?,OTP=? where Order_id=?");
     
         Random rand = new Random();
        int otp = rand.nextInt(1000);
        
        ps.setString(1, staffId);
        ps.setInt(2, otp);
        ps.setString(3, orderId);
        ps.executeUpdate();
        return otp;
     }
     
     public static String[] getInformationCompanyStaff(String cmpId,String staffId)throws SQLException
     {
         Connection conn = DBConnection.getConnection();
         String qry="SELECT c.company_name,c.email_id, s.staff_name FROM companies c JOIN staff s ON c.company_id = s.company_id WHERE c.company_id = ? AND s.staff_id = ? ";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, cmpId);
        ps.setString(2,staffId);
        String [] details=new String[3];
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            details[0]=rs.getString("company_name");
            details[1]=rs.getString("email_id");
            details[2]=rs.getString("staff_name");
        }
        return details;
     }
     public static List<OrderPojo> getOrderHistoryByStaffId(String staffId)throws SQLException {
          Connection conn = DBConnection.getConnection();
         String qry="SELECT p.product_name, p.product_price,c.customer_name, c.address,o.review"
               +" FROM orders o JOIN products p ON o.product_id = p.product_id"
               +" JOIN staff s ON o.staff_id = s.staff_id"
               +" JOIN customers c ON o.customer_id = c.customer_id WHERE o.staff_id = ? "
               +" AND o.status = 'DELIVERED' ORDER BY o.order_id DESC";
         PreparedStatement ps = conn.prepareStatement(qry);
         
         ps.setString(1, staffId);
         ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderHistory = new ArrayList<>();
        OrderPojo order=null;
        while (rs.next()) {
            order = new OrderPojo();
            
           
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setReview(rs.getString("review"));
           
            orderHistory.add(order);

        }
         return orderHistory;
     }
     
      public static List<OrderPojo> getOrderListByCompanyId(String cmpId)throws SQLException
     {
          Connection conn = DBConnection.getConnection();
         String qry="SELECT p.product_name, p.product_price, c.customer_name,s.staff_name,c.address, o.review "
                 + "FROM orders o JOIN products p ON o.product_id = p.product_id "
                 + "JOIN staff s ON o.staff_id = s.staff_id "
                 + "JOIN customers c ON o.customer_id = c.customer_id WHERE o.company_id = ? "
                 + "AND o.status = 'DELIVERED' ORDER BY o.order_id DESC";
         PreparedStatement ps = conn.prepareStatement(qry);
         
         ps.setString(1, cmpId);
         ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderHistory = new ArrayList<>();
        OrderPojo order=null;
        while (rs.next()) {
            order = new OrderPojo();
            
           
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setReview(rs.getString("review"));
           
            orderHistory.add(order);

        }
         return orderHistory;
     }
     
      public static List<OrderPojo> getOrderDetailsForCancel(String customerId)throws SQLException
      {
           Connection conn = DBConnection.getConnection();
          String qry="SELECT o.order_id, p.product_name, p.product_price,s.staff_name, c.address" 
                  + " FROM orders o JOIN products p ON o.product_id = p.product_id"
                  + " JOIN staff s ON o.staff_id = s.staff_id"
                  + " JOIN customers c ON o.customer_id = c.customer_id WHERE o.customer_id = ? "
                  + " AND o.status = 'ORDERED' ORDER BY o.order_id DESC";
                  
          PreparedStatement ps = conn.prepareStatement(qry);
         
         ps.setString(1, customerId);
         ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order=null;
        while (rs.next()) {
            order = new OrderPojo();
            
            order.setOrderId(rs.getString("order_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setCustomerAddress(rs.getString("address"));
           
           
            orderList.add(order);

        }
         return orderList;
      }
      
      public static boolean orderCancelByCustomer(String ordId)throws SQLException
      {
           Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update orders set status='CANCELED' where Order_id=?");
        ps.setString(1, ordId);
        return ps.executeUpdate()==1;
     
      }
      
      public static List<OrderPojo> getOrderHistoryByCustomer(String customerId)throws SQLException
      {
          Connection conn = DBConnection.getConnection();
          String qry="SELECT o.order_id ,p.product_name, p.product_price,s.staff_name,cm.company_name, c.address, o.review"
                +" FROM orders o JOIN products p ON o.product_id = p.product_id"
                +" JOIN staff s ON o.staff_id = s.staff_id"
                +" JOIN companies cm ON o.company_id=cm.company_id"
                +" JOIN customers c ON o.customer_id = c.customer_id WHERE o.customer_id = ?"
                +" AND o.status = 'DELIVERED' ORDER BY o.order_id DESC";
          
                   
          PreparedStatement ps = conn.prepareStatement(qry);
         
         ps.setString(1, customerId);
         ResultSet rs = ps.executeQuery();
        List<OrderPojo> orderHistory = new ArrayList<>();
        OrderPojo order=null;
        while (rs.next()) {
            order = new OrderPojo();
            
            order.setOrderId(rs.getString("order_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setCompanyName(rs.getString("company_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setReview(rs.getString("review"));
           
           
            orderHistory.add(order);

        }
         return orderHistory;
      }
      
      public static boolean setReviewByCustomer(String ordId,String review)throws SQLException
      {
          Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update orders set review=? where Order_id=?");
        ps.setString(1, review);
        ps.setString(2, ordId);
        return ps.executeUpdate()==1;
      }
}
       
       
       
       

