import groovy.transform.*

//@ToString
//@EqualsAndHashCode
//@TupleConstructor
@Canonical
class Person {
    String first
    int age
    String last
    
//    String toString() { "$first $last"}
}

def per1 = new Person(first: "John", last: "Grisham", age: 5)
def per2 = new Person(first: "John", last: "Grisham", age: 5)
def per3 = new Person("John", 10, "Grisham")

println per1

println per1 == per2