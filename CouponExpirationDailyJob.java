import java.sql.Date;

public class CouponExpirationDailyJob implements Runnable {
    private final CouponsDAO couponsDAO;
    private boolean quit;

    public CouponExpirationDailyJob(CouponsDAO couponsDAO) {
        this.couponsDAO = couponsDAO;
        this.quit = false;
    }

    @Override
    public void run() {
        while (!quit) {
            // Get current time
            Date currentTime = new Date(01-01-2019) ;

            // Delete expired coupons from database
            couponsDAO.deleteExpiredCoupons(currentTime);

            // Sleep for 24 hours
            try {
                Thread.sleep(24 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.quit = true;
    }
}
