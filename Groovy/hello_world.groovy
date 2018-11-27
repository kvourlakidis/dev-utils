// Groovy automatic imports:
import java.lang.*
import java.util.*
import java.net.*
import java.io.*
import java.math.BigInteger
import java.math.BigDecimal
import groovy.lang.*
import groovy.util.*

println "Welcome to Groovy (Version: $GroovySystem.version)" // org.codehaus.groovy.runtime.InvokerHelper.version

println "This machine: $InetAddress.localHost"
// To execute from command line:
// $groovy -e "println InetAddress.localHost"

// Running groovy from java:
// TODO (cover groovy, groovyc, java, javap)

// dynamic typing with 'def'
def z = 1
println z.getClass().getName() // java.lang.Integer
z = 'abc'
println z.getClass().getName() // java.lang.String
z = new Date()
println z.getClass().getName() // java.util.Date

// wrapper types
3.getClass().getName() // java.lang.Integer
3.14.getClass().getName() // java.math.BigDecimal
12345678901234567890123456.getClass().getName() // java.math.BigInteger

// 'double' error
println 2.0d - 1.1d   // 0.8999999999999999
println 2.0 - 1.1     // 0.9
println 3/5

// asserts
if (false) {
    println 'Example assert output:'
    int x = 3, y = 4
    assert 7 == x + y + 1 // power assert from sparc (powerful debug output)    
}


