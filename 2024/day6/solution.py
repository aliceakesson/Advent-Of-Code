from enum import Enum

class Dir(Enum):
    UP = 1
    DOWN = 2
    RIGHT = 3
    LEFT = 4

def part1():
    f = open("2024/day6/puzzle.txt", "r")
    lines = f.readlines()
    lines = list(map(lambda x: x.replace('\n', ''), lines))

    up = '^'
    down = 'v'
    right = '>'
    left = '<'

    xpos = 0
    ypos = 0

    direc = Dir.UP

    for line in lines:
        for c in line: 
            if c == up or c == down or c == right or c == left:
                xpos = line.index(c)
                ypos = lines.index(line)
                if c == down:
                    direc = Dir.DOWN
                elif c == right:
                    direc = Dir.RIGHT
                elif c == left:
                    direc = Dir.LEFT
                break 

    visited = [[xpos, ypos]]

    def addPos(x, y):
        if not [x, y] in visited:
            visited.append([x, y])

    def isObstacle(x, y):
        return lines[y][x] == '#'

    while True: 
        if (direc == Dir.UP and ypos == 0) or (direc == Dir.DOWN and ypos == len(lines)-1):
            break
        if (direc == Dir.LEFT and xpos == 0) or (direc == Dir.RIGHT and xpos == len(lines[0])-1):
            break

        if direc == Dir.UP:
            if isObstacle(xpos, ypos-1):
                direc = Dir.RIGHT
            else:
                ypos -= 1
                addPos(xpos, ypos)
        elif direc == Dir.DOWN:
            if isObstacle(xpos, ypos+1):
                direc = Dir.LEFT
            else:
                ypos += 1
                addPos(xpos, ypos)
        elif direc == Dir.RIGHT:
            if isObstacle(xpos+1, ypos):
                direc = Dir.DOWN
            else:
                xpos += 1
                addPos(xpos, ypos)
        elif direc == Dir.LEFT:
            if isObstacle(xpos-1, ypos):
                direc = Dir.UP
            else:
                xpos -= 1
                addPos(xpos, ypos)


    print(len(visited))

def part2():
    f = open("2024/day6/example.txt", "r")

    count = 0

    print(count)

# -------------------------------------------------------------------------------------------------

part1()
part2()
