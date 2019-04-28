package org.generic

class MetaCode implements Serializable {
    static void baseBuiltinTypes() {
        //(2..20).findAll { it.isPrime() }
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

        //Map a = [foo: 'FOO', bar: 'BAR', baz: 'BAZ'];   def test1 = a.asMap()
        LinkedHashMap.metaClass.cHeader = { ->
            def result = []
            def arg = delegate as Map
            arg.each { key, val ->
                result.add([name:key, value:val])
            }
            return result
        }
    }
}
