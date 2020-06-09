package Bank;

import Bank.user.User;
import Bank.user.UserDAO;
import Bank.validations.EmptySpace;
import Bank.validations.PassportDetailInputValidator;
import Bank.validations.PersonalDetailInputValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class Controller {

    @FXML
    private Pane registerPageLast;

    @FXML
    private Button completeBtn;

    @FXML
    private Hyperlink returnBtnLink;

    @FXML
    private Pane registerPage;

    @FXML
    private Button buttonProceed;

    @FXML
    private Hyperlink backBtnLink;

    @FXML
    private Pane signInPage;

    @FXML
    private Button buttonRegister;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Text signInText;

    @FXML
    private PersonalDetailInputValidation newUserName;

    @FXML
    private PersonalDetailInputValidation newUserSurName;

    @FXML
    private PersonalDetailInputValidation newUserCountryResident;

    @FXML
    private PassportDetailInputValidator newUserPassport;

    @FXML
    private DatePicker newUserBirthDate;

    @FXML
    void backToSignIn(ActionEvent event) {
        if (event.getSource().equals(backBtnLink)){
            signInPage.toFront();
        }
    }

    public void proceedRegistration(ActionEvent event) throws SQLException {
        String f_name = newUserName.getText().trim();
        String l_name = newUserSurName.getText().trim();
        String country = newUserCountryResident.getText().trim();
        String passport = newUserPassport.getText();
//        String DOB = newUserBirthDate.getValue().toString();
        User user = this.createUserObject(f_name, l_name,country,passport);
        int userID = UserDAO.insertInDB(user);
        registerPageLast.toFront();
    }

    private User createUserObject(String f_name, String l_name, String country, String passport) {
        User user = new User();
        user.setFirstName(f_name);
        user.setLastName(l_name);
        user.setCountry(country);
        user.setPassport(passport);

        return  user;
    }

    @FXML
    void registrationProcess(ActionEvent event) {
        if (event.getSource().equals(buttonRegister)){
            registerPage.toFront();
        }
    }

    @FXML
    void returnToRegister(ActionEvent event) {
        if (event.getSource().equals(returnBtnLink)){
            registerPage.toFront();
        }
    }

    @FXML
    void completeRegistration(ActionEvent event) {
        if (event.getSource().equals(completeBtn)){
            //Data should be checked, and then entered to db

        }
    }

    @FXML
    void forgotPasswordProcedure(ActionEvent event) {
        if (event.getSource().equals(forgotPassword)){
            //new pane opens up to allow user reset password
        }
    }

    @FXML
    void signIn(ActionEvent event) {
        if (event.getSource().equals(buttonSignIn)){
            //checks entered data, in case of existing a user there, allows to enter the system, otherwise stays unchanged
        }
    }


}
