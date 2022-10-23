package baseballgame.domain

import baseballgame.utils.baseBallInputValid
import baseballgame.utils.baseBallResetValid
import jdk.jfr.Description

class BaseBall {

    @Description("컴퓨터가 지정할 랜덤 값")
    var randomNumber: String = "123"

    @Description("정답 유무")
    var correctAnswer = false

    @Description("정답일 경우 재시작 유무")
    var reStart = true

    @Description("플레이어 입력 값")
    var playerInput: String = ""
        set(value) {
            value baseBallInputValid(value)
        }

    @Description("정답일 경우 재시작 입력 값(1, 2)")
    var restartAnswer: String? = null
        set(value) {
            if (value == "2") reStart = false
            field = value
        }

    @Description("게임을 다시 시작시 호출")
    fun isRestartBaseBall(input: String) {
        input baseBallResetValid(input)
        restartAnswer = input
        resetBaseBall()
    }

    @Description("모든게임 초기화")
    fun resetBaseBall() {
        this.randomNumber = "123"
        this.correctAnswer = false
    }

    @Description("플레이어 입력값과의 비교")
    fun isCorrectAnswer() {
        if(this.randomNumber == this.playerInput) {
            this.correctAnswer = true
        }
    }
}