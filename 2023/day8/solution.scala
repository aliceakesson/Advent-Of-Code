import scala.io.Source

object Day8:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day8/puzzle.txt").getLines.toVector

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
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day8/puzzle.txt").getLines.toVector

        val directions = src(0).trim().toVector
        val nodes = src.drop(2)

        var current = nodes.filter(n => n.split(' ')(0).endsWith("A"))
        var found = false

        var count = 0

        while !found do
            directions.foreach(dir => 
                for i <- current.indices do
                    val node = nodes.filter(n => n.startsWith(current(i)))(0)

                    dir match
                        case 'L' =>
                            current = current.updated(i, node.substring(node.indexOf("(") + 1, node.indexOf(",")).trim())
                        case _ => 
                            current = current.updated(i, node.substring(node.indexOf(",") + 1, node.indexOf(")")).trim())

                count += 1

                if current.forall(n => n.split(' ')(0).endsWith("Z")) then found = true
                println(current.filter(n => n.split(' ')(0).endsWith("Z")).size)
            )

        println(count)


