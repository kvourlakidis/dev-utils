class Enclosing {
    void run() {
        def getOwnerClosure = { getOwner() }
        println getOwnerClosure() == this
        def ownerClosure = { owner }
        println ownerClosure() == this
    }
}
class OuterClass {
    class InnerClass {
        Closure cl = { owner }
    }
    void run() {
        println new InnerClass().cl() == this
    }
}
class NestedClosures {
    void run() {
        def outerClosure = {
            def innerClosure = { owner }
            innerClosure()
        }
        println outerClosure() == this
        println outerClosure() == outerClosure
    }
}
new Enclosing().run()
new OuterClass().run()
new NestedClosures().run()