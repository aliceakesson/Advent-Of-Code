import scala.io.Source

object Day11:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day11/puzzle.txt").getLines.grouped(7).toVector
            .map( p => p.dropRight(1).map( s => s.trim() ).toVector )

        var inspections = Vector.fill(src.length)(0)

        for i <- 0 until 20 do 
            src.foreach( monkey => 
                val index = monkey(0).substring(monkey(0).lastIndexOf(" ")).trim()(0).toInt-48
                val items = monkey(1).substring(monkey(1).indexOf(" ", monkey(1).indexOf(":"))).trim().split(", ").toVector
                inspections = inspections.updated(index, inspections(index)+items.length)

                items.foreach( item =>

                    var worry = item.toInt

                    var otherNumber = worry
                    if monkey(2).substring(monkey(2).lastIndexOf(" ")).trim() != "old" then 
                        try otherNumber = monkey(2).substring(monkey(2).lastIndexOf(" ")).trim().toInt
                        catch case nfe: NumberFormatException => throw new NumberFormatException

                    if monkey(2).substring(monkey(2).indexOf(" ", monkey(2).indexOf("old"))).trim()(0) == '*' then
                        worry *= otherNumber
                    else 
                        worry += otherNumber

                    worry = (worry / 3).toInt

                    val divisibleNumber = monkey(3).substring(monkey(3).lastIndexOf(" ")).trim().toInt
                    var otherMonkey = 0
                    if worry % divisibleNumber == 0 then 
                        otherMonkey = monkey(4).substring(monkey(4).lastIndexOf(" ")).trim().toInt
                    else 
                        otherMonkey = monkey(5).substring(monkey(5).lastIndexOf(" ")).trim().toInt

                    val text = src(otherMonkey)(0).concat(s", $worry")
                    src = src.updated(otherMonkey, src(otherMonkey).updated(0, text))
                )
            )

        println(inspections)

    def part2: Unit =
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day11/puzzle.txt").getLines.toVector
