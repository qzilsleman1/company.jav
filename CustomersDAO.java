import java.util.ArrayList;

public interface CustomersDAO {
    boolean isCustomerExists(String email, String password) throws InterruptedException;
    void addCustomer(Customer customer) throws InterruptedException;
    void updateCustomer(Customer customer) throws InterruptedException;
    void deleteCustomer(int customerID) throws InterruptedException;
    ArrayList<Customer> getAllCustomers() throws InterruptedException;
    Customer getOneCustomer(int customerID) throws InterruptedException;

    ArrayList<Coupon> getCustomerCoupons(int customerID) throws InterruptedException;

    Customer getOneCustomer(String email, String password) throws InterruptedException;
}


