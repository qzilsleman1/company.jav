import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;


public class AdminFacade extends ClientFacade {
    // Hard-coded login email and password
    private static final String LOGIN_EMAIL = "admin@admin.com";
    private static final String LOGIN_PASSWORD = "admin";

    // Data structures or fields for storing companies and customers
    public AdminFacade() {
        companiesDAO = new CompaniesDBDAO();
        customersDAO = new CustomersDBDAO();
        couponsDAO = new CouponsDBDAO();
    }


   public boolean login (String email, String password){
        if(email.equals(LOGIN_EMAIL) && password.equals(LOGIN_PASSWORD)){
            return true;
        }

        return false;
   }
    // Add company method
    public void addCompany(Company company) throws InterruptedException, SQLException {

        // Check if a company with the same name or email already exists
        if (companiesDAO.isCompanyExists(company.getEmail(), company.getPassword())) {
            // If a company with the same name or email already exists, throw an exception
            throw new IllegalArgumentException("A company with the same name or email already exists");
        }else{
                companiesDAO.addCompany(company);        }
    }
    public void updateCompany (Company company) throws InterruptedException {
            // Check if company object is valid
            if (company == null || company.getCompanyID() == 0) {
                throw new IllegalArgumentException("Invalid company object. Cannot update company.");
            }

            // Check if company name or email is already in use by another company
            Company existingCompany = companiesDAO.getCompanyByName(company.getName());
            if (existingCompany != null && existingCompany.getCompanyID() != company.getCompanyID()) {
                throw new IllegalArgumentException("Company name already in use. Cannot update company.");
            }
            existingCompany = companiesDAO.getCompanyByEmail(company.getEmail());
            if (existingCompany != null && existingCompany.getCompanyID() != company.getCompanyID()) {
                throw new IllegalArgumentException("Company email already in use. Cannot update company.");
            }

            // Update company in database or data structure
            companiesDAO.updateCompany(company);

            // Confirm update was successful
            System.out.println("Company successfully updated.");
    }


    public void deleteCompany(int companyID) throws InterruptedException {
        companiesDAO.deleteCompany(companyID);
    }

    // Get all companies method
    public ArrayList<Company> getAllCompanies() throws SQLException, InterruptedException {
      return   companiesDAO.getAllCompanies();
    }


    // Get one company method
    public Company getOneCompany(int CompanyId) throws InterruptedException {return companiesDAO.getOneCompany(CompanyId);

    }
    // Add customer method
    public void addCustomer(Customer customer) throws InterruptedException {

        customersDAO.addCustomer(customer);
    }
    public void deleteCustomer(int CustomerId) throws InterruptedException {
        customersDAO.deleteCustomer(CustomerId);
    }
    public ArrayList<Customer> getAllCustomers() throws InterruptedException {
        return customersDAO.getAllCustomers();
    }
    public Customer getOneCustomer(int CustomerId) throws InterruptedException {
        return customersDAO.getOneCustomer(CustomerId);

    }

}

