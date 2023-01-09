import java.sql.SQLException;
import java.util.ArrayList;

public interface CompaniesDAO {


    Company getOneCompany(String email, String password)  throws InterruptedException;



    boolean isCompanyExists(String email, String password) throws SQLException, InterruptedException;
    ArrayList<Company> addCompany(Company company) throws InterruptedException, SQLException;
    void updateCompany(Company company) throws InterruptedException;
    void deleteCompany(int companyID) throws InterruptedException;
    ArrayList<Company> getAllCompanies() throws InterruptedException;
    Company getOneCompany(int companyID) throws InterruptedException;
    //Company getOneCompany (String email, String password) throws InterruptedException;

    Company getCompanyByName(String name)  throws InterruptedException;
   Company getCompanyByEmail(String email)throws InterruptedException;
}



