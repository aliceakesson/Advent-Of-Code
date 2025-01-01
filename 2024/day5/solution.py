def part1():
    f = open("2024/day5/puzzle.txt", "r")
    lines = f.readlines()

    rules = lines[:1176]
    updates = lines[1177:]

    # rules = lines[:21]
    # updates = lines[22:]

    count = 0

    for update in updates: 
        arr = update.replace('\n', '').split(',')
        valid = True 

        for elem in arr: 
            elementsAfter = list(map(lambda y: y[3:].replace('\n', ''), filter(lambda x: x.startswith(str(elem)), rules)))
            for after in elementsAfter: 
                if after in arr and arr.index(after) < arr.index(elem):
                    valid = False 
                    break

            elementsBefore = list(map(lambda y: y[:2], filter(lambda x: x.endswith(str(elem + "\n")), rules)))
            for before in elementsBefore: 
                if before in arr and arr.index(before) > arr.index(elem):
                    valid = False 
                    break

        if valid: 
            middleElem = int(arr[int(len(arr)/2)])
            count += middleElem

    print(count)


def part2():
    f = open("2024/day5/puzzle.txt", "r")
    lines = f.readlines()

    rules = lines[:1176]
    updates = lines[1177:]

    # rules = lines[:21]
    # updates = lines[22:]

    count = 0

    def moveYbeforeX(x, y, arr): # move y before x
        temp = arr[y]
        arr.remove(arr[y])
        arr.insert(x, temp)

    def moveXafterY(x, y, arr): # move x after y 
        temp = arr[x]
        arr.remove(arr[x])
        arr.insert(y+1, temp)

    for update in updates: 
        arr = update.replace('\n', '').split(',')
        fix = False
        k = 1

        while k > 0:
            k = 0
            for elem in arr: 
                elementsAfter = list(map(lambda y: y[3:].replace('\n', ''), filter(lambda x: x.startswith(str(elem)), rules)))
                for after in elementsAfter: 
                    if after in arr and arr.index(after) < arr.index(elem):
                        k += 1
                        moveYbeforeX(arr.index(elem), arr.index(after), arr)
                        fix = True 

                elementsBefore = list(map(lambda y: y[:2], filter(lambda x: x.endswith(str(elem + "\n")), rules)))
                for before in elementsBefore: 
                    if before in arr and arr.index(before) > arr.index(elem):
                        k += 1
                        moveXafterY(arr.index(elem), arr.index(before), arr)
                        fix = True 

        if fix: 
            middleElem = int(arr[int(len(arr)/2)])
            count += middleElem

    print(count)


# -------------------------------------------------------------------------------------------------

# part1()
part2()
