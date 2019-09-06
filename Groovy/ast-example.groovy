#!/usr/bin/env groovy

import groovy.transform.*

// @ToString
// @EqualsAndHashCode
// @TupleConstructor
@Canonical
class Person {
    String first
    int age
    String last
    
    public void inspect() {
        println this
    }
}

def per1 = new Person(first: "John", last: "Grisham", age: 5)
def per2 = new Person(first: "John", last: "Grisham", age: 5)
def per3 = new Person("John", 10, "Grisham")

per1.inspect()
