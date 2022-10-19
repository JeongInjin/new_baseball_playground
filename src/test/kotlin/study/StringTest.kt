
package study

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class StringTest {
    @Test
    fun replace() {
        val actual = "abc".replace("b", "d")
        assertThat(actual).isEqualTo("adc")
    }

    /**
     * 요구사항 1
     * "1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.
     * "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.
     */
    @Test
    fun `요구사항 1`() {
        //given
        val value = "1,2"

        //when
        val actual = value.split(",")

        //then
        assertThat(actual[0]).contains("1")
        assertThat(actual[1]).contains("2")
        assertThat(actual).containsExactly("1", "2")
    }

    /**
     * 요구사항 2
     * "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.
     */
    @Test
    fun `요구사항 2`() {
        //given
        val value = "(1,2)"

        //when
        val actual = value.substring(1, 4)

        //then
        assertThat(actual).isEqualTo("1,2")
    }

    /**
     * 요구사항 3
     * "abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.
     * String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트를 구현한다.
     * JUnit의 @DisplayName을 활용해 테스트 메소드의 의도를 드러낸다.
     */
    @Test
    @DisplayName("문자에 index 접근 테스트")
    fun `요구사항 3(1)`() {
        val actual = "abc"
        assertThat(actual[0]).isEqualTo("a")
        assertThat(actual[1]).isEqualTo("b")
        assertThat(actual[2]).isEqualTo("c")
    }

    @Test
    @DisplayName("문자열 인덱스를 넘어서면 익셉션이 발생한다.")
    fun `요구사항 3(2) `() {
        val actual = "abc"
        assertThatThrownBy { actual[3] }.isInstanceOf(StringIndexOutOfBoundsException::class.java)
    }

}