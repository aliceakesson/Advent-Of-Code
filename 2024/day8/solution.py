def part1():
    f = open("2024/day8/puzzle.txt", "r")
    lines = f.readlines()

    signals = []

    for line in lines:
        for c in line: 
            if not c == '.' and not c == '\n':
                signals.append([c, [line.index(c), lines.index(line)]]) 

    antinodes = []

    def addAntinode(c, x, y):
        if not [x, y] in antinodes and not [x, y] in list(map(lambda x: x[1], filter(lambda y: y[0] == c, signals))):
            if x >= 0 and y >= 0 and x < len(lines[0])-1 and y < len(lines):
                antinodes.append([x, y])

    for s1 in signals: 
        for s2 in signals[signals.index(s1):]:
            if s1[0] == s2[0]:
                dx = s1[1][0]-s2[1][0]
                dy = s1[1][1]-s2[1][1]
                addAntinode(s1[0], s1[1][0]+dx, s1[1][1]+dy)
                addAntinode(s1[0], s2[1][0]-dx, s2[1][1]-dy)

    print(len(antinodes))

def part2():
    f = open("2024/day8/puzzle.txt", "r")
    lines = f.readlines()

    signals = []

    for line in lines:
        for c in line: 
            if not c == '.' and not c == '\n':
                signals.append([c, [line.index(c), lines.index(line)]]) 

    antinodes = []

    def addAntinode(x, y):
        if not [x, y] in antinodes and not [x, y] in map(lambda a: a[1], signals):
            antinodes.append([x, y])

    for s1 in signals: 
        for s2 in signals[signals.index(s1)+1:]:
            if s1[0] == s2[0]:
                dx = s1[1][0]-s2[1][0]
                dy = s1[1][1]-s2[1][1]

                while s1[1][0]+dx >= 0 and s1[1][0]+dx < len(lines[0])-1 and s1[1][1]+dy >= 0 and s1[1][1]+dy < len(lines):
                    addAntinode(s1[1][0]+dx, s1[1][1]+dy)
                    dx += s1[1][0]-s2[1][0]
                    dy += s1[1][1]-s2[1][1]
                
                dx = s1[1][0]-s2[1][0]
                dy = s1[1][1]-s2[1][1]
                while s2[1][0]-dx >= 0 and s2[1][0]-dx < len(lines[0])-1 and s2[1][1]-dy >= 0 and s2[1][1]-dy < len(lines):
                    addAntinode(s2[1][0]-dx, s2[1][1]-dy)
                    dx += s1[1][0]-s2[1][0]
                    dy += s1[1][1]-s2[1][1]

    print(len(antinodes)+len(signals))

# -------------------------------------------------------------------------------------------------

part1()
part2()
