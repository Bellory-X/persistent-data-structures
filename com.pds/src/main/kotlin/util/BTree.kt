package org.pds.util

class BTree<K : Comparable<K>, V>(private val t: Int) {

    private var root: BTreeNode<K, V> = BTreeNode(isLeaf = true, t = t)

    fun search(key: K): V? {
        var currentNode = root

        while (true) {
            // Найти позицию ключа или ближайшего большего ключа
            var i = 0
            while (i < currentNode.keys.size && key > currentNode.keys[i]) {
                i++
            }
            // Если нашли ключ, вернуть его значение
            if (i < currentNode.keys.size && currentNode.keys[i] == key) {
                return currentNode.values[i]
            }
            // Если достигли листа, ключ не найден
            if (currentNode.isLeaf) {
                return null
            }
            // Переход к подходящему дочернему узлу
            currentNode = currentNode.children[i]
        }
    }

    fun insert(key: K, value: V) {
        if (root.isFull()) {
            val newRoot = BTreeNode<K, V>(isLeaf = false, t = t)
            newRoot.children.add(root)
            splitChild(newRoot, 0)
            root = newRoot
        }
        var currentNode = root

        while (!currentNode.isLeaf) {
            var i = currentNode.keys.size - 1
            while (i >= 0 && key < currentNode.keys[i]) { i-- }
            i++
            if (currentNode.children[i].isFull()) {
                splitChild(currentNode, i)
                if (key > currentNode.keys[i]) { i++ }
            }
            currentNode = currentNode.children[i]
        }
        // Вставка в найденный лист
        var insertPos = currentNode.keys.size - 1
        while (insertPos >= 0 && key < currentNode.keys[insertPos]) { insertPos-- }
        currentNode.keys.add(insertPos + 1, key)
        currentNode.values.add(insertPos + 1, value)
    }

    private fun splitChild(parent: BTreeNode<K, V>, index: Int) {
        val fullNode = parent.children[index]
        val midIndex = t - 1
        val newNode = BTreeNode<K, V>(isLeaf = fullNode.isLeaf, t = t)

        parent.keys.add(index, fullNode.keys[midIndex])
        parent.values.add(index, fullNode.values[midIndex])
        parent.children.add(index + 1, newNode)

        newNode.keys.addAll(fullNode.keys.subList(midIndex + 1, fullNode.keys.size))
        newNode.values.addAll(fullNode.values.subList(midIndex + 1, fullNode.values.size))

        fullNode.keys.subList(midIndex, fullNode.keys.size).clear()
        fullNode.values.subList(midIndex, fullNode.values.size).clear()

        if (!fullNode.isLeaf) {
            newNode.children.addAll(fullNode.children.subList(midIndex + 1, fullNode.children.size))
            fullNode.children.subList(midIndex + 1, fullNode.children.size).clear()
        }
    }
}