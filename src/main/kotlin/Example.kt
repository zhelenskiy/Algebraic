
import fields.BigDecimalField
import fields.DoubleField
import fields.FloatField
import monoids.Endofunctor
import monoids.ListMonoid
import monoids.Monad
import rings.*
import structures.Semiring
import structures.foldl
import structures.plus
import structures.zero

context(Semiring<T>)
fun <T> Iterable<T>.abstractSum(): T = foldl(sum)

// same to function above
context(Semiring<T>)
fun <T> Iterable<T>.abstractSum1(): T {
    var res = zero
    for (element in this) {
        res += element
    }
    return res
}

fun main() {
    with(DoubleField) {
        println(listOf(1.0, 2.0, 3.0, 4.0).abstractSum())
        println(10.0.power(3U))
        println(10.0.power(-3))
        println(10.0.power(3))
        println(10.0.power(0))
        println(10.0.power(0U))
    }
    with(FloatField) {
        println(listOf(1.0F, 2.0F, 3.0F, 4.0F).abstractSum())
    }
    with(BigDecimalField(/* default precision*/)) {
        println(listOf(1.0, 2.0, 3.0, 4.0).map { it.toBigDecimal() }.abstractSum())
    }
    with(UInt64Semiring) {
        println(listOf(1UL, 2UL, 3UL, 4UL).abstractSum())
    }
    with(Int64Ring) {
        println(listOf(1L, 2L, 3L, 4L).abstractSum())
    }
    with(UInt32Semiring) {
        println(listOf(1U, 2U, 3U, 4U).abstractSum())
        println(10U.power(3U))
        println(10U.power(0U))
    }
    with(Int32Ring) {
        println(listOf(1, 2, 3, 4).abstractSum())
    }
    with(BigIntRing) {
        println(listOf(1, 2, 3, 4).map { it.toBigInteger() }.abstractSum())
    }
    val actions = listOf<Endofunctor<Int>>(
        { it + 2 },
        { it * 3 },
        { it - 4 },
    )
    println(actions.foldl(Monad())(10))
    val numbers = (1..4).toList()
    with(Int32Ring) {
        println(numbers.foldl(sum))
        println(numbers.foldl(multi))
    }
    val printers = List<Endofunctor<Unit>>(5) { i -> { println(i) } }
    printers.foldl(Monad())(Unit)
    println(ListMonoid<Nothing>().identity)
}