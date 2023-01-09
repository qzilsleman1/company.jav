import java.util.HashMap;

public class LoginManager  {
    private static LoginManager instance;
//    private ConnectionPool connectionPool;
//    private HashMap<String, ClientFacade> loggedInUsers;

    private LoginManager() {
        // Initialize connection pool and logged in users map
//        connectionPool = new ConnectionPool();
//        loggedInUsers = new HashMap<>();
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws Exception {
       try{ // Check if user is already logged in



        // Create appropriate ClientFacade based on client type
        switch (clientType) {
            case ADMIN:
                AdminFacade adminFacade = new AdminFacade();
                if(adminFacade.login(email,password)){
                    return  adminFacade;
                }


            case COMPANY:
                /*Company company = CompaniesDAO.getOneCompany(email, password);
                if (company == null) {
                    throw new Exception("Invalid login credentials");
                }
                ClientFacade clientFacadecomp = new CompanyFacade(ConnectionPool.getInstance(), company.getId());
                break;*/
                CompanyFacade companyFacade = new CompanyFacade();
                if (companyFacade.login(email, password)){
                    return companyFacade;
                }
            case CUSTOMER:
                /*CustomersDBDAO customersDAO = null;
                Customer customer = customersDAO.getOneCustomer(email, password);
                if (customer == null) {
                    throw new Exception("Invalid login credentials");
                }
                ClientFacade clientFacadecust = new CustomerFacade(ConnectionPool.getInstance(), customer.getId());
                break;
            default:
                throw new Exception("Invalid client type");*/
                CustomerFacade customerFacade = new CustomerFacade();
                if (customerFacade.login(email, password)){
                    return customerFacade;
                }
        }

        // Check login credentials and add to logged in users map if successful

        return null;
    } catch (Exception e) {
           e.printStackTrace();
       }
        return null;
    }
}
