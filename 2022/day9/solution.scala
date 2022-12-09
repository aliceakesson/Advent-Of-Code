import scala.io.Source

object Day9:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day9/puzzle.txt").getLines.toVector
        var visited = Array.ofDim[Int](10000, 10000)
        val startPos = (visited.length/2, visited(0).length/2)

        var headPos = startPos
        var tailPos = startPos
        
        for i <- 0 until visited.length do 
            for j <- 0 until visited(0).length do
                visited(i)(j) = 0

        visited(startPos._1)(startPos._2) = 1

        def logChanges(pos: (Int, Int)): Unit = 
            visited(pos._1)(pos._2) = 1
        
        src.foreach( row => 
            val direction =  row(0)
            val steps = row.substring(row.lastIndexOf(" ")).trim().toInt

            for i <- 0 until steps do
                direction match
                    case 'U' => 
                        headPos = (headPos._1, headPos._2 - 1)
                        if tailPos._2 - headPos._2 == 2 then
                            tailPos = (tailPos._1, tailPos._2 - 1)
                            if headPos._1 > tailPos._1 then tailPos = (tailPos._1 + 1, tailPos._2)
                            else if headPos._1 < tailPos._1 then tailPos = (tailPos._1 - 1, tailPos._2)
                        logChanges(tailPos)
                    case 'D' => 
                        headPos = (headPos._1, headPos._2 + 1)
                        if headPos._2 - tailPos._2 == 2 then
                            tailPos = (tailPos._1, tailPos._2 + 1)
                            if headPos._1 > tailPos._1 then tailPos = (tailPos._1 + 1, tailPos._2)
                            else if headPos._1 < tailPos._1 then tailPos = (tailPos._1 - 1, tailPos._2)
                        logChanges(tailPos)
                    case 'L' => 
                        headPos = (headPos._1 - 1, headPos._2)
                        if tailPos._1 - headPos._1 == 2 then
                            tailPos = (tailPos._1 - 1, tailPos._2)
                            if headPos._2 > tailPos._2 then tailPos = (tailPos._1, tailPos._2 + 1)
                            else if headPos._2 < tailPos._2 then tailPos = (tailPos._1, tailPos._2 - 1)
                        logChanges(tailPos)
                    case 'R' => 
                        headPos = (headPos._1 + 1, headPos._2)
                        if headPos._1 - tailPos._1 == 2 then
                            tailPos = (tailPos._1 + 1, tailPos._2)
                            if headPos._2 > tailPos._2 then tailPos = (tailPos._1, tailPos._2 + 1)
                            else if headPos._2 < tailPos._2 then tailPos = (tailPos._1, tailPos._2 - 1)
                        logChanges(tailPos)
                    case _ => println()
        )

        println(visited.map( p => p.count(_ == 1)).sum)

    def part2: Unit =
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day9/puzzle.txt").getLines.toVector
        var visited = Array.ofDim[Int](10000, 10000)
        val startPos = (visited.length/2, visited(0).length/2)

        var headPos = startPos
        var knotsPoses = Vector.fill(9)(startPos)
        
        for i <- 0 until visited.length do 
            for j <- 0 until visited(0).length do
                visited(i)(j) = 0

        visited(startPos._1)(startPos._2) = 1

        def logChanges(pos: (Int, Int)): Unit = 
            visited(pos._1)(pos._2) = 1
        
        def moveWithKnot(otherPos: (Int, Int), ownPos: (Int, Int), knot: Int): Unit = 
            var pos = ownPos

            if ownPos._2 - otherPos._2 == 2 then
                pos = (ownPos._1, ownPos._2 - 1)
                if otherPos._1 > ownPos._1 then pos = (pos._1 + 1, pos._2)
                else if otherPos._1 < ownPos._1 then pos = (pos._1 - 1, pos._2)
            else if otherPos._2 - ownPos._2 == 2 then
                pos = (ownPos._1, ownPos._2 + 1)
                if otherPos._1 > ownPos._1 then pos = (pos._1 + 1, pos._2)
                else if otherPos._1 < ownPos._1 then pos = (pos._1 - 1, pos._2)
            else if ownPos._1 - otherPos._1 == 2 then
                pos = (ownPos._1 - 1, ownPos._2)
                if otherPos._2 > ownPos._2 then pos = (pos._1, pos._2 + 1)
                else if otherPos._2 < ownPos._2 then pos = (pos._1, pos._2 - 1)
            else if otherPos._1 - ownPos._1 == 2 then
                pos = (ownPos._1 + 1, ownPos._2)
                if otherPos._2 > ownPos._2 then pos = (pos._1, pos._2 + 1)
                else if otherPos._2 < ownPos._2 then pos = (pos._1, pos._2 - 1)

            knotsPoses = knotsPoses.updated(knot, pos)
            if knot == 8 then logChanges(pos)

        src.foreach( row => 
            val direction =  row(0)
            val steps = row.substring(row.lastIndexOf(" ")).trim().toInt

            for i <- 0 until steps do
                direction match
                    case 'U' => 
                        headPos = (headPos._1, headPos._2 - 1)
                        moveWithKnot(headPos, knotsPoses(0), 0)
                        for i <- 1 until 9 do
                            moveWithKnot(knotsPoses(i-1), knotsPoses(i), i)
                    case 'D' => 
                        headPos = (headPos._1, headPos._2 + 1)
                        moveWithKnot(headPos, knotsPoses(0), 0)
                        for i <- 1 until 9 do
                            moveWithKnot(knotsPoses(i-1), knotsPoses(i), i)
                    case 'L' => 
                        headPos = (headPos._1 - 1, headPos._2)
                        moveWithKnot(headPos, knotsPoses(0), 0)
                        for i <- 1 until 9 do
                            moveWithKnot(knotsPoses(i-1), knotsPoses(i), i)
                    case 'R' => 
                        headPos = (headPos._1 + 1, headPos._2)
                        moveWithKnot(headPos, knotsPoses(0), 0)
                        for i <- 1 until 9 do
                            moveWithKnot(knotsPoses(i-1), knotsPoses(i), i)
                    case _ => println()
        )

        println(visited.map( p => p.count(_ == 1)).sum)