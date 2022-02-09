fun interface Magma<T> {
    operator fun invoke(arg1: T, arg2: T): T
}

interface Associative
interface Commutative

interface WithIdentityElement<T> {
    val identity: T
}

interface Invertible<T> {
    fun T.inverse(): T
}