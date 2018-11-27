print("Running " .. _VERSION)

-- print("Enter a number:")
-- a = io.read("*number")
-- print(fact(a))

dofile('functions.lua')

local t1 = {
    -- array part
    'a', 'b', 'c', 'd',

    -- hash table part

    ['key1'] = 'value1',
    ['key2'] = 'value2',
    ['key3'] = 'value3',

    -- functions
}

printTable(t1)