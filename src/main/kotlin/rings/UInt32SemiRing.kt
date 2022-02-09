package rings

import CommutativeMonoid
import CommutativeSemiRingWithOne

object UInt32SemiRing : CommutativeSemiRingWithOne<UInt, UInt32SemiRing.Sum, UInt32SemiRing.Multi> {
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