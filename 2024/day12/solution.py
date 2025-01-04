def part1():
    f = open("2024/day12/example.txt", "r")
    lines = f.readlines() 
    
    price = 0

    def move(pos, direc):
        return tuple(p+d for p, d in zip(pos, direc))

    neighbors = {}
    plants = {}
    
    visited = {}

    def areaAndPerimeter(start, c, pos):
        x = pos[0]
        y = pos[1]

        if x < 0 or y < 0 or x >= len(lines[0])-1 or y >= len(lines):
            return (0,1)
        
        current = lines[y][x]
        if not current == c:
            if not start in neighbors.keys(): 
                neighbors[start] = [pos]
                return (0,1)
            if not pos in neighbors[start]:
                neighbors[start].append(pos)
                return (0,1)
            return (0,0)

        if pos in plants[start]:
            return (0,0)
        
        plants[start].append(pos)

        up = areaAndPerimeter(start, c, move(pos, (0, -1)))
        down = areaAndPerimeter(start, c, move(pos, (0, 1)))
        right = areaAndPerimeter(start, c, move(pos, (1, 0)))
        left = areaAndPerimeter(start, c, move(pos, (-1, 0)))

        return list(sum(x) for x in zip(*[up, down, right, left, (1,0)]))

    def calculateRegion(pos):
        c = lines[pos[1]][pos[0]]
        plants[pos] = [pos]

        up = areaAndPerimeter(pos, c, move(pos, (0, -1)))
        down = areaAndPerimeter(pos, c, move(pos, (0, 1)))
        right = areaAndPerimeter(pos, c, move(pos, (1, 0)))
        left = areaAndPerimeter(pos, c, move(pos, (-1, 0)))

        return list(sum(x) for x in zip(*[up, down, right, left, (1,0)]))

    # print(calculateRegion((4,0)))

    for i in range(len(lines)):
        visited[i] = []
    
    while visited: 
        m = min(visited) # minsta index som finns kvar
        for i in range(len(lines[0])-1):
            if not i in visited[m]
        break

    print(price) # 1930


def part2():
    f = open("2024/day12/puzzle.txt", "r")


# -------------------------------------------------------------------------------------------------

part1()
part2()
