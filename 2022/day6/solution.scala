import scala.io.Source

object Day6:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day6/puzzle.txt").getLines.toVector(0)

        var startIndex = 0

        var found = false
        while !found do 
            val nextLine = src.substring(startIndex, startIndex+4)

            var allDifferent = true
            for i <- startIndex until startIndex+4 do
                for j <- startIndex until startIndex+4 do
                    if i != j && src(i) == src(j) then allDifferent = false
            if allDifferent then found = true else startIndex += 1

        println(startIndex+4)

    def part2: Unit = 
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day6/puzzle.txt").getLines.toVector(0)

        var startIndex = 0

        var found = false
        while !found do 
            val nextLine = src.substring(startIndex, startIndex+14)

            var allDifferent = true
            for i <- startIndex until startIndex+14 do
                for j <- startIndex until startIndex+14 do
                    if i != j && src(i) == src(j) then allDifferent = false
            if allDifferent then found = true else startIndex += 1

        println(startIndex+14)
       
