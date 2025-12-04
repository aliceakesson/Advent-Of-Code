def part1():
    f = open("2024/day11/puzzle.txt", "r")

    stones = list(map(lambda x: int(x), f.readlines()[0].split(' ')))

    for i in range (0, 25):
        addToIndex = 0
        for stone in enumerate(stones.copy()):
            index = stone[0] + addToIndex
            if stone[1] == 0:
                stones[index] = 1
            elif len(str(stone[1])) % 2 == 0: 
                s = str(stone[1])
                half = int(len(s)/2)
                left = int(s[:half])
                right = int(s[half:])
                stones[index] = right
                stones[index:index] = [left]
                addToIndex += 1
            else:
                stones[index] = stone[1] * 2024

    print(len(stones))

import threading

def part2():
    f = open("2024/day11/puzzle.txt", "r")

    stones = list(map(lambda x: int(x), f.readlines().copy()[0].split(' ')))

    for stone in enumerate(stones): 
        threading.Thread(target=f, args=(stone[1],), daemon=True).start()

    def f(value):
        vs = [value]
        for i in range (0, 25):
            addToIndex = 0
            for v in enumerate(vs.copy()):
                index = v[0] + addToIndex
                if v[1] == 0:
                    vs[index] = 1
                elif len(str(v[1])) % 2 == 0: 
                    s = str(v[1])
                    half = int(len(s)/2)
                    left = int(s[:half])
                    right = int(s[half:])
                    vs[index] = right
                    vs[index:index] = [left]
                    addToIndex += 1
                else:
                    vs[index] = stone[1] * 2024

    print(len(stones))


# -------------------------------------------------------------------------------------------------

part1()
part2()
