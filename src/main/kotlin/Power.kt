context (SemiRing<T, *, SemiGroup<T>>)
@Suppress("UNCHECKED_CAST")
fun <T> T.power(exponent: ULong): T = when {
    exponent == 0UL ->
        if (this@power is RingWithOne<*, *, *>) with(this@power as RingWithOne<T, *, Monoid<T>>) { one }
        else throw IllegalArgumentException("${this@power} is not a ring with 1")
    exponent == 1UL -> this
    (exponent % 2UL) == 0UL -> power(exponent / 2UL).let { it * it }
    else -> this * power(exponent - 1UL)
}

context (Field<T, *, AbelGroup<T>>)
fun <T> T.power(exponent: Long): T = when {
    exponent == Long.MIN_VALUE -> power(Long.MAX_VALUE.toULong().inc()).reciprocal()
    exponent < 0L -> power((-exponent).toULong()).reciprocal()
    else -> power(exponent.toULong())
}