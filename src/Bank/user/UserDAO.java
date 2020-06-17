package Bank.user;

import Bank.database.ConnectionToDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public static void isInDatabase(String passportDetail, String user_password) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionToDB.getConnToDB();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("SELECT passport_detail, password FROM users");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String user = resultSet.getString(1);
                String pass = resultSet.getString(2);
                if (user.equals(passportDetail) && pass.equals(user_password)){
                    System.out.println("Success");
                }
            }


        }catch (Exception e){
            logger.log(Level.SEVERE,e.getMessage());
        }finally {
            if (null != preparedStatement) {
                preparedStatement.close();
            }

            if (null != connection) {
                connection.close();
            }
        }
    }

    public static int insertInDB(User user) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionToDB.getConnToDB();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO users(first_name, last_name, country, passport_detail, date_of_birth, password, phone_number,email_address)VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            preparedStatement.setString(counter++,user.getFirstName());
            preparedStatement.setString(counter++,user.getLastName());
            preparedStatement.setString(counter++,user.getCountry());
            preparedStatement.setString(counter++,user.getPassport());
            preparedStatement.setString(counter++,user.getDOB());
            preparedStatement.setString(counter++,user.getPassword());
            preparedStatement.setString(counter++,user.getPhone_number());
            preparedStatement.setString(counter,user.getEmail());

            preparedStatement.executeUpdate();
            connection.commit();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){

                return resultSet.getInt(1);
            }
        }catch (Exception e){
            logger.log(Level.SEVERE,e.getMessage());
            if (null != connection){
                connection.rollback();
            }
        }finally {
            if (null != resultSet) {
                resultSet.close();
            }

            if (null != preparedStatement) {
                preparedStatement.close();
            }

            if (null != connection) {
                connection.close();
            }
        }

        return 0;
    }
}
