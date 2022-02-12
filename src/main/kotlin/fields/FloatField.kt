package fields

import structures.AbelGroup
import structures.Field

object FloatField : Field<Float> {
    object Sum : AbelGroup<Float> {
        override fun invoke(arg1: Float, arg2: Float): Float = arg1 + arg2

        override val identity: Float = 0.0F

        override fun Float.inverse(): Float = unaryMinus()
    }

    object Multi : AbelGroup<Float> {
        override fun invoke(arg1: Float, arg2: Float): Float = arg1 * arg2

        override val identity: Float = 1.0F

        override fun Float.inverse(): Float = 1.0F / this

    }

    override val sum: Sum = Sum
    override val multi: Multi = Multi
}