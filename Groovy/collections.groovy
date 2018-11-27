def nums = [3, 1, 4, 1, 5, 9] as Set

// println nums.class.name 

// for (int i=0;i < nums.size(); i++) { println nums[i]} // java loop
// for (x in nums) println x // java loop

nums.each { print "$it " } println()
// nums.each { n -> print "$n " } println()
// nums.eachWithIndex { n, idx -> print "$idx:$n; " }
// println()

def doubles = nums.collect { it * 2 }

// nums.each { doubles << it * 2 } // not good

// doubles.each { print "$it "} println()

// def cities = ['Boston', 'New York', 'Hyderabad', 'Cairo'] 

// println cities.collect { it.toLowerCase().reverse() }

// println nums.collect { it * 2 }.findAll { it % 3 }

