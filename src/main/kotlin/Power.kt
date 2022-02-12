import structures.*

context (Semiring<T>)
@Suppress("UNCHECKED_CAST")
fun <T> T.power(exponent: ULong): T = when {
    exponent == 0UL -> {
        val newRing = this@Semiring as? SemiringWithOne<T> // bug this@power = this somewhy
            ?: throw IllegalArgumentException("${this@Semiring} is not a ring with 1")
        with(newRing) { one }
    }
    exponent == 1UL -> this
    (exponent % 2UL) == 0UL -> power(exponent / 2UL).let { it * it }
    else -> this * power(exponent - 1UL)
}

context (Field<T>)
fun <T> T.power(exponent: Long): T = when {
    exponent == Long.MIN_VALUE -> power(Long.MAX_VALUE.toULong().inc()).reciprocal()
    exponent < 0L -> power((-exponent).toULong()).reciprocal()
    else -> power(exponent.toULong())
}