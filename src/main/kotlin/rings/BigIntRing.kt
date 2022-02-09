package rings

import AbelGroup
import CommutativeMonoid
import CommutativeRingWithOne
import java.math.BigInteger

object BigIntRing : CommutativeRingWithOne<BigInteger, BigIntRing.Sum, BigIntRing.Multi> {
    object Sum : AbelGroup<BigInteger> {
        override val identity: BigInteger = BigInteger.ZERO

        override fun invoke(arg1: BigInteger, arg2: BigInteger): BigInteger = arg1 + arg2

        override fun BigInteger.inverse(): BigInteger = unaryMinus()
    }

    object Multi : CommutativeMonoid<BigInteger> {
        override fun invoke(arg1: BigInteger, arg2: BigInteger): BigInteger = arg1 * arg2

        override val identity: BigInteger = BigInteger.ONE
    }

    override val multi: Multi = Multi
    override val sum: Sum = Sum
}