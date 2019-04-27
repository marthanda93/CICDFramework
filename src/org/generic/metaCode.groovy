package org.generic

Number.metaClass.isPrime = { ->
    Integer x = delegate as Integer
    if (x == 2) return true
    boolean result = true
    int limit = Math.sqrt(x) + 1
    for (n in 2..limit) {
        if (x % n == 0) {
            result = false
            break
        }
    }
    return result
}