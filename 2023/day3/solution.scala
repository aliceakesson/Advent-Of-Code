import scala.io.Source

object Day2:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day3/puzzle.txt").getLines.toVector

        var sum = 0
        val notSymbol = Vector('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.')
        var partNumbers: Vector[Int] = Vector.empty[Int]

        for i <- 0 until src.length do 
            for j <- 0 until src(i).length do
                val charHasSymbol = Vector[(Int, Int)]((i - 1, j - 1), (i - 1, j), (i - 1, j + 1), (i, j - 1), (i, j + 1), (i + 1, j - 1), (i + 1, j), (i + 1, j + 1))
                        .filter((a, b) => a >= 0 && a < src.length && b >= 0 && b < src(i).length)
                        .map((a, b) => src(a)(b))
                        .filter(c => !notSymbol.contains(c))
                        .length > 0

                if charHasSymbol && src(i)(j).isDigit then 
                    var firstIndex = j
                    while firstIndex > 0 && src(i)(firstIndex - 1).isDigit do
                        firstIndex-=1

                    var lastIndex = firstIndex
                    while lastIndex < src(i).length - 1 && src(i)(lastIndex + 1).isDigit do
                        lastIndex+=1
                        
                    val number = src(i).substring(firstIndex, lastIndex + 1).toInt

                    if partNumbers.length == 0 || partNumbers.last != number then
                        sum += number
                        partNumbers = partNumbers :+ number


        println(sum)

    def part2: Unit =
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day3/puzzle.txt").getLines.toVector

        var sum = 0

        for i <- 0 until src.length do 
            for j <- 0 until src(i).length do
                if src(i)(j) == '*' then 
                    var partNumbers: Vector[Int] = Vector.empty[Int]
                    val adjacentNumbersPositions = Vector[(Int, Int)]((i - 1, j - 1), (i - 1, j), (i - 1, j + 1), (i, j - 1), (i, j + 1), (i + 1, j - 1), (i + 1, j), (i + 1, j + 1))
                            .filter((a, b) => a >= 0 && a < src.length && b >= 0 && b < src(i).length)
                            .filter((a, b) => src(a)(b).isDigit)

                    adjacentNumbersPositions.foreach((a, b) => 
                        var firstIndex = b
                        while firstIndex > 0 && src(a)(firstIndex - 1).isDigit do
                            firstIndex-=1

                        var lastIndex = firstIndex
                        while lastIndex < src(a).length - 1 && src(a)(lastIndex + 1).isDigit do
                            lastIndex+=1
                            
                        val number = src(a).substring(firstIndex, lastIndex + 1).toInt

                        if !partNumbers.contains(number) then
                            partNumbers = partNumbers :+ number
                    )

                    if partNumbers.length == 2 then 
                        val pow = partNumbers(0) * partNumbers(1)
                        sum += pow

        println(sum)

       

        
