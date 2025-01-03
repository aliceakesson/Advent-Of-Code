def part1():
    f = open("2024/day9/puzzle.txt", "r")
    line = f.readlines()[0]

    xs = []
    file = True
    i = 0
    for c in line: 
        if file:
            xs += [i] * int(c)
            i += 1
        else:
            xs += [-1] * int(c)
        file = not file 

    while -1 in xs: 
        start = xs.index(-1)
        end = start
        while xs[end] == -1:
            end += 1
        group = xs[start:end] # first occurence of a groupn of -1:s
        del xs[start:end]
        toAdd = []
        count = 0
        while len(toAdd) < len(group):
            if not xs[-1-count] == -1:
                toAdd.append(xs[-1-count])
            count+= 1
        xs[start:start] = toAdd
        del xs[-count:]

    checksum = 0
    for x in enumerate(xs):
        checksum += x[0] * x[1]

    print(checksum)
    
from itertools import groupby

def part2():
    f = open("2024/day9/puzzle.txt", "r")
    line = f.readlines()[0]

    xs = []
    file = True
    i = 0
    for c in line: 
        if file:
            xs += [i] * int(c)
            i += 1
        else:
            xs += [-1] * int(c)
        file = not file 

    ys = list(list(y) for x, y in groupby(xs))

    for group in reversed(ys.copy()): # to be able to change ys at the same time as iterating
        groupIndex = ys.index(group)
        if not group[0] == -1:
            spaceIndex = -1
            for space in enumerate(ys): 
                if space[0] > groupIndex:
                    break
                if space[1][0] == -1 and len(space[1]) >= len(group):
                    spaceIndex = space[0]
                    break
            if spaceIndex > -1:
                chosenSpace = ys[spaceIndex]
                ys[ys.index(group)] = [-1] * len(group)
                ys[spaceIndex:spaceIndex] = [group]
                if len(chosenSpace) == len(group):
                    del ys[spaceIndex+1]
                else:
                    del chosenSpace[-len(group):]
    
    checksum = 0
    for x in enumerate(sum(ys, [])):
        if not x[1] == -1:
            checksum += x[0] * x[1]

    print(checksum)

# -------------------------------------------------------------------------------------------------

# part1()
part2()
