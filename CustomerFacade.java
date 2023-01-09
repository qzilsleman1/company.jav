import java.sql.Date;
import java.util.ArrayList;
import java.sql.SQLException;

public class CustomerFacade extends ClientFacade {
    private int customerID;

    public CustomerFacade(ConnectionPool connectionPool, int customerID) {
        super(connectionPool);
        this.customerID = customerID;
    }

    public CustomerFacade() {

    }

    @Override
    public boolean login(String email, String password) throws InterruptedException {
        // Check login credentials against database
        return customersDAO.isCustomerExists(email, password);
    }

    public void purchaseCoupon(Coupon coupon) throws Exception {
        // Check if coupon with given ID exists
        Coupon oldCoupon = couponsDAO.getOneCoupon(coupon.getId());
        if (oldCoupon == null) {
            throw new Exception("Coupon with given ID does not exist");
        }

        // Check if customer has already purchased this coupon
        if (couponsDAO.isCouponPurchased(customerID, coupon.getId())) {
            throw new Exception("Customer has already purchased this coupon");
        }

        // Check if coupon is still in stock
        if (oldCoupon.getAmount() == 0) {
            throw new Exception("Coupon is out of stock");
        }

        // Check if coupon expiration date has passed
        if (oldCoupon.getEndDate().before(new Date(28-12-2022))) {
            throw new Exception("Coupon has expired");
        }

        // Purchase coupon and update coupon in database
        couponsDAO.addCouponPurchase(customerID, coupon.getId());
        oldCoupon.setAmount(oldCoupon.getAmount() - 1);
        couponsDAO.updateCoupon(oldCoupon);
    }

    public ArrayList<Coupon> getCustomerCoupons() {
        Category category = null;
        return couponsDAO.getCustomerCoupons(customerID, category);
    }

    public ArrayList<Coupon> getCustomerCoupons(Category category) {
        return couponsDAO.getCustomerCoupons(customerID, category);
    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) {
        return couponsDAO.getCustomerCoupons(customerID, maxPrice);
    }

    public Customer getCustomerDetails() throws InterruptedException {
        return customersDAO.getOneCustomer(customerID);
    }
}
