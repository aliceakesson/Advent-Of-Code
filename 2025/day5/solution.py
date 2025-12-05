def part1():
    f = open("puzzle.txt", "r")
    first_part = True 
    fresh_list = []
    ids = []
    for line in f: 
        if first_part:
            if line == '\n':
                first_part = False
            else: 
                split = line.replace('\n', '').split('-')
                fresh_list.append([int(split[0]), int(split[1])])
        else:
            ids.append(int(line.replace('\n', '')))

    sum = 0
    for id in ids: 
        for start, end in fresh_list:
            if start <= id and end >= id:
                sum += 1
                break 
    print(sum)

def part2():
    f = open("puzzle.txt", "r")
    fresh_list = []
    for line in f:
        if line == '\n':
            break
        else:
            split = line.replace('\n', '').split('-')
            fresh_list.append([int(split[0]), int(split[1])])

    fresh_list = sorted(fresh_list)
    sum = 0
    largest_seen = 0
    largest_seen_index = -1
    for i, [start, end] in enumerate(fresh_list):
        sum += end - start + 1
        if i > 0: 
            first = fresh_list[largest_seen_index][0]
            last = fresh_list[largest_seen_index][1]
            if start <= last and end > last:
                diff = last - start
                sum -= diff + 1
            elif start <= last:
                sum -= end - start + 1
        if end > largest_seen: 
            largest_seen = end 
            largest_seen_index = i

    print(sum)

    
part1()
part2()
