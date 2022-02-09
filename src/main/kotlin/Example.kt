import fields.BigDecimalField
import fields.DoubleField
import fields.FloatField
import rings.BigIntRing
import rings.Int64Ring
import rings.UInt32SemiRing

context(SemiRing<T, *, *>)
fun <T> Iterable<T>.abstractSum(): T {
    var res = zero
    for (element in this) {
        res += element
    }
    return res
}

fun main() {
    with(DoubleField) {
        println(listOf(1.0, 2.0, 3.0, 4.0).abstractSum())
    }
    with(FloatField) {
        println(listOf(1.0F, 2.0F, 3.0F, 4.0F).abstractSum())
    }
    with(BigDecimalField(/* default precision*/)) {
        println(listOf(1.0, 2.0, 3.0, 4.0).map { it.toBigDecimal() }.abstractSum())
    }
    with(Int64Ring) {
        println(listOf(1L, 2L, 3L, 4L).abstractSum())
    }
    with(UInt32SemiRing) {
        println(listOf(1U, 2U, 3U, 4U).abstractSum())
    }
    with(BigIntRing) {
        println(listOf(1, 2, 3, 4).map { it.toBigInteger() }.abstractSum())
    }
}