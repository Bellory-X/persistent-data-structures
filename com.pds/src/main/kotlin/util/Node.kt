package org.pds.util

interface Node<K, V> {

    fun key(): K

    fun value(key: K): V?
}