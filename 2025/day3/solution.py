def part1():
    f = open("puzzle.txt", "r")
    sum = 0
    for bank in f: 
        largest_index = -1
        largest_value1 = -1
        for i, battery in enumerate(bank[:-2]):
            if int(battery) > largest_value1:
                largest_value1 = int(battery)
                largest_index = i
        largest_value2 = -1
        for battery in bank[largest_index+1:-1]:
            if int(battery) > largest_value2:
                largest_value2 = int(battery)
        s = str(largest_value1) + str(largest_value2)
        sum += int(s)
    print(sum)

def part2():
    f = open("puzzle.txt", "r")
    sum = 0
    for bank in f: 
        digit = ''
        start_index = 0
        for i in reversed(range(0, 12)): # 11 till 0
            bank = bank.replace('\n', '')
            possible_contestants = bank[start_index:-i] if i > 0 else bank[start_index:]
            largest_value = -1
            largest_index = -1
            for i, battery in enumerate(possible_contestants):
                if int(battery) > largest_value:
                    largest_value = int(battery)
                    largest_index = i
            start_index += largest_index + 1
            digit += str(largest_value)
        sum += int(digit)
    print(sum)
    
part1()
part2()
