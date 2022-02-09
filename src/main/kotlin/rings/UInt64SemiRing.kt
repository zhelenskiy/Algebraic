package rings

import CommutativeMonoid
import CommutativeSemiRingWithOne

object UInt64SemiRing : CommutativeSemiRingWithOne<ULong, UInt64SemiRing.Sum, UInt64SemiRing.Multi> {
    object Sum : CommutativeMonoid<ULong> {
        override val identity: ULong = 0UL

        override fun invoke(arg1: ULong, arg2: ULong): ULong = arg1 + arg2
    }

    object Multi : CommutativeMonoid<ULong> {
        override fun invoke(arg1: ULong, arg2: ULong): ULong = arg1 * arg2

        override val identity: ULong = 1UL
    }

    override val multi: Multi = Multi
    override val sum: Sum = Sum
}