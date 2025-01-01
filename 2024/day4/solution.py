import re

def part1():
    f = open("2024/day4/puzzle.txt", "r")
    lines = f.readlines()

    result = 0

    # horizontal 
    for line in lines: 
        result += len(re.findall("XMAS", line))
        result += len(re.findall("SAMX", line)) 

    # vertical
    columns = []
    for i in range(0, len(lines[0])-1):
        columns.append([row[i] for row in lines])

    for col in columns: 
        s = ''.join(col)
        result += len(re.findall("XMAS", s))  
        result += len(re.findall("SAMX", s))

    # diagonal
    diagonals = []

    for i in range(0, len(lines[0])-1):
        k = i
        arr = []
        for line in lines:
            arr.append(line[k]) 
            k += 1
            if k >= len(lines[0])-1:
                break
        diagonals.append(arr)

    for i in range(1, len(lines)-1):
        k = 0
        arr = []
        for line in lines[i:]:
            arr.append(line[k]) 
            k += 1
        diagonals.append(arr)

    for i in reversed(range(0, len(lines[0])-1)):
        k = i
        arr = []
        for line in lines:
            arr.append(line[k]) 
            k -= 1
            if k < 0:
                break
        diagonals.append(arr)

    for i in range(0, len(lines)-1):
        k = len(lines[0])-1
        arr = []
        for line in lines[i:]:
            arr.append(line[k]) 
            k -= 1
        diagonals.append(arr)

    for dia in diagonals: 
        s = ''.join(dia)
        result += len(re.findall("XMAS", s))  
        result += len(re.findall("SAMX", s))

    print(result)

def part2():
    f = open("2024/day4/puzzle.txt", "r")

    lines = f.readlines()
    result = 0
    
    def isXMAS(x, y):
        if lines[y][x] == 'A':
            d1Correct = False # checking the different diagonals
            d2Correct = False

            if (lines[y-1][x-1] == "M" and lines[y+1][x+1] == "S") or (lines[y-1][x-1] == "S" and lines[y+1][x+1] == "M"):
                d1Correct = True 

            if (lines[y-1][x+1] == "M" and lines[y+1][x-1] == "S") or (lines[y-1][x+1] == "S" and lines[y+1][x-1] == "M"):
                d2Correct = True 

            if d1Correct and d2Correct:
                return True
        return False

    for x in range(1, len(lines[0])-1): 
        for y in range(1, len(lines)-1):
            if isXMAS(x, y):
                result += 1

    print(result)


# -------------------------------------------------------------------------------------------------

part1()
part2()
