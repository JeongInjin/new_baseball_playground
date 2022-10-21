package study

import org.junit.jupiter.api.DisplayName
import java.util.*

@DisplayName("입력값을 받아 사칙연산을 수행한다.")
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    println("수식을 입력해 주세요:(ex: 2 + 3 * 4 / 2)")
    print("=> ")
    val calculator = Calculator(Scanner(System.`in`).nextLine().toString())

    println("계산 값: ${calculator.result}")
}