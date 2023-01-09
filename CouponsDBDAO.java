
import java.sql.*;
import java.util.ArrayList;

public  class CouponsDBDAO implements CouponsDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void addCoupon(Coupon coupon) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO coupons (title, start_date, end_date, amount, category, message, price, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, coupon.getTitle());
            statement.setDate(2, new Date(coupon.getStartDate().getTime()));
            statement.setDate(3, new Date(coupon.getEndDate().getTime()));
            statement.setInt(4, coupon.getAmount());
            statement.setString(5, coupon.getCategory().name());
            statement.setString(6, coupon.getMessage());
            statement.setDouble(7, coupon.getPrice());
            statement.setString(8, coupon.getImage());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void updateCoupon(Coupon coupon) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE coupons SET title = ?, start_date = ?, end_date = ?, amount = ?, category = ?, message = ?, price = ?, image = ? WHERE id = ?");
            statement.setString(1, coupon.getTitle());
            statement.setDate(2, new Date(coupon.getStartDate().getTime()));
            statement.setDate(3, new Date(coupon.getEndDate().getTime()));
            statement.setInt(4, coupon.getAmount());
            statement.setString(5, coupon.getCategory().name());
            statement.setString(6, coupon.getMessage());
            statement.setDouble(7, coupon.getPrice());
            statement.setString(8, coupon.getImage());
            statement.setInt(9, coupon.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteCoupon(int couponID) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM coupons WHERE id = ?");
            statement.setInt(1, couponID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public ArrayList<Coupon> getAllCoupons() throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM coupons");
            ArrayList<Coupon> coupons = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                int amount = resultSet.getInt("amount");
                Category category = Category.valueOf(resultSet.getString("category"));
                String message = resultSet.getString("message");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                coupons.add(new Coupon(id, title, startDate, endDate, amount, category, message, price, image));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Coupon getOneCoupon(int couponID) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM coupons WHERE id = ?");
            statement.setInt(1, couponID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                int amount = resultSet.getInt("amount");
                Category category = Category.valueOf(resultSet.getString("category"));
                String message = resultSet.getString("message");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                return new Coupon(id, title, startDate, endDate, amount, category, message, price, image);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return null;
    }
    @Override
    public void addCouponPurchase(int customerID, int couponID) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO coupon_purchases (customer_id, coupon_id) VALUES (?, ?)");
            statement.setInt(1, customerID);
            statement.setInt(2, couponID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
    @Override
    public void deleteCouponPurchase(int customerID, int couponID) throws InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM coupon_purchases WHERE customer_id = ? AND coupon_id = ?");
            statement.setInt(1, customerID);
            statement.setInt(2, couponID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
    @Override
    public ArrayList<Coupon> getCompanyCoupons(int companyID) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM coupons WHERE company_id = ?");
            statement.setInt(1, companyID);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Coupon> coupons = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                int amount = resultSet.getInt("amount");
                Category category = Category.valueOf(resultSet.getString("category"));
                String message = resultSet.getString("message");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                coupons.add(new Coupon(id, title, startDate, endDate, amount, category, message, price, image));
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
    public void deleteCompanyCoupons(int companyID) throws InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM coupons WHERE company_id = ?");
            statement.setInt(1, companyID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }


        @Override
        public boolean isCouponPurchased(int customerID, int couponID) throws InterruptedException {
            Connection connection = connectionPool.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM coupon_purchases WHERE customer_id = ? AND coupon_id = ?");
                statement.setInt(1, customerID);
                statement.setInt(2, couponID);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectionPool.returnConnection(connection);
            }
            return false;
        }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(int customerID, double maxPrice) {
        return null;
    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(int customerID, Category category) {
        return null;
    }

    @Override
    public void deleteExpiredCoupons(java.util.Date currentTime) {

    }

    @Override
        public ArrayList<Coupon> getCustomerCoupons(int customerID) throws InterruptedException {
            Connection connection = connectionPool.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT c.* FROM coupons c INNER JOIN coupon_purchases cp ON c.id = cp.coupon_id WHERE cp.customer_id = ?");
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
                    String message = resultSet.getString("message");
                    double price = resultSet.getDouble("price");
                    String image = resultSet.getString("image");
                    coupons.add(new Coupon(id, title, startDate, endDate, amount, category, message, price, image));
                }
                return coupons;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectionPool.returnConnection(connection);
            }
return null;
        }
}
