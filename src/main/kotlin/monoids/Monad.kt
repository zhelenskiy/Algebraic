package monoids

import structures.Monoid

typealias Endofunctor<T> = (T) -> T

class Monad<T>: Monoid<Endofunctor<T>> {
    override fun invoke(arg1: Endofunctor<T>, arg2: Endofunctor<T>): Endofunctor<T> = { arg1(arg2(it)) }

    override val identity: Endofunctor<T> = { it }
}