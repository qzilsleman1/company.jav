import java.util.ArrayList;
import java.util.Date;

public abstract class ClientFacade {
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;
    protected CouponsDAO couponsDAO;

    public ClientFacade(ConnectionPool connectionPool) {

    }

    protected ClientFacade() {
    }


    public abstract boolean login(String email, String password) throws InterruptedException;
}
