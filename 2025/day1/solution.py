def part1():
    f = open("puzzle.txt", "r")
    res = 50
    cnt = 0
    for line in f:
        dir = line[0]
        steps = int(line[1:])
        sign = 1 if dir == 'R' else -1
        step = sign * steps
        res = (res + step) % 100
        if res == 0: 
            cnt += 1
    print(cnt)


def part2():
    f = open("puzzle.txt", "r")
    res = 50
    cnt = 0
    for line in f:
        prev = res
        dir = line[0]
        steps = int(line[1:])
        sign = 1 if dir == 'R' else -1
        step = sign * steps
        if step: 
            if steps >= 100: 
                varvningar = steps // 100
                cnt += varvningar
            res = (res + step) % 100
            has_increased = False
            if not prev == 0:
                if sign == 1 and res < prev: 
                    cnt += 1
                    has_increased = True 
                elif sign == -1 and res > prev:
                    cnt += 1
                    has_increased = True
            if res == 0 and not has_increased:
                cnt += 1
    print(cnt)
    

part1()
part2()
