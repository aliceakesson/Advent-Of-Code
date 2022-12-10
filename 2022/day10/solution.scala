import scala.io.Source

object Day10:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day10/puzzle.txt").getLines.toVector
        var x = 1
        var cycle = 1
        var sum = 0

        val cycles = Vector(20, 60, 100, 140, 180, 220)

        src.foreach( row =>  
            if row.startsWith("addx") then 
                if cycles.contains(cycle) then sum += x * cycle
                else if cycles.contains(cycle+1) then sum += x * (cycle+1)
                cycle += 2
                try 
                    x += row.substring(row.lastIndexOf(" ")).trim().toInt
                catch
                    case nfe: NumberFormatException => println()
                if cycles.contains(cycle) then sum += x * cycle
            else 
                cycle += 1   
                if cycles.contains(cycle) then sum += x * cycle
        )
        
        println(sum)

    def part2: Unit =
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day10/puzzle.txt").getLines.toVector