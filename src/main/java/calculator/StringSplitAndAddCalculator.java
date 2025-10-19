package calculator;

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
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. '//' 이후 '\\n'이 반드시 입력되어야 합니다.");
            }

            String custom = numbers.substring(2, newlineIdx); // 커스텀 구분자로 포함된 문자열
            if (custom.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
            }
            if (custom.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException("커스텀 구분자는 숫자가 될 수 없습니다: " + custom);
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
            sum += Integer.parseInt(token);
        }
        return sum;
    }
}