class Enclosing {
    void run() {
        def c1 = { getDelegate() }
        def c2 = { delegate }
        println c1() == c2()
        println c1() == this
        def enclosed = {
            { -> delegate }.call()
        }
        println enclosed() == enclosed
    }
}

new Enclosing().run()

class Person {
    String name;
}
class Animal {
    String name;
}
def p = new Person(name: 'Dave')
def a = new Animal(name: 'Pixie')

def upperCasedName = { name.toUpperCase() } // same as: { delegate.name.toUpperCase() }

upperCasedName.delegate = p
println upperCasedName()
upperCasedName.delegate = a
println upperCasedName()