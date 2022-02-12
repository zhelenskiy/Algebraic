package rings

import structures.CommutativeMonoid
import structures.CommutativeSemiringWithOne

object UInt32Semiring : CommutativeSemiringWithOne<UInt> {
    object Sum : CommutativeMonoid<UInt> {
        override val identity: UInt = 0U

        override fun invoke(arg1: UInt, arg2: UInt): UInt = arg1 + arg2
    }

    object Multi : CommutativeMonoid<UInt> {
        override fun invoke(arg1: UInt, arg2: UInt): UInt = arg1 * arg2

        override val identity: UInt = 1U
    }

    override val multi: Multi = Multi
    override val sum: Sum = Sum
}