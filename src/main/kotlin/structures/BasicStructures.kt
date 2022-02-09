interface QuasiGroup<T> : Magma<T>, Invertible<T>
interface UnitaryMagma<T> : Magma<T>, WithIdentityElement<T>
interface SemiGroup<T> : Magma<T>, Associative

interface CommutativeSemiGroup<T> : SemiGroup<T>, Commutative

interface Loop<T> : QuasiGroup<T>, UnitaryMagma<T>
interface InverseSemiGroup<T> : QuasiGroup<T>, SemiGroup<T>
interface Monoid<T> : SemiGroup<T>, UnitaryMagma<T>

interface CommutativeMonoid<T> : Monoid<T>, CommutativeSemiGroup<T>

interface Group<T> : Loop<T>, InverseSemiGroup<T>, Monoid<T>
interface AbelGroup<T> : Group<T>, CommutativeMonoid<T>

interface SemiRing<T, out Sum : CommutativeMonoid<T>, out Multi : SemiGroup<T>> {
    val sum: Sum
    val multi: Multi
}

context(SemiRing<T, CommutativeMonoid<T>, *>)
val <T> zero: T
    get() = sum.identity

context(SemiRing<T, CommutativeMonoid<T>, *>)
operator fun <T> T.plus(other: T): T = sum(this, other)

context(SemiRing<T, *,  SemiGroup<T>>)
operator fun <T> T.times(other: T): T = multi(this, other)

interface SemiRingWithOne<T, out Sum : CommutativeMonoid<T>, out Multi : Monoid<T>> : SemiRing<T, Sum, Multi>
interface CommutativeSemiRing<T, out Sum : CommutativeMonoid<T>, out Multi : CommutativeSemiGroup<T>> : SemiRing<T, Sum, Multi>
interface CommutativeSemiRingWithOne<T, out Sum : CommutativeMonoid<T>, out Multi : CommutativeMonoid<T>> :
    SemiRingWithOne<T, Sum, Multi>, CommutativeSemiRing<T, Sum, Multi>

interface Ring<T, out Sum : AbelGroup<T>, out Multi : SemiGroup<T>> : SemiRing<T, Sum, Multi>

context(Ring<T, AbelGroup<T>, *>)
operator fun <T> T.unaryMinus(): T = with(sum) { inverse() }

context(Ring<T, AbelGroup<T>, *>)
operator fun <T> T.minus(other: T): T = sum(this, -other)

interface RingWithOne<T, out Sum : AbelGroup<T>, out Multi : Monoid<T>> : Ring<T, Sum, Multi>, SemiRingWithOne<T, Sum, Multi>

context(RingWithOne<T, *, Monoid<T>>)
val <T> one: T
    get() = multi.identity

interface CommutativeRing<T, out Sum : AbelGroup<T>, out Multi : CommutativeSemiGroup<T>> : Ring<T, Sum, Multi>

interface CommutativeRingWithOne<T, out Sum : AbelGroup<T>, out Multi : CommutativeMonoid<T>> :
    RingWithOne<T, Sum, Multi>, CommutativeRing<T, Sum, Multi>

// dividing by 0 is undefined and implementation defined
interface Field<T, out Sum : AbelGroup<T>, out Multi : AbelGroup<T>> : CommutativeRingWithOne<T, Sum, Multi>

context (Field<T, *, AbelGroup<T>>)
fun <T> T.reciprocal() = with(multi) { inverse() }

context (Field<T, *, AbelGroup<T>>)
operator fun <T> T.div(divider: T) = multi(this, divider.reciprocal())
