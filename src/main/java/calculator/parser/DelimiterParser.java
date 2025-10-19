package calculator.parser;

import calculator.model.DelimiterParseResult;
import calculator.util.ErrorMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DelimiterParser {

    private static final String DEFAULT_DELIMITER = "[,:]";
    private static final String CUSTOM_PREFIX = "//";
    private static final String CUSTOM_NEWLINE = "\\n";

    public static DelimiterParseResult parse(String input) {
        String numbers = input;
        List<String> customDelimiters = new ArrayList<>();

        // 커스텀 구분자 반복 탐색
        while (numbers.startsWith(CUSTOM_PREFIX)) {
            int newlineIdx = numbers.indexOf(CUSTOM_NEWLINE);
            if (newlineIdx < 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT);
            }

            String custom = numbers.substring(2, newlineIdx);
            if (custom.isEmpty()) {
                throw new IllegalArgumentException(ErrorMessage.EMPTY_DELIMITER);
            }
            if (custom.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException(ErrorMessage.NUMERIC_DELIMITER + custom);
            }

            customDelimiters.add(Pattern.quote(custom));
            numbers = numbers.substring(newlineIdx + 2); // 다음 커스텀 구분자 탐색
        }

        // 기본 구분자, 복수 커스텀 구분자 통합
        String delimiterPattern = DEFAULT_DELIMITER;
        if (!customDelimiters.isEmpty()) {
            delimiterPattern += "|" + String.join("|", customDelimiters);
        }
        return new DelimiterParseResult(delimiterPattern, numbers);
    }
}