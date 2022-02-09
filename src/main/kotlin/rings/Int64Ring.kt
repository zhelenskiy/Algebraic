package rings

import AbelGroup
import CommutativeMonoid
import CommutativeRingWithOne

object Int64Ring : CommutativeRingWithOne<Long, Int64Ring.Sum, Int64Ring.Multi> {
    object Sum : AbelGroup<Long> {
        override val identity: Long = 0

        override fun invoke(arg1: Long, arg2: Long): Long = arg1 + arg2

        override fun Long.inverse(): Long {
            require(this != Long.MIN_VALUE) { "-(${Long.MIN_VALUE}) is out of Long32" }
            return unaryMinus()
        }
    }

    object Multi : CommutativeMonoid<Long> {
        override fun invoke(arg1: Long, arg2: Long): Long = arg1 * arg2

        override val identity: Long = 1
    }

    override val multi: Multi = Multi
    override val sum: Sum = Sum
}