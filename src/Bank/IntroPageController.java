package Bank;

import Bank.user.User;
import Bank.user.UserDAO;
import Bank.validations.FormValidation;
import Bank.validations.PassportDetailInputValidator;
import Bank.validations.PersonalDetailInputValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

public class IntroPageController {

    @FXML
    private Pane registerPageLast;

    @FXML
    private TextField newUserPhone;

    @FXML
    private TextField newUserEmail;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newPasswordConfirmation;

    @FXML
    private Button completeBtn;

    @FXML
    private Hyperlink returnBtnLink;

    @FXML
    private Label newPasswordFilledStatusVerificator;

    @FXML
    private Label passwordSimilarity;

    @FXML
    private Pane registerPage;

    @FXML
    private DatePicker newUserBirthDate;

    @FXML
    private Button buttonProceed;

    @FXML
    private Hyperlink backBtnLink;

    @FXML
    private Label nameVerificator;

    @FXML
    private Label surnameVerificator;

    @FXML
    private Label countryVerificator;

    @FXML
    private Label passportVerificator;

    @FXML
    private Label DOBVerificator;

    @FXML
    private Pane signInPage;

    @FXML
    private TextField user_ID;

    @FXML
    private PasswordField user_password;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonRegister;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private Label pageIndicator;

    @FXML
    private Label idFilledStatusVerificator;

    @FXML
    private Label profileVerificator;

    @FXML
    private Label passwordFilledStatusVerificator;

    @FXML
    private PersonalDetailInputValidation newUserName;

    @FXML
    private PersonalDetailInputValidation newUserSurName;

    @FXML
    private PersonalDetailInputValidation newUserCountryResident;

    @FXML
    private PassportDetailInputValidator newUserPassport;

    @FXML
    void backToSignIn(ActionEvent event) {
        if (event.getSource().equals(backBtnLink)){
            signInPage.toFront();
        }
    }

    public void proceedRegistration(ActionEvent event) throws ParseException {
        boolean isInputtedDOB = isInputtedDOB();
        boolean isInputtedName = FormValidation.isNotFilled(newUserName.getText(),nameVerificator,"Name is not entered!");
        boolean isInputtedSurName = FormValidation.isNotFilled(newUserSurName.getText(),surnameVerificator,"Surname is not entered!");
        boolean isInputtedCountry = FormValidation.isNotFilled(newUserCountryResident.getText(),countryVerificator,"Country is not entered!");
        boolean isInputtedPassport = FormValidation.isNotFilled(newUserPassport.getText(),passportVerificator,"Passport is not entered!");
        if (!isInputtedName && !isInputtedSurName && !isInputtedCountry && !isInputtedPassport && isInputtedDOB){
            registerPageLast.toFront();
        }

    }

    public boolean isInputtedDOB() throws ParseException {
        boolean isInputtedDOB;

        if (newUserBirthDate.getValue() != null){

            isInputtedDOB = true;
            DOBVerificator.setText("");
        }
        else {
            DOBVerificator.setText("Date of birth is not entered!");
            isInputtedDOB = false;
        }
        return isInputtedDOB;
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
    void completeRegistration(ActionEvent event) throws SQLException {
        String f_name = newUserName.getText().trim();
        String l_name = newUserSurName.getText().trim();
        String country = newUserCountryResident.getText().trim();
        String passport = newUserPassport.getText();
        String DOB = newUserBirthDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String entered_pass = newPassword.getText();
        User user = this.createUserObject(f_name, l_name,country,passport,DOB,entered_pass);
        int userID = UserDAO.insertInDB(user);
        confirmation(userID);
    }

    private void confirmation(int userID) {
        if (userID > 0){
            JOptionPane.showMessageDialog(null, "User data successfully saved!");
        }
        else {
            JOptionPane.showMessageDialog(null, "User data is not saved!");
        }
    }

    @FXML
    void forgotPasswordProcedure(ActionEvent event) {
        if (event.getSource().equals(forgotPassword)){
            //new pane opens up to allow user reset password
        }
    }

    @FXML
    void signIn(ActionEvent event) throws SQLException {
        String user_id = user_ID.getText().trim();
        String usr_password = user_password.getText().trim();
        if(UserDAO.isInDatabase(user_id,usr_password)){
            //Load some page
        }
    }

    private User createUserObject(String f_name, String l_name, String country, String passport,String dob, String password) {
        User user = new User();
        user.setFirstName(f_name);
        user.setLastName(l_name);
        user.setCountry(country);
        user.setPassport(passport);
        user.setPassword(password);
        return  user;
    }

}
