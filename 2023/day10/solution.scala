import scala.io.Source
import scala.compiletime.ops.string
val pathToFolder = "C:/Users/alcea/coding/aoc/"

object Day10:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile(pathToFolder + "/2023/day10/example.txt").getLines.toVector

        var positions = Set.empty[(Int, Int)]
        var startingPosition = (0, 0)
        val connectMap = Map("n" -> Vector('|', '7', 'F', 'S'),
                             "e" -> Vector('-', 'J', '7', 'S'),
                             "s" -> Vector('|', 'L','J', 'S'),
                             "w" -> Vector('-', 'L', 'F', 'S'))
        val canConnect = Map('|' -> Vector("n", "s"),
                             '-' -> Vector("e", "w"),
                             'L' -> Vector("n", "e"),
                             'J' -> Vector("n", "w"),
                             '7' -> Vector("s", "w"),
                             'F' -> Vector("s" ,"e"),
                             'S' -> Vector("n", "e", "s", "w"))
        var steps = Map.empty[(Int, Int), Int] //pos -> steps

        for i <- src.indices do 
            for j <- src(0).indices do 
                if src(i)(j).equals('S') then 
                    startingPosition = (i, j)

        println(s"start: $startingPosition")

        def connectedPipes(i: Int, j: Int): Vector[(Int, Int)] = 
            var result = Vector.empty[(Int, Int)]
            val c = src(i)(j)
            if i > 0 && connectMap("n").contains(src(i-1)(j)) && canConnect(c).contains("n")
                then result = result :+ (i-1, j) 
            if j < src(0).length()-1 && connectMap("e").contains(src(i)(j+1)) && canConnect(c).contains("e")
                then result = result :+ (i, j+1)
            if i < src.length-1 && connectMap("s").contains(src(i+1)(j)) && canConnect(c).contains("s")
                then result = result :+ (i+1, j)
            if j > 0 && connectMap("w").contains(src(i)(j-1)) && canConnect(c).contains("w")
                then result = result :+ (i, j-1)
            result

        var next = connectedPipes(startingPosition._1, startingPosition._2)
        var current = next(0)
        while current != startingPosition do 
            positions += current
            next = connectedPipes(current._1, current._2)
            if !positions.contains(next(0)) then current = next(0)
            else if next.length == 1 then current = startingPosition
            else current = next(1)

        var max = 0
        positions.foreach(p => 
            // println(s"p: $p")

            var xs = Vector.empty[Int]
            var steps = 0
            next = connectedPipes(p._1, p._2)
            current = next(0)
            var visited = Set.empty[(Int, Int)]
            while current != startingPosition do 
                steps += 1
                visited += current
                next = connectedPipes(current._1, current._2)
                if !visited.contains(next(0)) then current = next(0)
                else if next.length == 1 then current = startingPosition
                else current = next(1)
            xs = xs :+ steps

            next = connectedPipes(p._1, p._2)
            // println(s"next: $next")
            if next.length > 1 then
                steps = 0
                current = next(1)
                visited = Set.empty[(Int, Int)]
                visited += p
                while current != startingPosition do 
                    steps += 1
                    visited += current
                    next = connectedPipes(current._1, current._2)
                    if !visited.contains(next(0)) then current = next(0)
                    else if next.length == 1 then current = startingPosition
                    else current = next(1)
                xs = xs :+ steps

            // println(s"xs: $xs")

            if xs.min > max then max = xs.min

        )

        println(max)


    def part2: Unit =
        var src = Source.fromFile(pathToFolder + "/2023/day10/puzzle.txt").getLines.toVector

