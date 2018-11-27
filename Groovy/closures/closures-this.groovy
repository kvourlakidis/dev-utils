class Enclosing {
    void run() {
        def getThisObjectClosure = { getThisObject() }
        println getThisObjectClosure() == this
        def thisClosure = { this }
        println thisClosure() == this
    }
}
class OuterClass {
    class InnerClass {
        Closure cl = { this }
    }
    void run() {
        def ic = new InnerClass()
        println ic.cl() == this
        println ic.cl() == ic
    }
}
class NestedClosures {
    void run() {
        def outerClosure = {
            def innerClosure = { this }
            innerClosure()
        }
        println outerClosure() == this
        println outerClosure() == outerClosure
    }
}
println 'Enclosing'
new Enclosing().run()
println 'OuterClass'
new OuterClass().run()
println 'NestedClosures'
new NestedClosures().run()