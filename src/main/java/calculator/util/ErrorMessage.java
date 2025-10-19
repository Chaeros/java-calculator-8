package calculator.util;

public class ErrorMessage {
    public static final String INVALID_FORMAT = "잘못된 커스텀 구분자 형식입니다. '//'와 '\\n' 사이에 구분자를 입력해야 합니다.";
    public static final String EMPTY_DELIMITER = "커스텀 구분자가 비어 있습니다.";
    public static final String NUMERIC_DELIMITER = "커스텀 구분자는 숫자가 될 수 없습니다: ";
    public static final String EMPTY_TOKEN = "잘못된 입력: 빈 숫자 토큰";
    public static final String NON_DIGIT = "숫자가 아닌 문자가 포함되었습니다: ";
    public static final String NEGATIVE = "음수는 허용되지 않습니다: ";
}