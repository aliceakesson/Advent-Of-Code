import scala.io.Source

object Day2:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day2/puzzle.txt").getLines.toVector

        var sum = 0
        
        src.foreach(line => 
            val ys = line.substring(line.indexOf(':') + 1).trim().split(";").map(c => c.trim()).toVector.map(cube => cube.split(",").map(e => e.trim()).toVector)
            var possible = true

            ys.foreach(cube => 
                var red = 0
                var blue = 0
                var green = 0

                cube.foreach(e => 
                    val value = e.substring(0, e.indexOf(" ")).trim().toInt
                    val color = e.substring(e.indexOf(" ")).trim()

                    color match
                        case "red" => red += value
                        case "blue" => blue += value
                        case "green" => green += value
                        case _ =>    
                )

                if red > 12 || green > 13 || blue > 14 then possible = false
            )

            if possible then
                val number = line.substring(line.indexOf(" "), line.indexOf(":")).trim().toInt
                println(number)
                sum += number
        )

        println(sum)

    def part2: Unit =
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day2/puzzle.txt").getLines.toVector

        var sum = 0
        
        src.foreach(line => 
            val ys = line.substring(line.indexOf(':') + 1).trim().split(";").map(c => c.trim()).toVector.map(cube => cube.split(",").map(e => e.trim()).toVector)

            var red = 0
            var blue = 0
            var green = 0

            ys.foreach(cube => 
                cube.foreach(e => 
                    val value = e.substring(0, e.indexOf(" ")).trim().toInt
                    val color = e.substring(e.indexOf(" ")).trim()

                    color match
                        case "red" => if red < value then red = value
                        case "blue" => if blue < value then blue = value
                        case "green" => if green < value then green = value
                        case _ =>    
                )
            )

            
            var power = 1
            val xs = Vector(red, blue, green).filter(color => color > 0).foreach(c => power *= c)
            println(s"red: $red, blue: $blue, green: $green, power: $power")

            sum += power

        )

        println(sum)

        
