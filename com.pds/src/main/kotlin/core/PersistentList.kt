package org.pds.core

import core.Persistent

interface PersistentList<T>: Persistent, List<T> {}