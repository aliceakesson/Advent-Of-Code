import scala.io.Source

object Day4:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var sum = 0
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day4/puzzle.txt").getLines.toVector
        src.foreach( r => 
            val pairs = r.split(",").toVector
            var elfOne = pairs(0).split("-").toVector.map(_.toInt)
            var elfTwo = pairs(1).split("-").toVector.map(_.toInt)

            var iContainsAll = true
            var jContainsAll = true

            for i <- elfOne(0) to elfOne(1) do 
                if !(elfTwo(0) to elfTwo(1)).contains(i) then jContainsAll = false

            for j <- elfTwo(0) to elfTwo(1) do 
                if !(elfOne(0) to elfOne(1)).contains(j) then iContainsAll = false

            if iContainsAll || jContainsAll then sum += 1
        )
        println(sum)

    def part2: Unit = 
        var sum = 0
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day4/puzzle.txt").getLines.toVector
        src.foreach( r => 
            val pairs = r.split(",").toVector
            var elfOne = pairs(0).split("-").toVector.map(_.toInt)
            var elfTwo = pairs(1).split("-").toVector.map(_.toInt)

            var overlap = false

            for i <- elfOne(0) to elfOne(1) do 
                for j <- elfTwo(0) to elfTwo(1) do
                    if j == i then overlap = true

            if overlap then sum += 1
        )
        println(sum)

