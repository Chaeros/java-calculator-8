package calculator;

import calculator.util.ErrorMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringSplitAndAddCalculator {
    public static int add(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        String numbers = input;
        String delimiterPattern = "[,:]";

        List<String> customDelimiters = new ArrayList<>();
        while (numbers.startsWith("//")) {
            int newlineIdx = numbers.indexOf("\\n");
            if (newlineIdx < 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT);
            }

            String custom = numbers.substring(2, newlineIdx); // 커스텀 구분자로 포함된 문자열
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
        if (!customDelimiters.isEmpty()) {
            delimiterPattern += "|" + String.join("|", customDelimiters);
        }

        String[] tokens = numbers.split(delimiterPattern, -1);
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