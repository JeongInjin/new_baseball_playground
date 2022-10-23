package baseballgame.utils

import baseballgame.code.MESSAGE
import baseballgame.code.MESSAGE.*

fun throwIllegalArgumentException(message: MESSAGE? = null): Nothing =
    throw IllegalArgumentException(message?.desc)

infix fun String.baseBallInputValid(input: String) {
    inputValidated(input)
}

infix fun String.baseBallResetValid(input: String) {
    resetValidated(input)
}

fun inputValidated(input: String) {
    isBlankCheck(input)
    inputSizeThreeDigits(input)
    for (c in input.toCharArray()) {
        numericCheck(c)
        inputZeroCheck(c)
    }
}

fun resetValidated(input: String) {
    isBlankCheck(input)
    inputSizeOneDigits(input)
    checkResetNumber(input)
}

private fun inputZeroCheck(c: Char) {
    if (c.digitToInt() == 0) throwIllegalArgumentException(INPUT_VALUE_ZERO)
}

private fun numericCheck(c: Char) {
    c.digitToIntOrNull() ?: throwIllegalArgumentException(IS_NOT_NUMERIC)
}

private fun inputSizeThreeDigits(input: String) {
    if (input.toCharArray().size != 3) throwIllegalArgumentException(INPUT_SIZE_IS_NOT_THREE_DIGITS)
}

private fun inputSizeOneDigits(input: String) {
    if (input.toCharArray().size != 1) throwIllegalArgumentException(INPUT_SIZE_IS_NOT_ONE_DIGITS)
}

private fun isBlankCheck(input: String) {
    if (input.isBlank()) throwIllegalArgumentException(INPUT_BLANK)
}

private fun checkResetNumber(input: String) {
    if(input != "1" && input != "2") throwIllegalArgumentException(RESET_ONLY_1_2_ALLOWED)
}
