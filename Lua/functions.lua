-- factorial
function fact(n)
    if n == 0 then
        return 1
    else
        return n * fact(n-1)
    end
end

-- pythagoras
function norm(x, y)
    local n2 = x^2 + y^2
    return math.sqrt(n2)
end

-- print keys in a table
function printTable(T)
    if type(T) ~= 'table' then return end
    for k,v in pairs(T) do
    -- for k,v in ipairs(T) do
    -- for k,v in next, T do
        a = rightPadTwo(k, v, 25)
        print(rightPadTwo(k, v, 25))
        -- print(k .. ' : ' .. type(v))
    end


end

-- pad the concatenation of two strings
function rightPadTwo(a, b, max)
    if type(a)    ~= 'string' or 
        type(b)   ~= 'string' or 
        type(max) ~= 'number' then
        return
    end
    local toPad = max - string.len(a) - string.len(b)
    if toPad < 0 then
        error("Strings: " .. a .. ", " .. b .. " longer than " .. max)
    end
    local padding = string.rep(' ',toPad)
    return  a .. padding .. b
end

function leftPadTwo(values)

end