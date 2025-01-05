def part1():
    f = open("2024/day12/puzzle.txt", "r")
    lines = f.readlines() 
    
    price = 0

    neighbors = {}
    plants = {}
    
    visited = {}

    def move(pos, direc):
        return tuple(p+d for p, d in zip(pos, direc))
    
    def addNeighbors(start, c, pos):
        count = 0
        directions = [(0,1), (0,-1), (1,0), (-1, 0)]
        for direc in directions:
            p = move(pos, direc)
            x = p[0]
            y = p[1]
            if x >= 0 and y >= 0 and x < len(lines[0])-1 and y < len(lines) and not lines[y][x] == c:
                count += 1
                neighbors[start].append((x, y))
        return count

    def areaAndPerimeter(start, c, pos):
        x = pos[0]
        y = pos[1]

        if x < 0 or y < 0 or x >= len(lines[0])-1 or y >= len(lines):
            return (0,1)

        current = lines[y][x]
        if not current == c:
            return (0,0)

        if pos in plants[start]:
            return (0,0)
        
        plants[start].append(pos)
        if y in visited.keys() and not x in visited[y]:
            visited[y].append(x)

        n = addNeighbors(start, c, pos)


        up = areaAndPerimeter(start, c, move(pos, (0, -1)))
        down = areaAndPerimeter(start, c, move(pos, (0, 1)))
        right = areaAndPerimeter(start, c, move(pos, (1, 0)))
        left = areaAndPerimeter(start, c, move(pos, (-1, 0)))

        return list(sum(x) for x in zip(*[up, down, right, left, (1,n)]))

    def calculateRegion(pos):
        c = lines[pos[1]][pos[0]]
        plants[pos] = [pos]
        visited[pos[1]].append(pos[0])
        neighbors[pos] = []

        up = areaAndPerimeter(pos, c, move(pos, (0, -1)))
        down = areaAndPerimeter(pos, c, move(pos, (0, 1)))
        right = areaAndPerimeter(pos, c, move(pos, (1, 0)))
        left = areaAndPerimeter(pos, c, move(pos, (-1, 0)))

        n = addNeighbors(pos, c, pos)

        return list(sum(x) for x in zip(*[up, down, right, left, (1, n)]))

    for i in range(len(lines)):
        visited[i] = []

    while visited: # för att gå igenom alla punkter
        y = min(visited) # minsta index som finns kvar
        x = -1
        for i in range(len(lines[0])-1):
            if not i in visited[y]: 
                x = i
                break 
        if x >= 0: 
            value = calculateRegion((x, y))
            price += value[0] * value[1]
        if len(visited[y]) >= len(lines[0])-1:
            del visited[y]

    print(price) # 772


def part2():
    f = open("2024/day12/puzzle.txt", "r")
    lines = f.readlines() 
    
    price = 0

    plants = {}
    visited = {}

    def move(pos, direc):
        return tuple(p+d for p, d in zip(pos, direc))
    
    def neighbors(c, pos):
        directions = [(0,-1), (1,0), (0, 1), (-1, 0)]
        arr = []

        for direc in directions:
            p = move(pos, direc)
            x = p[0]
            y = p[1]
            if x < 0 or y < 0 or x >= len(lines[0])-1 or y >= len(lines) or not lines[y][x] == c:
                arr.append(1)
            else:
                arr.append(0)

        sides = 0
        if arr == [1, 1, 0, 0] or arr == [0, 1, 1, 0] or arr == [0, 0, 1, 1] or arr == [1, 0, 0, 1]:
            sides = 1
        elif arr == [1, 1, 1, 0] or arr == [0, 1, 1, 1] or arr == [1, 0, 1, 1] or arr == [1, 1, 0, 1]:
            sides = 2
        elif arr == [1, 1, 1, 1]:
            sides = 4

        # count for "inside curves"
        for v in [(0, -1), (0, 1)]:
            for h in [(-1, 0), (1, 0)]:
                p0 = move(pos, v)
                p = move(p0, h)
                x = p[0]
                y = p[1]
                if x >= 0 and y >= 0 and x < len(lines[0])-1 and y < len(lines) and not lines[y][x] == c: 
                    p1 = move(pos, h)
                    if lines[p0[1]][p0[0]] == c and lines[p1[1]][p1[0]] == c: 
                        sides += 1
        return sides

    def areaAndPerimeter(start, c, pos):
        x = pos[0]
        y = pos[1]

        if x < 0 or y < 0 or x >= len(lines[0])-1 or y >= len(lines):
            return (0,0)

        current = lines[y][x]
        if not current == c:
            return (0,0)

        if pos in plants[start]:
            return (0,0)
        
        plants[start].append(pos)
        if y in visited.keys() and not x in visited[y]:
            visited[y].append(x)

        up = areaAndPerimeter(start, c, move(pos, (0, -1)))
        down = areaAndPerimeter(start, c, move(pos, (0, 1)))
        right = areaAndPerimeter(start, c, move(pos, (1, 0)))
        left = areaAndPerimeter(start, c, move(pos, (-1, 0)))

        return list(sum(x) for x in zip(*[up, down, right, left, (1, neighbors(c, pos))]))

    def calculateRegion(pos):
        c = lines[pos[1]][pos[0]]
        plants[pos] = [pos]
        visited[pos[1]].append(pos[0])

        up = areaAndPerimeter(pos, c, move(pos, (0, -1)))
        down = areaAndPerimeter(pos, c, move(pos, (0, 1)))
        right = areaAndPerimeter(pos, c, move(pos, (1, 0)))
        left = areaAndPerimeter(pos, c, move(pos, (-1, 0)))

        return list(sum(x) for x in zip(*[up, down, right, left, (1, neighbors(c, pos))]))

    for i in range(len(lines)):
        visited[i] = []

    while visited: # för att gå igenom alla punkter
        y = min(visited) # minsta index som finns kvar
        x = -1
        for i in range(len(lines[0])-1):
            if not i in visited[y]: 
                x = i
                break 
        if x >= 0: 
            value = calculateRegion((x, y))
            price += value[0] * value[1]
        if len(visited[y]) >= len(lines[0])-1:
            del visited[y]

    print(price) 


# -------------------------------------------------------------------------------------------------

part1()
part2()
