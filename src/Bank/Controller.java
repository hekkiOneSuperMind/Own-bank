package Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
    void backToSignIn(ActionEvent event) {
        if (event.getSource().equals(backBtnLink)){
            signInPage.toFront();
        }
    }

    @FXML
    void proceedRegistration(ActionEvent event) {
        if (event.getSource().equals(buttonProceed)){
            registerPageLast.toFront();
        }
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
