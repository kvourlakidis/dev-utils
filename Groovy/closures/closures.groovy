// Formal syntax:
// { [closureParameters -> ] statements }
// Where [closureParameters -> ] is an optional comma-delimited list of
// parameters, and statements are 0 or more Groovy statements.
// The parameters look similar to a method parameter list, and these may
// be typed or untyped.
//
// Valid closures:
def c1 = { item++ } // accepts an optional argument
def c2 = { -> item++ } // accepts zero arguments
def c3 = { println it } // 'it' is implicit (can be null)
def c4 = { it -> println it } // 'it' is explicit
def c5 = { name -> println name }
def c6 = { String x, int y -> println "hey $x, the value is $y"}
def c61 = c6.curry('Kirill')
def c62 = c6.rcurry(3)
def c7 = { reader -> 
        def line = reader.readLine()
        line.trim()
    }

item = 0; 5.times {c1()}; println item
c3("using implicit 'it'")
c4("using explicit 'it'")
c5.call("using explicit 'name'")
c6("you", 42)

def c8 = { String... strings -> strings.join(";") }
println c8("one", "two", "three")

// composition 
def plus3 = { it + 3 }
def times2 = { it * 2 }
def times2plus3 = plus3 << times2
def plus3times2 = plus3 >> times2

println times2plus3(9)
println plus3times2(7)

// caching
def fib
fib = { it < 2 ? it : fib(it - 1) + fib(it - 2) }.memoize()
40.times { println "$it ${fib(it)}" }

