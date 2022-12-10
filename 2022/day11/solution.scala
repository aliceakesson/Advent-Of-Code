import scala.io.Source

object Day11:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        val src = Source.fromFile("C:/Users/alice/Advent-Of-Code/2022/day11/puzzle.txt").getLines.toVector

    def part2: Unit =
        val src = Source.fromFile("C:/Users/alice/Advent-Of-Code/2022/day11/puzzle.txt").getLines.toVector