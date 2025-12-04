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

    pivots = []

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
    circles = 0

    def addPos(x, y):
        if not [x, y] in visited:
            visited.append([x, y])

    def isObstacle(x, y):
        return lines[y][x] == '#'

    def addPivot(x, y):
        if not [x, y, direc] in pivots: 
            pivots.append([x, y, direc])

    def addObstacle(x, y):
        if not [x, y] in obstacles:
            obstacles.append([x, y])

    while True: 
        if (direc == Dir.UP and ypos == 0) or (direc == Dir.DOWN and ypos == len(lines)-1):
            break
        if (direc == Dir.LEFT and xpos == 0) or (direc == Dir.RIGHT and xpos == len(lines[0])-1):
            break

        if len(pivots) >= 3:
            # om man når gammal pivot genom att svänga 90 grader här 
            if direc == Dir.UP: # svänga höger
                if any(filter(lambda x: x[1] == ypos and x[0] > xpos and x[2] == Dir.RIGHT, pivots)):
                    circles += 1
            elif direc == Dir.DOWN: # svänga vänster
                if any(filter(lambda x: x[1] == ypos and x[0] < xpos and x[2] == Dir.LEFT, pivots)):
                    circles += 1  
            elif direc == Dir.RIGHT: # svänga ner
                if any(filter(lambda x: x[0] == xpos and x[1] > ypos and x[2] == Dir.DOWN, pivots)):
                    circles += 1  
            elif direc == Dir.LEFT: # svänga upp
                if any(filter(lambda x: x[0] == xpos and x[1] < ypos and x[2] == Dir.UP, pivots)):
                    circles += 1  

            # gammal rektangel metod lösning
            # if pivots[-3][1] == ypos and pivots[-1][0] == xpos and pivots[-2][0] == pivots[-3][0] and pivots[-2][1] == pivots[-1][1]:
            #     circles += 1
            #     print(str([xpos, ypos]))
            # elif pivots[-1][1] == ypos and pivots[-3][0] == xpos and pivots[-2][0] == pivots[-1][0] and pivots[-2][1] == pivots[-3][1]:
            #     circles += 1
            #     print(str([xpos, ypos]))

        if direc == Dir.UP:
            if isObstacle(xpos, ypos-1):
                addPivot(xpos, ypos)
                direc = Dir.RIGHT
            else:
                ypos -= 1
                addPos(xpos, ypos)
        elif direc == Dir.DOWN:
            if isObstacle(xpos, ypos+1):
                addPivot(xpos, ypos)
                direc = Dir.LEFT
            else:
                ypos += 1
                addPos(xpos, ypos)
        elif direc == Dir.RIGHT:
            if isObstacle(xpos+1, ypos):
                addPivot(xpos, ypos)
                direc = Dir.DOWN
            else:
                xpos += 1
                addPos(xpos, ypos)
        elif direc == Dir.LEFT:
            if isObstacle(xpos-1, ypos):
                addPivot(xpos, ypos)
                direc = Dir.UP
            else:
                xpos -= 1
                addPos(xpos, ypos)

    print(circles)

# -------------------------------------------------------------------------------------------------

part1()
part2()
