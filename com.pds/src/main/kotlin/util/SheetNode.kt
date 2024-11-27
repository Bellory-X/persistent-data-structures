package org.pds.util

data class SheetNode<V>(
    private val key: Int,
    private val value: V
): Node<Int, V> {

    override fun key(): Int = key

    override fun value(key: Int): V? = value
}