package baseballgame.utils

import baseballgame.code.MESSAGE
import baseballgame.code.MESSAGE.*

fun throwIllegalArgumentException(message: MESSAGE? = null): Nothing =
    throw IllegalArgumentException(message?.desc)

infix fun String.baseBallInputValid(input: String) {
    inputValidated(input)
}

fun inputValidated(input: String) {
    if(input.isBlank()) throwIllegalArgumentException(INPUT_BLANK)
    if(input.toCharArray().size != 3) throwIllegalArgumentException(INPUT_SIZE_IS_NOT_THREE_DIGITS)
    for (c in input.toCharArray()) {
        c.digitToIntOrNull()?: throwIllegalArgumentException(IS_NOT_NUMERIC)
        if(c.digitToInt() == 0) throwIllegalArgumentException(INPUT_VALUE_ZERO)
    }
}
