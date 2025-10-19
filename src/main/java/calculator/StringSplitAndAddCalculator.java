package calculator;

import calculator.model.DelimiterParseResult;
import calculator.parser.DelimiterParser;
import calculator.util.ErrorMessage;

public class StringSplitAndAddCalculator {
    public static int add(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }
        DelimiterParseResult result = DelimiterParser.parse(input);

        String[] tokens = result.numbers().split(result.delimiterPattern(), -1);
        int sum = 0;
        for (String token : tokens) {
            validateToken(token);
            sum += parsePositiveInt(token);
        }
        return sum;
    }

    private static void validateToken(String token) {
        if (token.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_TOKEN);
        }
        if (!token.matches("\\d+")) { // "\\d+" : 숫자가 한 자리 이상 연속된 문자열 전체
            throw new IllegalArgumentException(ErrorMessage.NON_DIGIT + token);
        }
    }

    private static int parsePositiveInt(String token) {
        int num = Integer.parseInt(token);
        if (num < 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE + token);
        }
        return num;
    }
}