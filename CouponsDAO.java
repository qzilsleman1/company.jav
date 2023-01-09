import java.util.ArrayList;
import java.util.Date;

public interface CouponsDAO {
    void addCoupon(Coupon coupon) throws InterruptedException;
    void updateCoupon(Coupon coupon) throws InterruptedException;
    void deleteCoupon(int couponID) throws InterruptedException;
    ArrayList<Coupon> getAllCoupons() throws InterruptedException;
    Coupon getOneCoupon(int couponID) throws InterruptedException;
    void addCouponPurchase(int customerID, int couponID) throws InterruptedException;
    void deleteCouponPurchase(int customerID, int couponID) throws InterruptedException;

    ArrayList<Coupon> getCompanyCoupons(int companyID) throws InterruptedException;

    void deleteCompanyCoupons(int companyID) throws InterruptedException;

    boolean isCouponPurchased(int customerID, int id) throws InterruptedException;

    ArrayList<Coupon> getCustomerCoupons(int customerID, double maxPrice);

    ArrayList<Coupon> getCustomerCoupons(int customerID, Category category);

    void deleteExpiredCoupons(Date currentTime);

    ArrayList<Coupon> getCustomerCoupons(int customerID) throws InterruptedException;
}