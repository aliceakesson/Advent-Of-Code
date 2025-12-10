def part1():
    f = open("puzzle.txt", "r")
    lines = []
    for line in f: 
        lines.append(line)

    digit_rows = lines[:-1]
    op_row = [x for x in lines[-1].split(' ') if x]

    digits = []
    for i, line in enumerate(digit_rows): 
        xs = [int(x) for x in map(lambda y: y.replace('\n', ''), line.split(' ')) if x]
        for j, digit in enumerate(xs): 
            if i == 0: 
                digits.append(digit)
            else:
                op = op_row[j]
                if op == '*':
                    digits[j] *= digit
                else:
                    digits[j] += digit
    print(sum(digits))

def part2():
    f = open("puzzle.txt", "r")
    lines = []
    for line in f: 
        lines.append(line)

    op = lines[-1][0]
    sum = 0
    digits = []
    for col in range(len(lines[0][:-1])):
        possible_op = lines[-1][col]
        if col > 0 and not possible_op == ' ' or col == len(lines[0])-2: # switch of operation, sum what we have so far
            if col == len(lines[0])-2:
                s = ''
                for row in range(len(lines)-1):
                    s += lines[row][col]
                if s.strip(): 
                    digits.append(int(s.strip()))
            if op == '+':
                summation = 0
                for d in digits: 
                    summation += d
                sum += summation
            else: 
                product = 1
                for d in digits: 
                    product *= d
                sum += product
            op = possible_op
            digits = []
        s = ''
        for row in range(len(lines)-1):
            s += lines[row][col]
        if s.strip(): 
            digits.append(int(s.strip()))
    print(sum)
    

part1()
part2()
