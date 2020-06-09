package Bank.user;

import Bank.database.ConnectionToDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public boolean isInDatabase(String passportDetail) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();

        try{
            connection = ConnectionToDB.getConnToDB();
            connection.setAutoCommit(false);
            String sql = "SELECT id, passport_detail FROM users WHERE passport_detail = ?";
            preparedStatement = connection.prepareStatement(sql);
            int counter = 1;
            preparedStatement.setString(counter++,passportDetail);
            resultSet = preparedStatement.executeQuery();
            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt(1));
                user.setPassport(resultSet.getString(4));
                userList.add(user);
            }
            return userList.isEmpty()? false:true;
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
        return userList.isEmpty()? false:true;
    }

    public static int insertInDB(User user) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionToDB.getConnToDB();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO users(first_name, last_name, country, passport_detail, date_of_birth)VALUES(?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            preparedStatement.setString(counter++,user.getFirstName());
            preparedStatement.setString(counter++,user.getLastName());
            preparedStatement.setString(counter++,user.getCountry());
            preparedStatement.setString(counter++,user.getPassport());
            preparedStatement.setString(counter,user.getDOB());
            counter++;
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
