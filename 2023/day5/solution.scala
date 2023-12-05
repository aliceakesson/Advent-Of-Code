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

        val seeds: Vector[(Long, Long)] = pairs(src(0).substring(src(0).indexOf(":") + 1).trim().split(" ").map(s=>s.trim().toLong))

        /**
          * for varje range elem i source
          *     för varje range r bland raderna
          *         där r och seed överlappar: mappa rätt värden
          *         spara undan positioner där de överlappar 
          *     ta hela seeden minus alla där de överlappar 
          *     för denna del: mappa värde till värde 
        */

        def addValues(source: Vector[(Long, Long)], startRow: Int, endRow: Int): Map[(Long, Long), (Long, Long)] =
            var xs = Map.empty[(Long, Long), (Long, Long)]

            def intersection(r1: (Long, Long), r2: (Long, Long)): Option[(Long, Long)] = {
            val start = Math.max(r1._1, r2._1)
            val end = Math.min(r1._2, r2._2)

                if (start <= end) {
                    Some((start, end))
                } else {
                    None
                }
            }

            def findGaps(range: (Long, Long), parts: Vector[(Long, Long)]): Vector[(Long, Long)] = {
                val sortedRanges = parts.sortBy(r=>r(0))
                var gaps = Vector.empty[(Long, Long)]

                if sortedRanges.length > 0 then
                    var current = range(0)
                    sortedRanges.foreach((x1, x2) => 
                        if x1 > current then 
                            gaps = gaps :+ (current, x1 - 1)
                        current = x2 + 1
                    )
                    if sortedRanges.last(1) < range(1) then gaps = gaps :+ (sortedRanges.last(1) + 1, range(1))

                gaps
            }

            source.foreach((elemStart, elemRange) =>
                val elemEnd = elemStart + elemRange - 1
                var intersections = Vector.empty[(Long, Long)]

                for i <- startRow to endRow do
                    val values = src(i).split(" ").map(s=>s.trim().toLong).toVector
                    val min = values(1)
                    val range = values(2)
                    val startValue = values(0)
                    val rEnd = min + range - 1

                    //mappa rätt värden där de överlappar 
                    val r = (min, rEnd)
                    val intersect = intersection((elemStart, elemEnd), (min, rEnd))

                    //spara undan positioner där de överlappar 
                    if intersect != None then 
                        intersections = intersections :+ intersect.get
                        val diff = math.abs(intersect.get(0) - min)
                        val intersectLength = intersect.get(1) - intersect.get(0)
                        xs = xs + (intersect.get -> (startValue + diff, startValue + diff + intersectLength))
                        

                //ta hela seeden minus där de överlappar
                var gaps = findGaps((elemStart, elemEnd), intersections)

                //för denna del: mappa värde till värde
                gaps.foreach(gap => 
                    xs = xs + (gap -> gap)    
                )
            )

            xs

        var soils = Map.empty[(Long, Long), (Long, Long)]
        soils = addValues(seeds, 3, 19)

        

        var fertilizers =  Map.empty[(Long, Long), (Long, Long)]
        fertilizers = addValues(soils.values.toVector, 22, 47)

        var waters = Map.empty[(Long, Long), (Long, Long)]
        waters = addValues(fertilizers.values.toVector, 50, 96)

        var lights =  Map.empty[(Long, Long), (Long, Long)]
        lights = addValues(waters.values.toVector, 99, 106)

        var temperatures = Map.empty[(Long, Long), (Long, Long)]
        temperatures = addValues(lights.values.toVector, 109, 123)

        var humidities = Map.empty[(Long, Long), (Long, Long)]
        humidities = addValues(temperatures.values.toVector, 126, 165)

        var locations = Map.empty[(Long, Long), (Long, Long)]
        locations = addValues(humidities.values.toVector, 168, 191)

        println(locations.values.min._2)




       

        
