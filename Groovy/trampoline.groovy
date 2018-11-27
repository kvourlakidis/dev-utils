def factorial

// factorial = { int n -> 
//     if (n < 2) return n
//     else return n * factorial(n-1)
// }

factorial = { int n, def accu = 1G -> 
    if (n < 2) return accu
    factorial.trampoline(n-1, n * accu)
}
factorial = factorial.trampoline()

20.times { println "$it ${factorial(it)}" }