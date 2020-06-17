package Bank.validations;

import javafx.scene.control.Label;

public class FormValidation {
    public static final String Empty = "";

    public static boolean isNotFilled(String text){
        boolean isEmpty = false;
        if(text.equals(Empty)) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean isNotFilled(String text, Label label, String error){
        boolean isEmpty = false;
        label.setText(Empty);
        if (isNotFilled(text)){
            isEmpty = true;
            label.setText(error);
        }
        return isEmpty;
    }


}
