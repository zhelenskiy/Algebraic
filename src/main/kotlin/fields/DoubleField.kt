package fields

import AbelGroup
import Field

object DoubleField : Field<Double, DoubleField.Sum, DoubleField.Multi> {
    object Sum : AbelGroup<Double> {
        override fun invoke(arg1: Double, arg2: Double): Double = arg1 + arg2

        override val identity: Double = 0.0

        override fun Double.inverse(): Double = unaryMinus()
    }

    object Multi : AbelGroup<Double> {
        override fun invoke(arg1: Double, arg2: Double): Double = arg1 * arg2

        override val identity: Double = 1.0

        override fun Double.inverse(): Double = 1.0 / this

    }

    override val sum: Sum = Sum
    override val multi: Multi = Multi

}