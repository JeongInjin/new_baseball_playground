package baseballgame

import baseballgame.domain.BaseBall
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assumptions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class BaseBallGameTest {

    lateinit var baseBall: BaseBall

    @BeforeEach
    fun setUp() {
        baseBall = BaseBall()
    }

    /**
     * README > 목록 1 테스트
     */
    @Test
    @DisplayName("입력값이 숫자가 아니면 익셉션이 발생한다.")
    fun inputOnlyNumberTest() {
        assertThatThrownBy { baseBall.playerInput = "1a2" }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("오직 숫자만 입력 가능합니다.")
    }

    @Test
    @DisplayName("입력값의 숫자가 0 이 포함되었다면 익셉션이 발생한다.")
    fun inputNumberZeroTest() {
        val input = "012"
        assertThatThrownBy { baseBall.playerInput = "012" }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력 값은 0일 수 없습니다.")
    }

    @Test
    @DisplayName("입력값의 숫자가 3자리가 아니면 익셉션이 발생한다.")
    fun inputLengthThreeDigitsTest() {
        assertThatThrownBy { baseBall.playerInput = "1234" }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력은 3자리 숫자(0 제외)로 해주세요.")
    }

    @Test
    @DisplayName("빈값을 입력하면 익셉션이 발생한다.")
    fun inputBlankTest() {
        assertThatThrownBy { baseBall.playerInput = "" }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("빈값은 허용되지 않습니다.")
    }

    /**
     * 목록 2 테스트
     */
    @Test
    @DisplayName("정답일경우 빈값 입력시 익셉션이 발생한다.")
    fun answerBlankTest() {
        assertThatThrownBy { baseBall.isRestartBaseBall("") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("빈값은 허용되지 않습니다.")
    }

    @Test
    @DisplayName("정답일경우 숫자가 1자리가 아니면 익셉션이 발생한다.")
    fun answerLengthOneDigitsTest() {
        assertThatThrownBy { baseBall.isRestartBaseBall("123") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력은 1자리 숫자(1 또는 2)로 해주세요.")
    }

    @Test
    @DisplayName("정답일경우 숫자 1 또는 2 만 입력 가능하다.")
    fun checkResetNumberTest() {
        assertThatThrownBy { baseBall.isRestartBaseBall("4") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력 값은 1 또는 2 만 입력 가능합니다.")
    }

    @Test
    @DisplayName("정답일경우 랜덤값이 초기화된다.")
    fun randomNumInitTest() {
        val baseBall = BaseBall()
        baseBall.randomNumber = "test"
        baseBall.isRestartBaseBall("1")

        assertThat(baseBall.randomNumber).isNotEqualTo("test")
    }

    /**
     * 목록 3 테스트
     */
    @Test
    @DisplayName("같은 수가 같은 자리에 있으면 스트라이크 이다.")
    fun sameInputStrikeTest() {
        baseBall.randomNumber = "123"
        baseBall.playerInput = "145"
        assertThat(baseBall.strike).isEqualTo(1)
        baseBall.playerInput = "125"
        assertThat(baseBall.strike).isEqualTo(2)
        baseBall.playerInput = "123"
        assertThat(baseBall.strike).isEqualTo(3)
    }

    @Test
    @DisplayName("같은 수가 같은 자리에 있으면 볼 이다.")
    fun sameInputBallTest() {
        baseBall.randomNumber = "123"
        baseBall.playerInput = "345"
        assertThat(baseBall.ball).isEqualTo(1)
        baseBall.playerInput = "315"
        assertThat(baseBall.ball).isEqualTo(2)
        baseBall.playerInput = "312"
        assertThat(baseBall.ball).isEqualTo(3)
    }

    @Test
    @DisplayName("같은 수가 전혀 없으면 낫싱 이다.")
    fun sameInputNothingTest() {
        baseBall.randomNumber = "123"
        baseBall.playerInput = "125"
        assertThat(baseBall.nothing).isEqualTo(1)
        baseBall.playerInput = "145"
        assertThat(baseBall.nothing).isEqualTo(2)
        baseBall.playerInput = "567"
        assertThat(baseBall.nothing).isEqualTo(3)
    }

    /**
     * 목록 4 테스트
     */
    @Test
    @DisplayName("1~9 의 숫자중 중복되지 않은 3자리를 생성한다.")
    fun randomInit() {
        assertThat(baseBall.randomNumber.length).isEqualTo(3)
    }

}
