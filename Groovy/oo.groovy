import groovy.transform.*

@ToString(includeNames = true) // AST transformation
@EqualsAndHashCode // AST transformation
@TupleConstructor // AST transformation
// the above 3 transforms can be replaced
// by @Canonical
class Person {
	String first
	String last
}

kv = new Person()
kv.setFirst('Kirill')
kv.last = 'Vourlakidis'
kv2 = new Person(first: 'Kirill', last:'Vourlakidis')
tb = new Person(first: 'Tom', last: 'Brady')

println kv.equals(kv2)
println kv.hashCode()
