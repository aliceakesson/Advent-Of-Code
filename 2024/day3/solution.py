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

    for line in f: 
        regex = r"don't\(\).*do\(\)"
        newLine = re.sub(regex, "", line)

        regex = r"don't\(\).*"
        newLine = re.sub(regex, "", newLine)

        regex = "mul\(\d+,\d+\)"

        i = sum(list(map(lambda y: int(y[0])*int(y[1]), map(lambda x: re.findall("\d+", x), re.findall(regex, newLine)))))
        result += i

    print(result)

# -------------------------------------------------------------------------------------------------

part1()
part2()
