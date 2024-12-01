def part1():
    f = open("2024/day1/puzzle.txt", "r")

    left = []
    right = []

    for line in f:
        arr = list(map(lambda y: y.replace('\n', ''), filter(lambda x: len(x) > 0, line.split(' '))))
        left.append(int(arr[0]))
        right.append(int(arr[1]))

    sum = 0

    while(left):
        l = min(left)
        left.remove(l)

        r = min(right)
        right.remove(r)

        sum += abs(l-r)

    print(sum)


def part2():
    f = open("2024/day1/puzzle.txt", "r")
    
    left = []
    right = []

    for line in f:
        arr = list(map(lambda y: y.replace('\n', ''), filter(lambda x: len(x) > 0, line.split(' '))))
        left.append(int(arr[0]))
        right.append(int(arr[1]))

    sum = 0

    for n in left: 
        l = list(filter(lambda x: x == n, right))
        sum += len(l) * n

    print(sum)


part1()
part2()
