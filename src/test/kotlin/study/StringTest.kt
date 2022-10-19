
package study

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class StringTest {
    @Test
    fun replace() {
        val actual = "abc".replace("b", "d")
        Assertions.assertThat(actual).isEqualTo("adc")
    }

}