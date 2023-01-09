import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class ConnectionPool {
    private static ConnectionPool instance;
    static List<Connection> connections = new ArrayList<Connection>();
    public com.sun.jdi.connect.spi.Connection get;
    private Semaphore semaphore = new Semaphore(1);
    private int i=10;
    public Connection con = null;

    // JDBC - JAVA Data base Connection , connection parameters
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/company";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "1234";


    public ConnectionPool() {
    }

   /* public static Set<Connection> getConnections() {
        return connections;
    }

    public static void setConnections(Set<Connection> connections) {
        ConnectionPool.connections = connections;
    }
*/

    //singleton
    private void DBConnection()
    {
        int i =10;

        try {
            while (i>0)
            {
                con = createConnection();
                connections.add(con);

                i--;
            }


        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL , USERNAME , PASSWORD);
    }


    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        // Acquire a semaphore permit before trying to retrieve a connection
        DBConnection();
        semaphore.acquire();
        synchronized (connections) {

           if (connections.isEmpty())
                return null;
           else
                    return connections.get(0);
            // Retrieve a connection from the repository and return it
        }

    }

    public void restoreConnection(Connection connection) {
        synchronized (connections) {
            // Add the connection back to the repository and release the semaphore permit
            connections.add(connection);
            semaphore.release();
        }
    }

    public void closeAllConnections() {
        // Close all connections in the repository
    }

    public void returnConnection(Connection connection) {

    }
}
