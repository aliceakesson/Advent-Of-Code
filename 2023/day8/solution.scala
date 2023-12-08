import scala.io.Source
val pathToFolder = "C:/Users/alcea/coding/aoc/"

object Day8:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile(pathToFolder + "/2023/day8/puzzle.txt").getLines.toVector

        val directions = src(0).toVector
        val nodes = src.drop(2)

        var current = "AAA"
        val end = "ZZZ" 
        var found = false

        var count = 0

        while !found do
            directions.foreach(dir => 
                val node = nodes.filter(n => n.startsWith(current))(0)
                dir match
                    case 'L' =>
                        current = node.substring(node.indexOf("(") + 1, node.indexOf(",")).trim()
                    case _ => 
                        current = node.substring(node.indexOf(", ") + 1, node.indexOf(")")).trim()

                count += 1

                if current == end then found = true
            )

        println(count)


    def part2: Unit =
        var src = Source.fromFile(pathToFolder + "/2023/day8/puzzle.txt").getLines.toVector

        val directions = src(0).trim().toVector.filter(!_.toString().isEmpty())

        val nodes = src.drop(2).map(n => 
            (n.split(' ')(0), 
            n.substring(n.indexOf("(") + 1, n.indexOf(",")).trim(), 
            n.substring(n.indexOf(",") + 1, n.indexOf(")")).trim()))

        var current = nodes.map((a, b, c) => a).filter(_.endsWith("A"))

        var cycleLength = Vector.empty[Long]

        current.foreach(curr => 
            var n = curr
            var found = false
            var count: Long = 0

            while !found do
                directions.foreach(dir => 
                    if !found then
                        val node = nodes.filter((a, b, c) => a.equals(n))(0)

                        dir match
                            case 'L' =>
                                n = node(1)
                            case 'R' => 
                                n = node(2)

                        if n.endsWith(("Z")) then found = true else count += 1L
                )

            cycleLength = cycleLength :+ count
        )
        
        def gcd(a: Long, b: Long): Long = if b == 0 then a else gcd(b, a % b)
        def lcm(xs: Vector[Long]): Long = xs.reduce((a, b) => a*(b/gcd(a,b)))

        println(lcm(cycleLength))
        println(lcm(Vector(1000000000000L, 2)))