def myList = [3, 1, 4, 1, 5, 9]
println myList
println myList.class.getName()

def myLinkedList = [3, 1, 4, 1, 5, 9] as LinkedList
println myLinkedList
println myLinkedList.class.getName()
println myLinkedList == myList

def mySet = [3, 1, 4, 1, 5, 9] as Set 
println mySet
println mySet.class.getName()

def mySortedSet = [3, 1, 4, 1, 5, 9] as SortedSet
println mySortedSet
println mySortedSet.class.getName()

for (int i=0; i < myList.size(); i++) print "${myList[i]} "; 
