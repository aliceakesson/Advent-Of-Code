def part1():
    f = open("2024/day7/puzzle.txt", "r")

    result = 0

    def findSolution(p, t, s):
        if not t: 
            return p == eval(s)

        if not s: # if it's the first time, add the first term in the beginning
            s += str(t.pop(0))
        # elif eval(s) > p: # for optimization, but seems to be making it slower because of eval()
        #     return False
        
        return findSolution(p, t[1:], "(" + s + ")" + "+" + str(t[0])) or findSolution(p, t[1:], "(" + s + ")" + "*" + str(t[0])) 

    for line in f:
        product = int(line.split(':')[0])
        terms = list(map(lambda y: int(y.replace('\n', '')),filter(lambda x: len(x) > 0, line.split(':')[1].split(' '))))

        if findSolution(product, terms, ""): 
            result += product

    print(result)


def part2():
    f = open("2024/day7/puzzle.txt", "r")

    result = 0

    def findSolution(p, t, r):
        if not t: 
            return p == int(r)

        if not r: # if it's the first time, add the first term in the beginning
            r = str(t.pop(0))
        # elif eval(s) > p: # for optimization, but seems to be making it slower because of eval()
        #     return False
        
        return findSolution(p, t[1:], str(int(r) + t[0])) or findSolution(p, t[1:], str(int(r) * t[0])) or findSolution(p, t[1:], r + str(t[0])) 

    for line in f:
        product = int(line.split(':')[0])
        terms = list(map(lambda y: int(y.replace('\n', '')),filter(lambda x: len(x) > 0, line.split(':')[1].split(' '))))

        if findSolution(product, terms, ""): 
            result += product

    print(result)

# -------------------------------------------------------------------------------------------------

part1()
part2()
