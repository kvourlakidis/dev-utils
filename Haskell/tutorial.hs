-- this is a comment
{-
this is also 
a comment
-}
import Data.List
import System.IO


main = do
    putStrLn "What's your name?"
    name <- getLine
    putStrLn ( "Hello " ++ name)

-- Types: Int Integer Float Double Bool Char Tuple
maxInt = maxBound :: Int
minInt = minBound :: Int
bigFloat = 3.999999 + 0.0000001

-- arithmetic
num5 :: Int
num5 = 2 + 3
numNe1 = 2 + (-3)
num6 = 2 * 3 :: Int
num7 = 14 / 2
num2 = mod 8 3
num1 = 7 `mod` 3
num81 = 9 ** 2

-- misc math functions
{- 
pi exp log truncate round ceiling floor 
sin cos tan asin atan acos sinh
tanh cosh asinh atanh acosh
-}

-- type conversion
num3 = sqrt (fromIntegral num5)

-- sum a list
sumTo1k = sum [1..1000]

-- logical
trueAndFalse = True && False
trueOrFalse = True || False
notTrue = not(True)

-- lists
somePrimes = [3,5,7,11,13]
somePrimesSize = length somePrimes
somePrimesRev = reverse somePrimes
somePrimesIsEmpty = null somePrimes
somePrimesThird = somePrimes !! 2
somePrimesFirst = head somePrimes
somePrimesLast = last somePrimes

morePrimes = somePrimes ++ [17,19,23,29]
someNums = 2 : 7 : 21 : 66 :[]
multiList = [somePrimes, morePrimes]
zeroToTne = [0..10]
evenList = [2,5 .. 20]
everyOtherLetter = ['A', 'C' .. 'Z']
infinPow10 = [10,20..] -- do not print contents
fiftieth10 = infinPow10 !! 50
many2s = take 10 (repeat 2)
many3s = replicate 10 3
cyclelist = take 10 (cycle [1,2,3,4,5])

listTimes2 = [x * 2 | x <- [1..10]]
listTimes3 = [x * 5 | x <- [1..10], x * 5 <= 50]

commonfactors = [x | x <- [1..500], x `mod` 13 == 0, x `mod` 9 == 0]
multTable = [[x * y | y <- [1..10]] | x <- [1..10]]

-- More list functions:
-- init
-- take
-- drop
-- elem
-- maximum
-- minimum
-- product
-- sort
-- zipWith
-- filter
-- takeWhile
-- foldl foldr

-- List comprehension
pow3List = [3^n | n <- [1..10]]

-- Tuples
bobSmith = ("Bob Smith", 52)
-- fst bobSmith
-- snd bobSmith

animalSounds = zip ["cow", "cat", "dog"] ["moo", "meow", "woof"]


-- functions
addMe :: Int -> Int -> Int
-- funcName param1 param2 = operations (returned value)
addMe x y = x + y
