package org.pds.util

class BTreeNode<K : Comparable<K>, V>(
    var isLeaf: Boolean,
    val t: Int // Минимальный фактор (половина максимальной степени)
) {
    val keys: MutableList<K> = mutableListOf()
    val values: MutableList<V> = mutableListOf()
    val children: MutableList<BTreeNode<K, V>> = mutableListOf()

    fun isFull() = keys.size == 2 * t - 1

    override fun toString(): String {
        return "Node(keys=$keys, values=$values, children=${if (isLeaf) "[]" else children})"
    }
}