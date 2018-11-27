def map = [a:1, b:2, c:2]
map.d = 3
println map
println map.keySet()
println map.values()
println map.entrySet()
map['e'] = 4
map.put('f', 3)
// map.each { println it }
// map.each { println "$it.key == $it.value"}
map.each { k,v -> println "map[$k] = $v"}

