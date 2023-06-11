package calculator

import calculator.model.CalculatorErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PositiveNumbersTest : StringSpec({

    "PositiveNumbers 생성 시 양수로 구성되면 정상 생성된다." {
        val expect = IntArray(10) { it }

        val positiveNumbers = PositiveNumbers(elements = expect)

        positiveNumbers.elements shouldBe expect
    }

    "PositiveNumbers 생성 시 깊은 복사가 적용된다." {
        val expect = IntArray(10) { it }

        val positiveNumbers = PositiveNumbers(elements = expect)
        expect[0] = 100

        positiveNumbers.elements shouldNotBe expect
    }

    "PositiveNumbers 생성 시 음수가 있을 경우 IllegalArgumentException 예외가 발생한다." {
        val expect = -1

        val exception = shouldThrow<IllegalArgumentException> {
            PositiveNumbers(
                elements = IntArray(10) { expect },
            )
        }

        exception shouldHaveMessage CalculatorErrorCode.INVALID_POSITIVE_NUMBERS.message("$expect")
    }
})
