import scala.io.Source

object Day2:
    @main
    def run: Unit =
        part1
        part2
    
    def part1: Unit = 
        var points = 0

        def pointsForwin(c1: Char, c2: Char): Int =
            c2 match
                case 'X' =>
                    c1 match
                        case 'A' => 1 + 3
                        case 'B' => 1
                        case 'C' => 1 + 6
                        case _ => 0
                case 'Y' =>
                    c1 match
                        case 'A' => 2 + 6
                        case 'B' => 2 + 3
                        case 'C' => 2
                        case _ => 0
                case 'Z' =>
                    c1 match
                        case 'A' => 3
                        case 'B' => 3 + 6
                        case 'C' => 3 + 3
                        case _ => 0
                case _ => 0


        val rounds = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day2/puzzle.txt").getLines
        rounds.foreach( s =>
            val characters = s.mkString(" ").toVector
            points += pointsForwin(characters(0), characters(4))
        )
        println(points)

    def part2: Unit = 
        var points = 0

        def pointsForwin(c1: Char, c2: Char): Int =
            c2 match
                case 'X' =>
                    c1 match
                        case 'A' => 3
                        case 'B' => 1
                        case 'C' => 2
                        case _ => 0
                case 'Y' =>
                    c1 match
                        case 'A' => 1 + 3
                        case 'B' => 2 + 3
                        case 'C' => 3 + 3
                        case _ => 0
                case 'Z' =>
                    c1 match
                        case 'A' => 2 + 6
                        case 'B' => 3 + 6
                        case 'C' => 1 + 6
                        case _ => 0
                case _ => 0


        val rounds = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day2/puzzle.txt").getLines
        rounds.foreach( s =>
            val characters = s.mkString(" ").toVector
            points += pointsForwin(characters(0), characters(4))
        )
        println(points)