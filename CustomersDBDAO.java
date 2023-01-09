import java.sql.*;
import java.util.ArrayList;


public class CustomersDBDAO implements CustomersDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Override
    public boolean isCustomerExists(String email, String password) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return false;
    }



    public void addCustomer(Customer customer) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customers (name, email, password) VALUES (?, ?, ?)");
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPassword());
            statement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE customers SET name = ?, email = ?, password = ? WHERE id = ?");
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPassword());
            statement.setInt(4, customer.getId());
            statement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }


    @Override
    public void deleteCustomer(int customerID) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE id = ?");
            statement.setInt(1, customerID);
            statement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }


    @Override
    public ArrayList<Customer> getAllCustomers() throws InterruptedException {
        ArrayList<Customer> customers = new ArrayList<>();

        Connection connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Customer customer = new Customer(id, name, email, password);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }

        return null;
    }

    @Override
    public Customer getOneCustomer(int customerID) throws InterruptedException {
        // Query the database to retrieve the customer with the given ID
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE id = ?");
            statement.setInt(1, customerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                return new Customer(id, name, email, password);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return null;
    }


    @Override
    public ArrayList<Coupon> getCustomerCoupons(int customerID) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.id, c.title, c.start_date, c.end_date, c.amount, c.category, c.description, c.price FROM coupons c INNER JOIN customers_coupons cc ON c.id = cc.coupon_id WHERE cc.customer_id = ?");
            statement.setInt(1, customerID);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Coupon> coupons = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                int amount = resultSet.getInt("amount");
                Category category = Category.valueOf(resultSet.getString("category"));
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                Coupon coupon = new Coupon(id, title, startDate, endDate, amount, category, description, price);
                coupons.add(coupon);
            }
            return coupons;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return null;
    }

    @Override
    public Customer getOneCustomer(String email, String password) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                email = resultSet.getString("email");
                password = resultSet.getString("password");
                return new Customer(id, name, email, password);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return null;
    }
}



