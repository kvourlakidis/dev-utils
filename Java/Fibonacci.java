/*
    The Fibonacci sequence is defined as follows:
    F(0) = 0; F(1) = 1; F(n)= F(n-1) + F(n-2)

    The largest term which fits into an int is:
    F(46) = 1836311903;

    The largest term which fits into a long is:
    F(92) = 7540113804746346429;

    The largest term which fits into a float is:
    F(186) = 3.3282503 x 10^38 (using float)
    F(186) = 3.328251100870676 x 10^38 (using double)
    F(186) = 3.32825110087067562321196029789634457848 x 10^38 (exact)

    The largest term which fits into a double is:
    F(1476) = 1.3069892237633987x10^308 (approximate)

    An interesting hypothesis to test is whether the 
    floating-point results are ever out by more than
    1 part in 10^(n-1) where n is the decimal precision
    (i.e. 1 part in 10^6 for floats and 1 part in 10^14 for doubles).

    It would also be good to understand how the error
    behaves with the cardinality of the output. 

    Further reading:
    https://en.wikipedia.org/wiki/IEEE_754
*/

public class Fibonacci {
    public static void main(String[] args) {
        long a = 0, b = 1;
        for (int i=0;i<90;i+=2) {
            System.out.println(i + ": " + a);
            System.out.println(i+1 + ": " + b);
            a = a + b;
            b = a + b;
        }
    }
}