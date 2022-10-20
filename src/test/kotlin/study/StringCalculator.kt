package study

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

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
        val inputValue = "2 + 3 * 4 / 2"
        val calculator = Calculator(inputValue)

        for (s in calculator.valueList) {
            getArithmeticOperation(calculator, s)
        }

        Assertions.assertThat(calculator.result).isEqualTo(10.0)

    }
}

infix fun String.isInteger(s: String): Int? {
    return s.toIntOrNull()
}

class Calculator(val inputValue: String) {

    init {
        if(inputValue.isNullOrEmpty())
            throw IllegalArgumentException("값을 입력해 주세요.")
        inputValue.split(" ").also { this.valueList = it as MutableList<String> }
        if(valueList.size <= 1) throw IllegalArgumentException("숫자와 식 은 띄워쓰기로 입력해 주세요.")

        val integer = valueList[0].isInteger(valueList[0])
        (integer?.toDouble() ?: kotlin.run { throw IllegalArgumentException("첫번째 인수는 반드시 숫자여야 합니다") }).also { this.result = it }
        this.valueList.removeAt(0)
    }

    var result: Double = 0.0
    lateinit var arithmeticOperation: String

    var valueList:MutableList<String>

    fun add(num: Int) {
        result += num
    }

    fun subtract(num: Int) {
        result -= num
    }

    fun multiply(num: Int) {
        result *= num
    }

    fun divide(num: Int) {
        result /= num
    }
}

fun getArithmeticOperation(calculator: Calculator, num: String) {
    if (num.isInteger(num) == null) {
        calculator.arithmeticOperation = num
    } else {
        when (calculator.arithmeticOperation) {
            "+" -> calculator.add(num.toInt())
            "-" -> calculator.subtract(num.toInt())
            "*" -> calculator.multiply(num.toInt())
            "/" -> calculator.divide(num.toInt())
            else -> throw IllegalArgumentException("[+, -, *, /] 만 허용됩니다.")
        }
    }
}

