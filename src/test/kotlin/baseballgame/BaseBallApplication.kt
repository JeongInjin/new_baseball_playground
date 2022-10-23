package baseballgame

import baseballgame.domain.BaseBall
import baseballgame.utils.baseBallInputValid
import java.io.BufferedReader

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val baseBall = BaseBall()
    while (baseBall.reStart) {
        baseBall.playerInput = startBaseBall()

        baseBall.isCorrectAnswer()
        if (baseBall.correctAnswer) {
            restartQuestion(baseBall)
        }
    }
}

private fun BufferedReader.restartQuestion(baseBall: BaseBall) {
    println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
    baseBall.isRestartBaseBall(readLine())
}

private fun BufferedReader.startBaseBall(): String {
    print("숫자를 입력해 주세요 : ")
    val input = readLine()
    input baseBallInputValid (input)
    return input
}