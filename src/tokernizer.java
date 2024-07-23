import java.util.ArrayList;
import java.util.List;

class Token {
    String type;
    String value;

    Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" + "type='" + type + '\'' + ", value='" + value + '\'' + '}';
    }
}

class Tokenizer {
    private String input;
    private int pos = 0;

    Tokenizer(String input) {
        this.input = input;
    }

    List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            char currentChar = input.charAt(pos);
            if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    number.append(input.charAt(pos));
                    pos++;
                }
                tokens.add(new Token("NUMBER", number.toString()));
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                tokens.add(new Token("OPERATOR", Character.toString(currentChar)));
                pos++;
            } else {
                pos++;
            }
        }
        return tokens;
    }
}
