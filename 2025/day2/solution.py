def part1():
    f = open("puzzle.txt", "r")
    sum = 0
    for line in f: 
        for ids in line.split(","):
            first_id = int(ids.split("-")[0])
            last_id = int(ids.split("-")[1])
            for i in range(first_id, last_id+1):
                s = str(i)
                if len(s) % 2 == 0:
                    mid = int(len(s) / 2)
                    first = s[:mid]
                    last = s[mid:]
                    if first == last: 
                        sum += i
    print(sum)

def part2():
    f = open("puzzle.txt", "r")
    sum = 0
    for line in f: 
        for ids in line.split(","):
            first_id = int(ids.split("-")[0])
            last_id = int(ids.split("-")[1])
            for i in range(first_id, last_id+1):
                s = str(i)
                for j in range(2, len(s)+1):
                    if len(s) % j == 0:
                        n = len(s) // j
                        chunks = [s[k:k + n] for k in range(0, len(s), n)]
                        if all(map(lambda x: x == chunks[0], chunks)):
                            sum += i
                            break
    print(sum)
    

# part1()
part2()
