import scala.io.Source

object Day6:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day6/puzzle.txt").getLines.toVector

        val times = src(0).substring(src(0).indexOf(":") + 1).split(" ").map(c=>c.trim()).filter(!_.isEmpty()).map(c=>c.toInt).toVector
        val distances = src(1).substring(src(1).indexOf(":") + 1).split(" ").map(c=>c.trim()).filter(!_.isEmpty()).map(c=>c.toInt).toVector

        var product = 1

        for i <- 0 until times.length do
            var wins = 0
            val time = times(i)
            val distance = distances(i)

            for j <- 0 until time do 
                val speed = j
                val timeLeft = time - j 
                val d = speed * timeLeft

                if d > distance then wins += 1

            if wins > 0 then product *= wins


        println(product)

    def part2: Unit =
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day6/puzzle.txt").getLines.toVector

        import scala.math.BigInt.*

        val time = src(0).substring(src(0).indexOf(":") + 1).split(" ").map(c=>c.trim()).filter(!_.isEmpty()).mkString.toInt
        val distance = BigInt(src(1).substring(src(1).indexOf(":") + 1).split(" ").map(c=>c.trim()).filter(!_.isEmpty()).mkString)

        var wins = 0

        for a <- 1 to time do
            val b = time - a
            if a * b > distance then wins += 1

        println(wins)

    



       

        
