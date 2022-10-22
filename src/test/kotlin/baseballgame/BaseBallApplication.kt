package baseballgame

import baseballgame.utils.baseBallInputValid

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    print("숫자를 입력해 주세요 : ")
    val input = readLine()
    input baseBallInputValid(input)
}