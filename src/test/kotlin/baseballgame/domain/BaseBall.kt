package baseballgame.domain

import baseballgame.utils.baseBallInputValid
import baseballgame.utils.baseBallResetValid
import jdk.jfr.Description
import kotlin.text.StringBuilder

class BaseBall {

    init {
        getRandomNumber()
    }

    var ball = 0
    var strike = 0
    var nothing = 0

    @Description("컴퓨터가 지정할 랜덤 값")
    lateinit var randomNumber: String

    @Description("정답 유무")
    var correctAnswer = false

    @Description("정답일 경우 재시작 유무")
    var reStart = true

    @Description("플레이어 입력 값")
    var playerInput: String = ""
        set(value) {
            value baseBallInputValid(value)
            field = value
            compareNumber()
        }

    @Description("정답일 경우 재시작 입력 값(1, 2)")
    var restartAnswer: String? = null
        set(value) {
            if (value == "2") reStart = false
            field = value
        }

    @Description("게임 재시작시 호출")
    fun isRestartBaseBall(input: String) {
        input baseBallResetValid(input)
        restartAnswer = input
        resetBaseBall()
    }

    @Description("모든게임 초기화")
    fun resetBaseBall() {
        getRandomNumber()
        this.correctAnswer = false
    }

    @Description("숫자 비교")
    private fun compareNumber() {
        ball =0
        strike = 0
        nothing = 0

        for (i in randomNumber.indices) {
            val findIndexOf = this.playerInput.indexOf(this.randomNumber[i])
            if(i == findIndexOf) strike++
            if(i != findIndexOf && findIndexOf >= 0) ball++
            if(findIndexOf < 0) nothing++
        }

        printPoint()
    }

    @Description("점수 보여주기")
    private fun printPoint() {
        var printString = StringBuilder()
        if (ball != 0) printString.append("${ball}볼 ")
        if(strike != 0) printString.append("${strike}스트라이크 ")
        if(nothing != 0) printString.append("${nothing} 낫싱 ")
        println(printString)
        if(strike == 3) isCorrectAnswer()
    }

    @Description("3스트라이크일 경우")
    fun isCorrectAnswer() {
        if(this.randomNumber == this.playerInput) {
            println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
            this.correctAnswer = true
        }
    }

    @Description("랜덤 값 생성")
    private fun getRandomNumber() {
        var numberList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        var choiceNumber = StringBuilder()

        while(choiceNumber.length < 3) {
            val randomIndex = (0 until numberList.size).random()
            choiceNumber.append(numberList[randomIndex])
            numberList.removeAt(randomIndex)
        }
        this.randomNumber = choiceNumber.toString()
    }
}