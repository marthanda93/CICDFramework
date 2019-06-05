package org.generic

import java.util.LinkedHashMap
import java.util.Set
import com.cloudbees.groovy.cps.NonCPS

import java.util.regex.Matcher
import java.util.regex.Pattern

class MetaCode implements Serializable {

    @NonCPS
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
            
            Map<String, String> hashMap = delegate as Map<String, String>
            Set<String> keys = hashMap.keySet();
            
            for(String key:keys){
                result.add([name:key, value:hashMap.get(key)])
            }

            return result
        }

        String.metaClass.stripSlash = {
            return delegate.replaceAll("/+", "/");
        }

        String.metaClass.fnameFromPath = {
            return delegate.split('/')[-1];
        }

        String.metaClass.MStringTemplateEngine = { Map parameter ->
            String strObj = delegate as String
            String prefixPattern = ''
            String prefix = ''
            String key = ''

            if((strObj =~ /\$\{.+?\}/).size() > 0) {
                prefixPattern = /\$\{.+?\}/
                prefix = /\$\{([^\}]*)/
            } else if((strObj =~ /\{\{.+?\}\}/).size() > 0) {
                prefixPattern = /\{\{.+?\}\}/
                prefix = /\{\{([^\}\}]*)/
            }
            
            Matcher patternMatcher = Pattern.compile(prefixPattern).matcher(strObj)

            if(patternMatcher.size() > 0) {
                
                patternMatcher[0..-1].unique().each{ keyWithPrefix ->
                    key = (keyWithPrefix=~prefix).collect{it[1]}[0] as String
                    
                    strObj = strObj.replaceAll(keyWithPrefix.replaceAll(/(\{|\}|\$)/, /\\$0/), parameter[key.trim()])
                }
            }
            return strObj
        }

        String.metaClass.MsubSplit = {
            List item = []
            List path = delegate.split("(/\\{|\\}/)") as String[];

            path.eachWithIndex{ key, index ->
                item = key.split(",|\\s")
                
                if(item.size() > 1) {
                    item.removeAll(Arrays.asList(null,""));
                    path[index] = item
                }
            }

            return path
        }

        //List tmp = ['/path/env', 'anand', ['one','two'], 'app.properties',['three', 'four'], 'kumar']
        List.metaClass.MsubListjoin = { ->
            List list = []
            List path = []
            Integer i = 0
            Boolean sublist = true

            delegate.eachWithIndex { key, index ->
                if(key instanceof java.util.List) {
                    i = 0
                    key.each{
                        if(sublist == true) {
                            path << (list + [it])
                        } else {
                            for(List item in path) {
                                path[i] << it
                                i = i + 1
                                break;
                            }
                        }
                    }
                    sublist = false
                } else {
                    if(sublist == false) {
                        path.each {
                            it << key
                        }
                    } else {
                        list << key
                    }
                }
            }

            return path
        }
    }
}
