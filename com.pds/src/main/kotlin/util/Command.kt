package org.pds.util

import org.pds.core.PersistentArray

interface Command<T> {
    fun redo(): PersistentArray<T>
    fun undo(): PersistentArray<T>
}