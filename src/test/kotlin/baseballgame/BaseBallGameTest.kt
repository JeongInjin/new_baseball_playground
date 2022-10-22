package baseballgame

import baseballgame.utils.baseBallInputValid
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BaseBallGameTest {
    /**
     * README > 목록 1 테스트
     */
    @Test
    @DisplayName("입력값이 숫자가 아니면 익셉션이 발생한다.")
    fun inputOnlyNumberTest() {
        val input = "1a2"
        assertThatThrownBy {input baseBallInputValid(input)}
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("오직 숫자만 입력 가능합니다.")
    }

    @Test
    @DisplayName("입력값의 숫자가 0 이 포함되었다면 익셉션이 발생한다.")
    fun inputNumberZeroTest() {
        val input = "012"
        assertThatThrownBy {input baseBallInputValid(input)}
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력 값은 0일 수 없습니다.")
    }

    @Test
    @DisplayName("입력값의 숫자가 3자리가 아니면 익셉션이 발생한다.")
    fun inputLengthThreeDigitsTest() {
        val input = "1234"
        assertThatThrownBy {input baseBallInputValid(input)}
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력은 3자리 숫자로 해주세요.")
    }

    @Test
    @DisplayName("빈값을 입력하면 익셉션이 발생한다.")
    fun inputBlankTest() {
        val input = ""
        assertThatThrownBy {input baseBallInputValid(input)}
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("빈값은 허용되지 않습니다.")
    }
}