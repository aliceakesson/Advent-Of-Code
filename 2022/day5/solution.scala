import scala.io.Source

object Day5:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var crates = Vector("NWB", "BMDTPSZL", "RWZHQ", "RZJVDW", "BMHS", "BPVHJNGL", "SLDHFZQJ", "BQGJFSW", "JDCSMWZ").map(_.toVector)
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day5/puzzle.txt").getLines.toVector.drop(10)
        src.foreach( step =>
            val amount = Integer.parseInt(step.substring(step.indexOf(" ") + 1, step.indexOf(" ", step.indexOf(" ") + 1)).toString)
            val from = Integer.parseInt(step(step.indexOf(" ", step.indexOf(" ") + 1) + 6).toString())
            val to = Integer.parseInt(step(step.lastIndexOf(" ") + 1).toString)

            val fromRemoved = crates(from-1).drop(amount)
            var toAdded = crates(from-1).take(amount).reverse ++ crates(to-1)
            
            crates = crates.updated(to-1, toAdded)
            crates = crates.updated(from-1, fromRemoved)

        )

        var result = ""
        crates.foreach( crate =>
            result += crate(0)
        )
        println(result)

    def part2: Unit = 
        var crates = Vector("NWB", "BMDTPSZL", "RWZHQ", "RZJVDW", "BMHS", "BPVHJNGL", "SLDHFZQJ", "BQGJFSW", "JDCSMWZ").map(_.toVector)
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day5/puzzle.txt").getLines.toVector.drop(10)
        src.foreach( step =>
            val amount = Integer.parseInt(step.substring(step.indexOf(" ") + 1, step.indexOf(" ", step.indexOf(" ") + 1)).toString)
            val from = Integer.parseInt(step(step.indexOf(" ", step.indexOf(" ") + 1) + 6).toString())
            val to = Integer.parseInt(step(step.lastIndexOf(" ") + 1).toString)

            val fromRemoved = crates(from-1).drop(amount)
            var toAdded = crates(from-1).take(amount) ++ crates(to-1)
            
            crates = crates.updated(to-1, toAdded)
            crates = crates.updated(from-1, fromRemoved)

        )

        var result = ""
        crates.foreach( crate =>
            result += crate(0)
        )
        println(result)

       
