package rings

import structures.AbelGroup
import structures.CommutativeMonoid
import structures.CommutativeRingWithOne

object Int32Ring : CommutativeRingWithOne<Int> {
    object Sum : AbelGroup<Int> {
        override val identity: Int = 0

        override fun invoke(arg1: Int, arg2: Int): Int = arg1 + arg2

        override fun Int.inverse(): Int {
            require(this != Int.MIN_VALUE) { "-(${Int.MIN_VALUE}) is out of Int32" }
            return unaryMinus()
        }
    }

    object Multi : CommutativeMonoid<Int> {
        override fun invoke(arg1: Int, arg2: Int): Int = arg1 * arg2

        override val identity: Int = 1
    }

    override val multi: Multi = Multi
    override val sum: Sum = Sum
}