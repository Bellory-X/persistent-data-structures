package org.pds.util

data class PathNode<V>(
    val maxKey: Int,
    val leftChild: Node<Int, V>,
    val rightChild: Node<Int, V>
): Node<Int, V> {

    override fun key(): Int = maxKey

    override fun value(key: Int): V? = when {
        key > maxKey -> null
        key <= leftChild.key() -> leftChild.value(key)
        else -> rightChild.value(key)
    }
}