package calculator.validator;

import calculator.util.ErrorMessage;

public class InputValidator {
    public static void validateToken(String token) {
        if (token.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_TOKEN);
        }
        if (!token.matches("-?\\d+")) {
            throw new IllegalArgumentException(ErrorMessage.NON_DIGIT + token);
        }
    }

    public static int parsePositiveInt(String token) {
        int num = Integer.parseInt(token);
        if (num < 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE + token);
        }
        return num;
    }
}