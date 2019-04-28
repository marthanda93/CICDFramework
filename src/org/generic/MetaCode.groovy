package org.generic

import java.util.LinkedHashMap
import java.util.Set

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
            
            Map hashMap = delegate;
            def arg = delegate as LinkedHashMap
            Set<String> keys = arg.keySet();
            
            for(String key:keys){
                result.add([name:key, value:arg.get(key)])
            }

            return result
        }
    }
}
