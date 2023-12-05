import scala.io.Source

object Day5:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day5/puzzle.txt").getLines.toVector

        val seeds = src(0).substring(src(0).indexOf(":") + 1).trim().split(" ").map(s=>s.trim().toLong).toVector

        def addValues(source: Vector[Long], start: Int, end: Int): Map[Long, Long] =
            var ys = Map.empty[Long, Long]

            for i <- start to end do
                val values = src(i).split(" ").map(s=>s.trim().toLong).toVector
                val min = values(1)
                val range = values(2)
                val startValue = values(0)

                source.foreach(s =>
                    val diff = s - min
                    if s >= min && diff < range then 
                        val result = startValue + diff
                        ys = ys + (s -> result)
                )

            source.foreach(s =>
                if !ys.keySet.contains(s) then ys = ys + (s -> s)
            )

            ys

        var soils = Map.empty[Long, Long]
        soils = addValues(seeds, 3, 19)

        var fertilizers = Map.empty[Long, Long]
        fertilizers = addValues(soils.values.toVector, 22, 47)

        var waters = Map.empty[Long, Long]
        waters = addValues(fertilizers.values.toVector, 50, 96)

        var lights = Map.empty[Long, Long]
        lights = addValues(waters.values.toVector, 99, 106)

        var temperatures = Map.empty[Long, Long]
        temperatures = addValues(lights.values.toVector, 109, 123)

        var humidities = Map.empty[Long, Long]
        humidities = addValues(temperatures.values.toVector, 126, 165)

        var locations = Map.empty[Long, Long]
        locations = addValues(humidities.values.toVector, 168, 191)

        println(locations.values.min)

    def part2: Unit =
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day5/puzzle.txt").getLines.toVector

        def pairs[A](as: Array[A]) = as.grouped(2).map {
           case Array(x, y) => (x, y)
           case _          => sys.error("uneven size")
         }.toVector

        val input: Vector[(Long, Long)] = pairs(src(0).substring(src(0).indexOf(":") + 1).trim().split(" ").map(s=>s.trim().toLong))
        var seeds = Vector.empty[Long]

        println(seeds)

        def addValues(source: Vector[Long], start: Int, end: Int): Map[Long, Long] =
            var ys = Map.empty[Long, Long]

            for i <- start to end do
                val values = src(i).split(" ").map(s=>s.trim().toLong).toVector
                val min = values(1)
                val range = values(2)
                val startValue = values(0)

                source.foreach(s =>
                    val diff = s - min
                    if s >= min && diff < range then 
                        val result = startValue + diff
                        ys = ys + (s -> result)
                )

            source.foreach(s =>
                if !ys.keySet.contains(s) then ys = ys + (s -> s)
            )

            ys

        var soils = Map.empty[Long, Long]
        soils = addValues(seeds, 3, 19)

        var fertilizers = Map.empty[Long, Long]
        fertilizers = addValues(soils.values.toVector, 22, 47)

        var waters = Map.empty[Long, Long]
        waters = addValues(fertilizers.values.toVector, 50, 96)

        var lights = Map.empty[Long, Long]
        lights = addValues(waters.values.toVector, 99, 106)

        var temperatures = Map.empty[Long, Long]
        temperatures = addValues(lights.values.toVector, 109, 123)

        var humidities = Map.empty[Long, Long]
        humidities = addValues(temperatures.values.toVector, 126, 165)

        var locations = Map.empty[Long, Long]
        locations = addValues(humidities.values.toVector, 168, 191)

        println(locations.values.min)




       

        
