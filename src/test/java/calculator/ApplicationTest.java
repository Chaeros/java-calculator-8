package calculator;

import calculator.util.ErrorMessage;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 기본_구분자_쉼표와_콜론_사용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 빈_문자열_입력시_0_반환() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 복수_커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//a\\n//b\\n1a2b3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 여러_글자_커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//abc\\n1abc2abc3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 잘못된_커스텀_구분자_형식_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;1;2"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.INVALID_FORMAT)
        );
    }

    @Test
    void 구분자_사이에_빈값이_있을_경우_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,,2"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.EMPTY_TOKEN)
        );
    }

    @Test
    void 숫자가_아닌_문자가_포함된_경우_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.NON_DIGIT)
        );
    }

    @Test
    void 음수가_포함된_경우_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,-2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.NEGATIVE)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
