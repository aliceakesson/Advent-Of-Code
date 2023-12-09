import scala.io.Source
val pathToFolder = "C:/Users/alcea/coding/aoc/"

object Day9:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile(pathToFolder + "/2023/day9/puzzle.txt").getLines.toVector

        var sum = 0

        src.foreach(line => 
            var ys: Vector[Int] = line.split(' ').map(_.trim().toInt).toVector
            var steps: Vector[Vector[Int]] = Vector(ys)

            var xs = Vector.empty[Int]
            for i <- 1 until ys.length do 
                xs = xs :+ ys(i) - ys(i - 1)
                
            steps = steps :+ xs

            while !xs.forall(_.equals(0)) do 
                ys = xs
                xs = Vector.empty[Int]
                for i <- 1 until ys.length do 
                    xs = xs :+ ys(i) - ys(i - 1)

                steps = steps :+ xs
    
            for i <- steps.indices.reverse do 
                if i == steps.length-1 then steps = steps.updated(i, steps(i) :+ 0)
                else steps = steps.updated(i, steps(i) :+ (steps(i).last + steps(i + 1).last))

            sum += steps(0).last
        )

        println(sum)

    def part2: Unit =
        var src = Source.fromFile(pathToFolder + "/2023/day9/puzzle.txt").getLines.toVector

        var sum = 0

        src.foreach(line => 
            var ys: Vector[Int] = line.split(' ').map(_.trim().toInt).toVector
            var steps: Vector[Vector[Int]] = Vector(ys)

            var xs = Vector.empty[Int]
            for i <- 1 until ys.length do 
                xs = xs :+ ys(i) - ys(i - 1)
                
            steps = steps :+ xs

            while !xs.forall(_.equals(0)) do 
                ys = xs
                xs = Vector.empty[Int]
                for i <- 1 until ys.length do 
                    xs = xs :+ ys(i) - ys(i - 1)

                steps = steps :+ xs
    
            for i <- steps.indices.reverse do 
                if i == steps.length-1 then steps = steps.updated(i, 0 +: steps(i))
                else steps = steps.updated(i, (steps(i).head - steps(i + 1).head) +: steps(i))

            sum += steps(0).head
        )

        println(sum)

