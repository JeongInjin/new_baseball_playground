package baseballgame.code

enum class MESSAGE(val desc: String) {
    INPUT_BLANK("빈값은 허용되지 않습니다."),
    INPUT_SIZE_IS_NOT_THREE_DIGITS("입력은 3자리 숫자(0 제외)로 해주세요."),
    INPUT_SIZE_IS_NOT_ONE_DIGITS("입력은 1자리 숫자(1 또는 2)로 해주세요."),
    IS_NOT_NUMERIC("오직 숫자만 입력 가능합니다."),
    INPUT_VALUE_ZERO("입력 값은 0일 수 없습니다."),
    RESET_ONLY_1_2_ALLOWED("입력 값은 1 또는 2 만 입력 가능합니다."),
}