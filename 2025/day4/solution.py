def part1():
    f = open("puzzle.txt", "r")
    sum = 0
    lines = []
    for line in f: 
        lines.append(line)

    for row, line in enumerate(lines): 
        line = line.replace('\n', '')
        for col, roll in enumerate(line): 
            if roll == '@':
                adjacent = 0
                for i in range(-1, 2):
                    r = row + i
                    for j in range(-1, 2):
                        c = col + j
                        if not (r < 0 or c < 0 or r > len(lines)-1 or c > len(line)-1):
                            if not (i == 0 and j == 0) and lines[r][c] == '@': adjacent += 1
                if adjacent < 4: 
                    sum += 1
    print(sum)
def part2():
    f = open("puzzle.txt", "r")
    sum = 0
    lines = []
    for line in f: 
        lines.append(line)

    while True:
        found = False
        for row, line in enumerate(lines): 
            line = line.replace('\n', '')
            for col, roll in enumerate(line): 
                if roll == '@':
                    adjacent = 0
                    for i in range(-1, 2):
                        r = row + i
                        for j in range(-1, 2):
                            c = col + j
                            if not (r < 0 or c < 0 or r > len(lines)-1 or c > len(line)-1):
                                if not (i == 0 and j == 0) and lines[r][c] == '@': adjacent += 1
                    if adjacent < 4: 
                        found = True
                        sum += 1
                        lines[row] = lines[row][:col] + '.' + lines[row][col+1:]
        if found: continue
        else: break 
    print(sum)
    
part1()
part2()
