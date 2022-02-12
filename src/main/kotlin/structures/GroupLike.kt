package structures

interface CommutativeMagma<T> : Magma<T>, Commutative
interface Quasigroup<T> : Magma<T>, Invertible<T>
interface UnitaryMagma<T> : Magma<T>, WithIdentityElement<T>
interface Semigroup<T> : Magma<T>, Associative

interface CommutativeQuasigroup<T> : Quasigroup<T>, CommutativeMagma<T>
interface CommutativeUnitaryMagma<T> : UnitaryMagma<T>, CommutativeMagma<T>
interface CommutativeSemigroup<T> : Semigroup<T>, CommutativeMagma<T>

interface Loop<T> : Quasigroup<T>, UnitaryMagma<T>
interface InverseSemigroup<T> : Quasigroup<T>, Semigroup<T>
interface Monoid<T> : Semigroup<T>, UnitaryMagma<T>

fun <T> Iterable<T>.foldl(x: Monoid<T>): T = foldl(x.identity, x)

fun <T> Iterable<T>.foldl(init: T, x: Semigroup<T>): T {
    var res = init
    for (item in this) {
        res = x(res, item)
    }
    return res
}

interface CommutativeLoop<T> : Loop<T>, CommutativeQuasigroup<T>, CommutativeUnitaryMagma<T>
interface CommutativeInverseSemigroup<T> : InverseSemigroup<T>, CommutativeQuasigroup<T>, CommutativeSemigroup<T>
interface CommutativeMonoid<T> : Monoid<T>, CommutativeSemigroup<T>, CommutativeUnitaryMagma<T>

interface Group<T> : Loop<T>, InverseSemigroup<T>, Monoid<T>
interface CommutativeGroup<T> : Group<T>, CommutativeLoop<T>, CommutativeInverseSemigroup<T>, CommutativeMonoid<T>
typealias AbelGroup<T> = CommutativeGroup<T>