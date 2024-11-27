package org.pds.core

import core.Persistent

interface PersistentMap<K, V>: Persistent, Map<K, V> {}