package structures

interface SemiRing<T> {
    val sum: CommutativeMonoid<T>
    val multi: Semigroup<T>
}

context(SemiRing<T>)
val <T> zero: T
    get() = sum.identity

context(SemiRing<T>)
operator fun <T> T.plus(other: T): T = sum(this, other)

context(SemiRing<T>)
operator fun <T> T.times(other: T): T = multi(this, other)

interface SemiringWithOne<T> : SemiRing<T> {
    override val multi: Monoid<T>
}

interface CommutativeSemiRing<T> : SemiRing<T> {
    override val multi: CommutativeSemigroup<T>
}

interface CommutativeSemiringWithOne<T> : SemiringWithOne<T>, CommutativeSemiRing<T> {
    override val multi: CommutativeMonoid<T>
}

interface Ring<T> : SemiRing<T> {
    override val sum: AbelGroup<T>
}

context(Ring<T>)
operator fun <T> T.unaryMinus(): T = with(sum) { inverse() }

context(Ring<T>)
operator fun <T> T.minus(other: T): T = sum(this, -other)

interface RingWithOne<T> : Ring<T>, SemiringWithOne<T>

context(SemiringWithOne<T>)
val <T> one: T
    get() = multi.identity

interface CommutativeRing<T> : Ring<T>, CommutativeSemiRing<T>

interface CommutativeRingWithOne<T> : RingWithOne<T>, CommutativeRing<T>, CommutativeSemiringWithOne<T>

// dividing by 0 is undefined and implementation defined
interface Field<T> : CommutativeRingWithOne<T> {
    override val multi: AbelGroup<T>
}

context (Field<T>)
fun <T> T.reciprocal() = with(multi) { inverse() }

context (Field<T>)
operator fun <T> T.div(divider: T) = multi(this, divider.reciprocal())
