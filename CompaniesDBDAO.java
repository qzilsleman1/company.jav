import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompaniesDBDAO implements CompaniesDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE email ='"+email+"' AND password = '"+password+"';");

            ResultSet resultSet = statement.executeQuery();
            System.out.println("exist");
            return resultSet.next();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public ArrayList<Company> addCompany(Company company) throws InterruptedException, SQLException {
        Connection connection = connectionPool.getConnection();
        if (isCompanyExists(company.getEmail(), company.getPassword())) {
            System.out.println("Company Already Exist");
        } else {
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO companies (name, email, password) VALUES (?, ?, ?)");
                statement.setString(1, company.getName());
                statement.setString(2, company.getEmail());
                statement.setString(3, company.getPassword());
                statement.executeUpdate();
                System.out.println("Adding Company is Successful");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectionPool.returnConnection(connection);
            }
        }
        return null;
    }


        @Override
        public void updateCompany(Company company) throws InterruptedException{
            Connection connection = connectionPool.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM companies WHERE id = "+ company.getCompanyID() +"AND name = '"+ company.getName()+"'" );
                statement.setString(1, company.getEmail());
                statement.setString(2, company.getPassword());
                int check = statement.executeUpdate("delete from companies where id = '" + company.getCompanyID() + "'");

                if (check != 0) {
                    System.out.println("Company deleted sucssefuly");
                }

                //statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                connectionPool.returnConnection(connection);
            }
        }

        @Override
        public void deleteCompany(int companyID) throws InterruptedException {
            Connection connection = connectionPool.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM companies WHERE id = ?");
                statement.setInt(1, companyID);
                statement.executeUpdate();
                int check = statement.executeUpdate("delete from companies where id = '" + companyID + "'");

                if (check != 0) {
                    System.out.println("Company deleted sucssefuly");
                }

                //statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                connectionPool.returnConnection(connection);
            }
        }

        @Override
        public ArrayList<Company> getAllCompanies() throws InterruptedException{
            Connection connection = connectionPool.getConnection();
            ArrayList<Company> allCompanies = new ArrayList<Company>();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM companies");
                //ArrayList<Company> companies = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    Company company = new Company();
                    allCompanies.add(company);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                connectionPool.returnConnection(connection);
            }
            return allCompanies;
        }


        @Override
        public Company getOneCompany(int companyID){
            int ID;
            String Name, Email, Password;
            ArrayList<Coupon> coupons = new ArrayList<>();
            Company One_company = new Company() ;
            Coupon coup = null;
            Connection conn = null;
            Connection conn1 = null;
            try {
                conn = connectionPool.getConnection();

                String sqlQuery = "SELECT * FROM companies WHERE id = " + companyID + ";";
                Statement stmt = conn.prepareStatement(sqlQuery);
                ResultSet rs = stmt.executeQuery(sqlQuery);

                while (rs.next()) {
                    ID = rs.getInt("id");
                    Name = rs.getString("name");
                    Email = rs.getString("email");
                    Password = rs.getString("password");


                    conn1 = connectionPool.getConnection();
                    String sqlCoupon = "SELECT * FROM coupons WHERE company_id = " + companyID + ";";

                    PreparedStatement ps2 = conn1.prepareStatement(sqlCoupon);
                    ResultSet resultSet = ps2.executeQuery(sqlCoupon);
                    while (resultSet.next()) {
                       /*coup = new Coupon(rs2.getInt("id"), rs2.getInt("company_id"),
                                rs2.getInt("category_id"), rs2.getString("title"),
                                rs2.getString("description"), rs2.getDate("start_date"),
                                rs2.getDate("end_date"), rs2.getInt("amount"),
                                rs2.getDouble("price"), rs2.getString("image"));*/
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String password = resultSet.getString("password");
                        //

 }
                    ps2.close();
                    resultSet.close();

                    One_company = new Company();
                    //One_company = new Company(ID, Name, Email, Password, coupons);

                    coupons.add(coup);


                }
                rs.close();
                stmt.close();


            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                connectionPool.restoreConnection(conn);
                connectionPool.restoreConnection(conn1);
            }

            return One_company;

        }

    @Override
    public Company getCompanyByName(String name) throws InterruptedException {
        Company company = null;

        Connection connection = connectionPool.getConnection();
        try {
            // Use a prepared statement to prevent SQL injection attacks
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE name=?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            // If there is a result, create a Company object and set its fields
            if (resultSet.next()) {
                company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setName(resultSet.getString("name"));
                company.setEmail(resultSet.getString("email"));
                company.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            // Handle the exception
        } finally {
            connectionPool.returnConnection(connection);
        }

        return company;
    }

    @Override
    public Company getCompanyByEmail(String email) throws InterruptedException {
        Company company = null;

        Connection connection = connectionPool.getConnection();
        try {
            // Use a prepared statement to prevent SQL injection attacks
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE name=?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            // If there is a result, create a Company object and set its fields
            if (resultSet.next()) {
                company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setName(resultSet.getString("name"));
                company.setEmail(resultSet.getString("email"));
                company.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            // Handle the exception
        } finally {
            connectionPool.returnConnection(connection);
        }

        return company;
    }


    public Company getOneCompany (String email, String password) {
            int ID;
            String Name, Email, Password;
            ArrayList<Coupon> coupons = new ArrayList<>();
            Company One_company = null;
            Coupon coup = null;
            Connection conn = null;
            Connection conn1 = null;
            try {
                conn = connectionPool.getConnection();
                String sqlQuery = "SELECT * FROM companies WHERE email = '" + email + "' AND password = '" + password + "' ;";
                Statement stmt =conn.prepareStatement(sqlQuery);
                ResultSet rs = stmt.executeQuery(sqlQuery);

                while (rs.next()) {
                    ID = rs.getInt("id");
                    Name = rs.getString("name");
                    Email = rs.getString("email");
                    Password = rs.getString("password");
                    conn1 = connectionPool.getConnection();
                    String sqlCoupon = "SELECT * FROM coupons WHERE company_id = " + ID + ";";
                    PreparedStatement ps2 = conn.prepareStatement(sqlCoupon);
                    ResultSet resultSet = ps2.executeQuery(sqlCoupon);
                    while (resultSet.next()) {
                       ArrayList<Coupon> coupon = new ArrayList<>();
                        int id = resultSet.getInt("id");

                        coupon.add(coup);
                    }
                    ps2.close();
                    resultSet.close();
                    One_company = new Company();

                    //One_company = new Company(ID, Name, Email, Password, coupons);

                }
                rs.close();
                stmt.close();


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }finally {
                connectionPool.restoreConnection(conn);
                connectionPool.restoreConnection(conn1);
            }

            return One_company;
        }










        /*@Override
        public Company getOneCompany ( int companyID) throws InterruptedException {
            connection = connectionPool.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE id = ?");
                statement.setInt(1, companyID);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    return new Company();
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectionPool.returnConnection(connection);
            }
            return null;
        }*/





       /* @Override
        public Company getCompanyByName (String name) throws InterruptedException {
            // Declare a Company object to hold the result
            Company company = null;

            Connection connection = connectionPool.getConnection();
            try {
                // Use a prepared statement to prevent SQL injection attacks
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE name=?");
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                // If there is a result, create a Company object and set its fields
                if (resultSet.next()) {
                    company = new Company();
                    company.setId(resultSet.getInt("id"));
                    company.setName(resultSet.getString("name"));
                    company.setEmail(resultSet.getString("email"));
                    company.setPassword(resultSet.getString("password"));
                }
            } catch (SQLException e) {
                // Handle the exception
            } finally {
                connectionPool.returnConnection(connection);
            }

            return company;
        }*/


        //@Override
        // public Company getCompanyByEmail(String email) {

        //   return null;
        //}





}

