import java.sql.Date;
import java.util.ArrayList;

public class Test {
    public static void testAll() {
     // Start the daily job
      CouponsDAO couponsDAO = null;
        CouponExpirationDailyJob job = new CouponExpirationDailyJob(couponsDAO);
        Thread thread = new Thread(String.valueOf(job));
       thread.start();

        try {
            // Login as administrator and call business logic functions
            AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType .ADMIN);
            adminFacade.addCompany(new Company(123,"sskll@","ssss","hsjhskj",null));

            ArrayList<Company> companies = adminFacade.getAllCompanies();

            System.out.println(adminFacade.getAllCompanies());
            Company company = adminFacade.getOneCompany(1558);

            // Login as company and call business logic functions
            CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("company@email.com", "password", ClientType.COMPANY);

               companyFacade.addCoupon(new Coupon(22345,"c1", Date.valueOf("1-1-2023"),Date.valueOf("12-29-2030") , 5, Category.Food, "nothing", 74.05, "image"));
         companyFacade.updateCoupon(new Coupon(9908, "c2", Date.valueOf("6-2-2022"),Date.valueOf("2-17-2040") , 6, Category.Food, "message", 400, "image"));
            companyFacade.addCoupon(new Coupon(22222, "c2", Date.valueOf("6-12-2021"), Date.valueOf("1-13-2028"), 8, Category.Resturants, "nothing", 50.99, "image"));
            companyFacade.addCoupon(new Coupon(33333, "c3", Date.valueOf("30-10-2015"), Date.valueOf("12-3-2023"), 10, Category.Vacation, "nothing", 100.00, "image"));
            companyFacade.addCoupon(new Coupon(44444, "c4", Date.valueOf("25-11-2023"), Date.valueOf("2-27-2044"), 15, Category.Food, "nothing", 40.50, "image"));
            companyFacade.addCoupon(new Coupon(55555, "c5", Date.valueOf("23-12-2019"), Date.valueOf("5-3-2026"), 3, Category.Resturants, "nothing", 70.00, "image"));
            companyFacade.addCoupon(new Coupon(66666, "c6", Date.valueOf("29-4-2016"), Date.valueOf("7-9-2024"), 20, Category.Vacation, "nothing", 150.00, "image"));
            companyFacade.addCoupon(new Coupon(77777, "c7", Date.valueOf("16-7-2020"), Date.valueOf("26-2-2026"), 25, Category.Food, "nothing", 20.00, "image"));
            companyFacade.addCoupon(new Coupon(88888, "c8", Date.valueOf("31-7-2022"), Date.valueOf("1-11-2031"), 30, Category.Resturants, "nothing", 90.00, "image"));

            companyFacade.deleteCoupon(company.getId());
            ArrayList<Coupon> coupons = companyFacade.getCompanyCoupons();
            coupons = companyFacade.getCompanyCoupons(Category.Food);
            coupons = companyFacade.getCompanyCoupons();
            Company companyDetails = companyFacade.getCompanyDetails();

            // Login as customer and call business logic functions
            CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("customer@email.com", "password", ClientType.CUSTOMER);
            //customerFacade.purchaseCoupon(new Coupon(206703126, title, startDate, endDate, amount, category, message, price, image));
            coupons = customerFacade.getCustomerCoupons();
            coupons = customerFacade.getCustomerCoupons(Category.Food);
            coupons = customerFacade.getCustomerCoupons(100);
            Customer customerDetails = customerFacade.getCustomerDetails();
            // C. Login by LoginManager as a Company receive a CompanyFacade object and call each of its business logic functions
            companyFacade = (CompanyFacade) LoginManager.getInstance().login("company@company.com", "company", ClientType.COMPANY);

// Call business logic functions of the companyFacade object
            //companyFacade.addCoupon(new Coupon(id, title, startDate, endDate, amount, category, message, price, image));
            //.updateCoupon(new Coupon(id, title, startDate, endDate, amount, category, message, price, image));
            companyFacade.deleteCoupon(44444);
            ArrayList<Coupon> companyCoupons = companyFacade.getCompanyCoupons();
            companyCoupons = companyFacade.getCompanyCoupons(Category.Food);
            companyCoupons = companyFacade.getCompanyCoupons();
            companyDetails = companyFacade.getCompanyDetails();

// D. Login by the LoginManager as a Customer receive a CustomerFacade object and call each of its business logic functions
            customerFacade = (CustomerFacade) LoginManager.getInstance().login("customer@customer.com", "customer", ClientType.CUSTOMER);

// Call business logic functions of the customerFacade object
            //customerFacade.purchaseCoupon(new Coupon(id, title, startDate, endDate, amount, category, message, price, image));
            ArrayList<Coupon> customerCoupons = customerFacade.getCustomerCoupons();
            customerCoupons = customerFacade.getCustomerCoupons(Category.Food);
            customerCoupons = customerFacade.getCustomerCoupons(100.0);
            customerDetails = customerFacade.getCustomerDetails();

        } catch (Exception e) {
            // Display clear error message
            System.out.println(e.getMessage());
        }

        // Stop the daily job
       job.stop();

        // Close all connections
        ConnectionPool.getInstance().closeAllConnections();
    }
}
