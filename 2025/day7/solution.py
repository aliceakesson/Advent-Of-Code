def part1():
    f = open("puzzle.txt", "r")
    lines = []
    for line in f: 
        lines.append(line)
    n_splits = 0
    col_positions = [lines[0].index('S')]
    for row in range(len(lines)-1):
        for pos_idx, pos in enumerate(col_positions): 
            next_place = lines[row+1][pos]
            if next_place == '^':
                n_splits += 1
                col_positions[pos_idx] = pos - 1 
                col_positions.append(pos + 1)
        col_positions = list(set(col_positions))

    print(n_splits)

def part2():
    f = open("example.txt", "r")
    lines = []
    for line in f: 
        lines.append(line)
    n_timelines = 1
    col_positions = [lines[0].index('S')]
    for row in range(len(lines)-1):
        for pos_idx, pos in enumerate(col_positions): 
            next_place = lines[row+1][pos]
            if next_place == '^':
                n_timelines += 1
                col_positions[pos_idx] = pos - 1 
                col_positions.append(pos + 1)
    print(n_timelines)

    
# part1()
part2()
