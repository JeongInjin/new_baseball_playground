package baseballgame.domain

import baseballgame.utils.baseBallResetValid

class BaseBall {

    var ramdomNumber: String = "123"
    lateinit var playerInput: String
    var correctAnswer = false


    var reStart = true
    var answerValue: String? = null
        set(value) {
            if (value == "2") reStart = false
            field = value
        }

    fun isRestartBaseBall(input: String) {
        input baseBallResetValid(input)
        answerValue = input
        resetBaseBall()
    }

    fun resetBaseBall() {
        this.ramdomNumber = "123"
        this.correctAnswer = false
    }

    fun isCorrectAnswer() {
        if(this.ramdomNumber == this.playerInput) {
            this.correctAnswer = true
        }
    }
}