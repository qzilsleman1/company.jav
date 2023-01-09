import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class CompanyFacade extends ClientFacade {
    private int companyId;
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public CompanyFacade(String email) {
        companiesDAO = new CompaniesDBDAO();
        customersDAO = new CustomersDBDAO();
        couponsDAO = new CouponsDBDAO();
        ArrayList<Company> companies = null;
        try {
            companies = companiesDAO.getAllCompanies();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Company company : companies) {
            if (company.getEmail().equals(email)) {
                companyId = company.getId();
            }
        }
    }

    public CompanyFacade() {

    }

    @Override
    public boolean login(String email, String password) {
        // Check login credentials against hard-coded values

        //email.equals("admin@admin.com") && passworreturn.equals("admin");

        try {
            if (companiesDAO.isCompanyExists(email, password)) {
                Company company = companiesDAO.getOneCompany(email, password);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addCoupon(Coupon coupon) {
        try {
            couponsDAO.addCoupon(coupon);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCoupon(Coupon coupon) {

        try {
            couponsDAO.updateCoupon(coupon);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteCoupon(int couponId) {
        try {
            couponsDAO.deleteCoupon(couponId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Coupon> getCompanyCoupons() {
        ArrayList<Coupon> coups = null;
        try {
            coups = couponsDAO.getAllCoupons();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        coups.removeIf(e -> e.getCompanyID() != companyId);
        return coups;
    }

    public ArrayList<Coupon> getCompanyCoupons(Category category) {
        ArrayList<Coupon> coups = getCompanyCoupons();
        coups.removeIf(e -> e.getCategory() != category);
        return coups;
    }

    public ArrayList<Coupon> getCompanyCoupons(double maxPrice) {
        ArrayList<Coupon> coups = getCompanyCoupons();
        Iterator<Coupon> iterator = coups.iterator();
        while (iterator.hasNext()) {
            Coupon coupon = iterator.next();
            if (coupon.getPrice() >= maxPrice) {
                iterator.remove();
            }
        }
        return coups;
    }

    public Company getCompanyDetails(){
        try {
            return companiesDAO.getOneCompany(companyId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Company(); //????????????????????
    }
}









    /*public void addCompany(Company company) throws Exception {
        // Check if company name or email already exists
        if (companiesDAO.isCompanyExists(company.getEmail(),company.getPassword())) {
            throw new Exception("Company with same name already exists");
        }
        if (companiesDAO.isCompanyExists(company.getEmail(),company.getPassword())) {
            throw new Exception("Company with same email already exists");
        }

        // Add company to database
        companiesDAO.addCompany(company);
    }*/


   /* public void updateCompany(Company company) throws Exception {
        // Check if company with given ID exists
        Company oldCompany = companiesDAO.getOneCompany(company.getId());
        if (oldCompany == null) {
            throw new Exception("Company with given ID does not exist");
        }

        // Update company in database
        companiesDAO.updateCompany(company);
    }*/

   /* public void deleteCompany(int companyID) throws Exception {
        // Check if company with given ID exists
        Company company = companiesDAO.getOneCompany(companyID);
        if (company == null) {
            throw new Exception("Company with given ID does not exist");
        }

        // Delete company and its coupons from database
        couponsDAO.deleteCompanyCoupons(companyID);
        companiesDAO.deleteCompany(companyID);
    }*/

   /* public ArrayList<Company> getAllCompanies() throws InterruptedException {
        return companiesDAO.getAllCompanies();
    }*/

    /*public Company getOneCompany(int companyID) throws InterruptedException {
        return companiesDAO.getOneCompany(companyID);
    }*/

    /*public void addCustomer(Customer customer) throws Exception {
        // Check if customer email already exists

        if (customersDAO.isCustomerExists(customer.getEmail(),password)) {
            throw new Exception("Customer with same email already exists");
        }

        // Add customer to database
        customersDAO.addCustomer(customer);
    }*/

   /* public void updateCustomer(Customer customer) throws Exception {
        // Check if customer with given ID exists
        Customer oldCustomer = customersDAO.getOneCustomer(customer.getId());
        if (oldCustomer == null) {
            throw new Exception("Customer with given ID does not exist");
        }

        // Update customer in database
        customersDAO.updateCustomer(customer);
    }*/

    /*public void deleteCustomer(int customerID) throws Exception {
        // Check if customer with given ID exists
        Customer customer = customersDAO.getOneCustomer(customerID);
        if (customer == null) {
            throw new Exception("Customer with given ID does not exist");
        }

    }*/
    /*    // Delete customer and its coupon
       public ArrayList<Customer> getAllCustomers() throws InterruptedException {
            return customersDAO.getAllCustomers();
        }*/










