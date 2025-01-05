def part1():
    f = open("2024/day13/puzzle.txt", "r")
    lines = list(zip(*[iter(filter(lambda x: not x == '\n', f.readlines()))] * 3))

    price = 0

    # Button A: X+94, Y+34
    # Button B: X+22, Y+67
    # Prize: X=8400, Y=5400
    # 
    # 94*moveA + 22*moveB = 8400 => moveB = 1/22(8400 - 94*moveA)
    # 34*moveA + 67*moveB = 5400 = 34*moveA + 67/22*(8400 - 94*moveA) = 
    #       = 34*moveA + (67*8400)/22 - (67*94)/22*moveA =>
    #       => (34 - (67*94)/22)*moveA = 5400 - (67*8400)/22 =>
    #       => moveA = (5400 - (67*8400)/22) / (34 - (67*94)/22)
    # price = 3*moveA + 1*moveB = 3*(5400 - (67*8400)/22) / (34 - (67*94)/22) + 1/22(8400 - 94*moveA)
    #
    # moveA = (y - (by*x)/bx) / (ay - (by*ax)/bx)
    # moveB = (1/bx) * (x - ax*moveA)
    #
    # MINIMERA price !! 

    # 
    # 94*moveA + 22*moveB = 8400 => moveB = 1/22(8400 - 94*moveA)
    # 34*moveA + 67*moveB = 5400 => moveB = 1/67(5400 - 34*moveA)
    #       => 1/22(8400 - 94*moveA) = 1/67(5400 - 34*moveA) =>
    #       => 8400/22 - (94/22)*moveA = 5400/67 - (34/67)*moveA =>
    #       => (94/22 - 34/67)*moveA = 8400/22 - 5400/67 =>
    #       => moveA = (8400/22 - 5400/67) / (94/22 - 34/67)
    # price = 3*moveA + 1*moveB 

    for line in lines:
        ax = int(line[0].split(' ')[2][2:][:-1])
        ay = int(line[0].split(' ')[3][2:][:-1])
        bx = int(line[1].split(' ')[2][2:][:-1])
        by = int(line[1].split(' ')[3][2:][:-1])
        x = int(line[2].split(' ')[1][2:][:-1])
        y = int(line[2].split(' ')[2][2:].replace('\n', ''))

        # print(f"ax: {ax}, ay: {ay}, bx: {bx}, by: {by}, x: {x}, y: {y}")

        # moveA = (y - (by*x)/bx) / (ay - (by*ax)/bx)
        # moveB = (1/bx) * (x - ax*moveA)

        # if moveA <= 100 and moveB <= 100 and moveA >= 0 and moveB >= 0:
        #     if moveA % 1 == 0 and moveB % 1 == 0: 
        #         price += int(3*moveA + moveB)
        #     else:
        #         moveA = int(moveA)
        #     while moveA <= 100: 
        #         moveA += 1
        #         moveB = (1/bx) * (x - ax*moveA)
        #         if moveA % 1 == 0 and moveB % 1 == 0: 
        #             price += int(3*moveA + moveB)
        #             break
 
        # screw it, brute force
        possible = []
        for moveA in range(0, 100+1):
            for moveB in range(0, 100+1):
                if ax*moveA + bx*moveB == x and ay*moveA + by*moveB == y: 
                    possible.append(3*moveA + moveB)

        if len(possible) > 0: 
            price += min(possible)

    print(price) # rätt svar: 36758

def part2():
    f = open("2024/day13/puzzle.txt", "r")
    lines = list(zip(*[iter(filter(lambda x: not x == '\n', f.readlines()))] * 3))

    price = 0

    for line in lines:
        ax = int(line[0].split(' ')[2][2:][:-1])
        ay = int(line[0].split(' ')[3][2:][:-1])
        bx = int(line[1].split(' ')[2][2:][:-1])
        by = int(line[1].split(' ')[3][2:][:-1])
        x = int(line[2].split(' ')[1][2:][:-1])
        y = int(line[2].split(' ')[2][2:].replace('\n', ''))

        # print(f"ax: {ax}, ay: {ay}, bx: {bx}, by: {by}, x: {x}, y: {y}")

        moveA = (y - (by*x)/bx) / (ay - (by*ax)/bx)
        moveB = (1/bx) * (x - ax*moveA)

        if moveA <= 100 and moveB <= 100 and moveA >= 0 and moveB >= 0:
            if moveA % 1 == 0 and moveB % 1 == 0: 
                price += int(3*moveA + moveB)
            else:
                moveA = int(moveA)
                while moveA <= 100: 
                    moveA += 1
                    moveB = (1/bx) * (x - ax*moveA)
                    if moveA % 1 == 0 and moveB % 1 == 0: 
                        price += int(3*moveA + moveB)
                        break

    print(price)

# -------------------------------------------------------------------------------------------------

# part1()
part2()
