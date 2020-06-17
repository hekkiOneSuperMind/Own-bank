package Bank.validations;

import javafx.scene.control.TextField;

public class DigitsInputValidation extends TextField {

    public DigitsInputValidation() {
        this.setPromptText("Enter detail...");
    }

    @Override
    public void replaceText(int i, int il, String string){
        String pattern ="[0-9]+$";
        if ((string.matches(pattern))||(string.isEmpty())){
            super.replaceText(i,il,string);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}
