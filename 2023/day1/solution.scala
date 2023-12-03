import scala.io.Source

object Day1:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day1/puzzle.txt").getLines.toVector

        var sum = 0

        src.foreach(line => 
            var found = false
            var a = '0'
            var b = '0'

            line.foreach(c =>
                if c.isDigit && !found then 
                    a = c
                    found = true
            )

            found = false
            line.reverse.foreach(c =>
                if c.isDigit && !found then 
                    b = c
                    found = true
            )

            val num = a.toString() + b.toString() + ""
            val numToInt = num.toInt

            sum += numToInt
            println(s"a: $a, b: $b, num: $num, numtoInt: $numToInt")
        )

        println(sum)

    def part2: Unit =
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day1/puzzle.txt").getLines.toVector

        var sum = 0
        val words = Map("one" -> '1', "two" -> '2', "three" -> '3', "four" -> '4', "five" -> '5', "six" -> '6', "seven" -> '7', "eight" -> '8', "nine" -> '9')

        src.foreach(line => 
            var found = false
            var a = '0'
            var b = '0'

            var a_index = -1
            var b_index = -1

            line.foreach(c =>
                if c.isDigit && !found then 
                    a = c
                    a_index = line.indexOf(c)
                    found = true
            )

            found = false
            line.reverse.foreach(c =>
                if c.isDigit && !found then 
                    b = c
                    b_index = line.lastIndexOf(c)
                    found = true
            )

            words.keySet.foreach(word =>
                if line.indexOf(word) >= 0 && (a_index < 0 || a_index > line.indexOf(word)) then
                     a  = words.get(word).get
                     a_index = line.indexOf(word)
                if line.lastIndexOf(word) >= 0 && (b_index < 0 || b_index < line.lastIndexOf(word)) then
                     b  = words.get(word).get
                     b_index = line.lastIndexOf(word)
            )

            val num = a.toString() + b.toString() + ""
            val numToInt = num.toInt

            sum += numToInt
            println(s"a: $a, b: $b, num: $num, numtoInt: $numToInt")
        )

        println(sum)
