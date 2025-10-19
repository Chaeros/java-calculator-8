package calculator;

import calculator.model.DelimiterParseResult;
import calculator.parser.DelimiterParser;
import calculator.validator.InputValidator;

public class StringSplitAndAddCalculator {
    public static int add(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }
        DelimiterParseResult result = DelimiterParser.parse(input);

        String[] tokens = result.numbers().split(result.delimiterPattern(), -1);
        int sum = 0;
        for (String token : tokens) {
            InputValidator.validateToken(token);
            sum += InputValidator.parsePositiveInt(token);
        }
        return sum;
    }
}