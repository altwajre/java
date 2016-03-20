def nums = [3, 1, 4, 1, 5, 9, 5]
println nums // [3, 1, 4, 1, 5, 9, 5]
println nums.class.name // java.util.ArrayList

def set = [ 3, 1, 4, 1, 5, 9, 5] as Set
println set // [3, 1, 4, 5, 9]
println set.class.name // java.util.LinkedHashSet

for (n in set) print n // 31459
println '\nclosures below: it works because of closures default one arg "it"'
set.each {print it} // 31459
println '\nexplicit arg "n"'
set.each { n -> print n} // 31459
println '\neachWithIndex()'
set.eachWithIndex{ n, idx ->
    println "nums[$idx] == $n"
}

// DO NOT do following because declare outside of closures and modify inside of closures.
def doubles = []
nums.each { doubles << it * 2 }
println doubles // [6, 2, 8, 2, 10, 18, 10]

// SHOULD use functional programming below
println nums.collect { it * 2 }.findAll{it % 3 == 0}.sum(); // 24

def names = ['Tom', 'Dick', 'Harry']
println names.collect{ it.toLowerCase() } // [tom, dick, harry]

def map = [a:1, b:2, c:2]
map.d = 1
map['e'] = 2
map.put('f',3)
println map // [a:1, b:2, c:2, d:1, e:2, f:3]
println map.keySet() // [a, b, c, d, e, f]
println map.values() // [1, 2, 2, 1, 2, 3]
println map.entrySet() // [a=1, b=2, c=2, d=1, e=2, f=3]
map.each { e -> print "map[${e.key}] == ${e.value}, "} // map[a] == 1, map[b] == 2, map[c] == 2, map[d] == 1, map[e] == 2, map[f] == 3,
println ''
map.each { k,v -> print "map[$k] == $v, " } // map[a] == 1, map[b] == 2, map[c] == 2, map[d] == 1, map[e] == 2, map[f] == 3,
