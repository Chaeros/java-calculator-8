package calculator.model;

public record DelimiterParseResult(
        String delimiterPattern,
        String numbers
) {}