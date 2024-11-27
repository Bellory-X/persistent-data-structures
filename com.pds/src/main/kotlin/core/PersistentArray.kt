package org.pds.core

import org.pds.util.PathNode

class PersistentArray<V>(private val size: Int) {

    private var root: PathNode<V>? = null;

    operator fun get(index: Int): V? = root?.value(index)

    operator fun set(index: Int, value: V) {

    }
}
