package Bank.validations;

import javafx.scene.control.TextField;

public class PersonalDetailInputValidation extends TextField {

    public PersonalDetailInputValidation(){
        this.setPromptText("Enter detail...");
    }

    @Override
    public void replaceText(int i, int il, String string){
        String pattern ="[A-Za-z]+$";
        if ((string.matches(pattern))||(string.isEmpty())){
            super.replaceText(i,il,string);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}
