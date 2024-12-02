def part1():
    f = open("2024/day2/puzzle.txt", "r")

    sum = 0

    for line in f: 
        arr = list(map(lambda y:int(y.replace('\n', '')), filter(lambda x: len(x) > 0, line.split(' '))))
        
        differences = []
        for n in range(1, len(arr)):
            differences.append(arr[n]-arr[n-1])

        if all(list(map(lambda x: x >= 1 and x <= 3, differences))) or all(list(map(lambda x: x <= -1 and x >= -3, differences))):
            sum += 1
    
    print(sum)

def part2():
    f = open("2024/day2/puzzle.txt", "r")

    sum = 0

    for line in f: 
        arr = list(map(lambda y:int(y.replace('\n', '')), filter(lambda x: len(x) > 0, line.split(' '))))
        
        differences = []
        for n in range(1, len(arr)-1):
            differences.append(arr[n]-arr[n-1])

        def isSafe(diff):
            if all(list(map(lambda x: x >= 1 and x <= 3, diff))) or all(list(map(lambda x: x <= -1 and x >= -3, diff))):
                return True
            return False
        
        if isSafe(differences):
            sum += 1
        else:
            for n in range(0, len(arr)):
                listcopy = arr.copy()
                listcopy.remove(arr[n])

                differences = []
                for i in range(1, len(listcopy)):
                    differences.append(listcopy[i]-listcopy[i-1])

                if isSafe(differences):
                    sum += 1
                    break
    
    print(sum)
    
# -------------------------------------------------------------------------------------------------

part1()
part2()
