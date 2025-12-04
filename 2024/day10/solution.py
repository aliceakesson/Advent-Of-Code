def part1():
    f = open("2024/day10/puzzle.txt", "r")
    lines = f.readlines() 
    count = 0

    def move(pos, direc):
        return list(p+d for p, d in zip(pos, direc))

    mappings = {}

    def getTrailheads(start, pos, prev):
        x = pos[0]
        y = pos[1]
        if x < 0 or y < 0 or x >= len(lines[0])-1 or y >= len(lines):
            return 0

        current = int(lines[y][x])
        if current == 9 and prev == 8:
            if not start in mappings.keys(): 
                mappings[start] = [(x, y)]
                return 1
            elif not (x, y) in mappings[start]:
                mappings[start].append((x, y))
                return 1 
            return 0
        if not current == prev + 1:
            return 0

        up = getTrailheads(start, move(pos, (0,-1)), current)
        down = getTrailheads(start, move(pos, (0, 1)), current)
        right = getTrailheads(start, move(pos, (1, 0)), current)
        left = getTrailheads(start, move(pos, (-1, 0)), current)

        return up + down + right + left

    def calculate(x, y):
        up = getTrailheads((x, y), move((x, y), (0,-1)), 0)
        down = getTrailheads((x, y), move((x, y), (0, 1)), 0)
        right = getTrailheads((x, y), move((x, y), (1, 0)), 0)
        left = getTrailheads((x, y), move((x, y), (-1, 0)), 0)

        return up + down + right + left

    for line in enumerate(lines): 
        for c in enumerate(line[1]):
            if c[1] == '0':
                count += calculate(c[0], line[0])

    print(count)


def part2():
    f = open("2024/day10/puzzle.txt", "r")
    lines = f.readlines() 
    count = 0

    def move(pos, direc):
        return list(p+d for p, d in zip(pos, direc))

    mappings = {}

    def getTrailheads(start, pos, prev):
        x = pos[0]
        y = pos[1]
        if x < 0 or y < 0 or x >= len(lines[0])-1 or y >= len(lines):
            return 0

        current = int(lines[y][x])
        if current == 9 and prev == 8:
            return 1
        if not current == prev + 1:
            return 0

        up = getTrailheads(start, move(pos, (0,-1)), current)
        down = getTrailheads(start, move(pos, (0, 1)), current)
        right = getTrailheads(start, move(pos, (1, 0)), current)
        left = getTrailheads(start, move(pos, (-1, 0)), current)

        return up + down + right + left

    def calculate(x, y):
        up = getTrailheads((x, y), move((x, y), (0,-1)), 0)
        down = getTrailheads((x, y), move((x, y), (0, 1)), 0)
        right = getTrailheads((x, y), move((x, y), (1, 0)), 0)
        left = getTrailheads((x, y), move((x, y), (-1, 0)), 0)

        return up + down + right + left

    for line in enumerate(lines): 
        for c in enumerate(line[1]):
            if c[1] == '0':
                count += calculate(c[0], line[0])

    print(count)

# -------------------------------------------------------------------------------------------------

# part1()
part2()
