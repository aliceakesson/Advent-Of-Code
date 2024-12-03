import re

def part1():
    f = open("2024/day3/puzzle.txt", "r")

    result = 0

    for line in f: 
        regex = "mul\(\d+,\d+\)"

        i = sum(list(map(lambda y: int(y[0])*int(y[1]), map(lambda x: re.findall("\d+", x), re.findall(regex, line)))))
        result += i

    print(result)

def part2():
    f = open("2024/day3/puzzle.txt", "r")

    result = 0

    def getSumOfLine(string):
        regex = "mul\(\d+,\d+\)"
        return sum(list(map(lambda y: int(y[0])*int(y[1]), map(lambda x: re.findall("\d+", x), re.findall(regex, string)))))

    for line in f: 
        regex = "don't\(\)"

        i = str(list(map(lambda x: re.split("do\(\)", x)[-1], re.split(regex, line))))
        result += getSumOfLine(i)

    print(result)

# -------------------------------------------------------------------------------------------------

part1()
part2()
