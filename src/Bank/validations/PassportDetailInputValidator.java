package Bank.validations;

import javafx.scene.control.TextField;

public class PassportDetailInputValidator extends TextField {
        final int inputLength = 9;
        final String pattern = "^[A-Za-z0-9]+$";

        @Override
        public void replaceText(int start, int end, String text) {
            if (matchTest(text)) {
                super.replaceText(start, end, text);
            }
        }
        @Override
        public void replaceSelection(String text) {
            if (matchTest(text)) {
                super.replaceSelection(text);
            }
        }
        private boolean matchTest(String text) {
            return text.isEmpty() || (text.matches(pattern) && getText().length() < inputLength);
        }
}