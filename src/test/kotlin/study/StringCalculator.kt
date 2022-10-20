package study

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.text.DecimalFormat

/**
 * 단위 테스트 실습 - 문자열 계산기
    다음 요구사항을 JUnit을 활용해 단위 테스트 코드를 추가해 구현한다.
    요구사항
        1.사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 한다.
        2.문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다. 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
        3.예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
    =힌트=
        문자열을 입력 받은 후(scanner의 nextLine() 메소드 활용) 빈 공백 문자열을 기준으로 문자들을 분리해야 한다.
            String value = scanner.nextLine();
            String[] values = value.split(" ");
        문자열을 숫자로 변경하는 방법
            int number = Integer.parseInt("문자열");
 */
class StringCalculator {

    @Test
    fun StringCalculatorTest() {
        //given
        val inputValue = "2 + 3 * 4 / 2"
        val inputValue2 = "51 - 32 * 11 / 3 + 3"
        val inputValue3 = "0 / 2 + 3 * 0 + 2 - 4 / 2"
        val inputValue4 = "1 / 1"
        val inputValue5 = "1 / 1"

        //when
        val calculator = Calculator(inputValue)
        val calculator2 = Calculator(inputValue2)
        val calculator3 = Calculator(inputValue3)
        val calculator4 = Calculator(inputValue4)
        val calculator5 = Calculator(inputValue5)
            calculator5.arithmeticOperation = "^"

        //then
        assertThat(calculator.result).isEqualTo(10.0)
        assertThat(calculator2.result).isEqualTo(72.67)
        assertThat(calculator3.result).isEqualTo(-1.0)
        //나눗셈일경우 0 이면 안된다.
        assertThatThrownBy { getArithmeticOperation(calculator4, "0") }.isInstanceOf(IllegalArgumentException::class.java)
        //사칙연산 을 제외한 나머지 계산식은 에러를 발생한다.
        assertThatThrownBy { getArithmeticOperation(calculator5, "1") }.isInstanceOf(IllegalArgumentException::class.java)
    }
}

class Calculator(inputValue: String) {

    init {

        if(inputValue.isNullOrEmpty()) throw IllegalArgumentException("값을 입력해 주세요.")
        inputValue.split(" ").also { this.valueList = it as MutableList<String> }
        if(valueList.size <= 1) throw IllegalArgumentException("숫자와 식 은 띄워쓰기로 입력해 주세요.")

        val integer = valueList[0].toIntOrNull()
        (integer?.toDouble() ?: kotlin.run { throw IllegalArgumentException("첫번째 인수는 반드시 숫자여야 합니다") }).also { this.result = it }
        this.valueList.removeAt(0)
        
        startCal() //계산시작
    }

    var result: Double = 0.0
        get() {
            return DecimalFormat("#.##").format(field).toDouble()
        }

    lateinit var arithmeticOperation: String

    var valueList:MutableList<String>

    private fun startCal() {
        for (s in this.valueList) {
            getArithmeticOperation(this, s)
        }
    }

    fun compute(operation: () -> Unit) = operation()
}

fun getArithmeticOperation(cal: Calculator, num: String) {
    if (num.toIntOrNull() == null) {
        cal.arithmeticOperation = num
    } else {
        when (cal.arithmeticOperation) {
            "+" -> cal.compute { cal.result += num.toDouble() }
            "-" -> cal.compute { cal.result -= num.toDouble() }
            "*" -> cal.compute { cal.result *= num.toDouble() }
            "/" -> cal.compute {
                if(num.toInt() == 0) throw IllegalArgumentException("나누기는 0 으로 할 수 없습니다.")
                cal.result /= num.toDouble()
            }
            else -> throw IllegalArgumentException("[+, -, *, /] 만 허용됩니다.")
        }
    }
}

