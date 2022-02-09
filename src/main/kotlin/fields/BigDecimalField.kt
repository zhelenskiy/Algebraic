package fields

import AbelGroup
import Field
import java.math.BigDecimal
import java.math.MathContext

class BigDecimalField(val mathContext: MathContext = MathContext.UNLIMITED) :
    Field<BigDecimal, BigDecimalField.Sum, BigDecimalField.Multi> {
    inner class Sum : AbelGroup<BigDecimal> {
        override fun invoke(arg1: BigDecimal, arg2: BigDecimal): BigDecimal = arg1.add(arg2, mathContext)

        override val identity: BigDecimal = 0.0.toBigDecimal(mathContext)

        override fun BigDecimal.inverse(): BigDecimal = negate(mathContext)
    }

    inner class Multi : AbelGroup<BigDecimal> {
        override fun invoke(arg1: BigDecimal, arg2: BigDecimal): BigDecimal = arg1.multiply(arg2, mathContext)

        override val identity: BigDecimal = 1.0.toBigDecimal(mathContext)

        override fun BigDecimal.inverse(): BigDecimal = identity.divide(this, mathContext)

    }

    override val sum: Sum = Sum()
    override val multi: Multi = Multi()
}