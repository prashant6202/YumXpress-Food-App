
package yumxpress.pojo;

/**
 *
 * @author Prashant yaduvanshi
 */
public class CustomerPojo {
    private String customerId;
    private String customerName;
    private String emailId;
    private String password;
    private String mobileNo;
    private String address;

    public CustomerPojo(String cumtomerId, String customerName, String emaidId, String password, String mobileNo, String address) {
        this.customerId = cumtomerId;
        this.customerName = customerName;
        this.emailId = emaidId;
        this.password = password;
        this.mobileNo = mobileNo;
        this.address = address;
    }
    public CustomerPojo(){
        
     }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
