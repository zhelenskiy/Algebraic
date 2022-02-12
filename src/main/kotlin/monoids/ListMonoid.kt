package monoids

import structures.Monoid

class ListMonoid<T> : Monoid<List<T>> {
    override fun invoke(arg1: List<T>, arg2: List<T>): List<T> = arg1 + arg2

    override val identity: List<T> = emptyList()

}