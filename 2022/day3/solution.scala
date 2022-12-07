import scala.io.Source

object Day3:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var sum = 0
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day3/puzzle.txt").getLines.toVector
        src.foreach( r => 
            val r1 = r.substring(0, r.length()/2)
            val r2 = r.substring(r.length()/2)
            var c = 'a'

            r1.foreach( c1 => 
                r2.foreach( c2 =>
                    if c1 == c2  then c = c1    
                )    
            )
            if c.toInt >= 97 then sum += c.toInt - 96
            else sum += c.toInt - 38
        )
        println(sum)

    def part2: Unit = 
        var sum = 0
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day3/puzzle.txt").getLines.toVector.grouped(3).toVector
        src.foreach( r => 
            var c = 'a'
            r(0).foreach( c1 => 
                r(1).foreach( c2 =>
                    r(2).foreach( c3 =>
                        if c1 == c2 && c2 == c3 then c = c1    
                    ) 
                )    
            )
            if c.toInt >= 97 then sum += c.toInt - 96
            else sum += c.toInt - 38
        )
        println(sum)
