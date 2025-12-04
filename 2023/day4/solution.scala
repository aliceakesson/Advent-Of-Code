import scala.io.Source

object Day4:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day4/puzzle.txt").getLines.toVector

        var sum = 0

        src.foreach(card => 
                val winning = card.substring(card.indexOf(':') + 1, card.indexOf('|')).trim().split(' ').map(c => c.trim()).toVector.filter(c => !c.isEmpty())
                val yours = card.substring(card.indexOf('|') + 1).trim().split(' ').map(c => c.trim()).toVector.filter(c => !c.isEmpty())

                val both = winning.intersect(yours)

                if both.length == 1 then sum += 1
                else if both.length > 1 then sum += Math.pow(2, both.length - 1).toInt
        )

        println(sum)


    def part2: Unit =
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day4/puzzle.txt").getLines.toVector
        var extras = Map.empty[Int, Int]

        for i <- 0 until src.length do 
            val card = src(i)
            val winning = card.substring(card.indexOf(':') + 1, card.indexOf('|')).trim().split(' ').map(c => c.trim()).toVector.filter(c => !c.isEmpty())
            val yours = card.substring(card.indexOf('|') + 1).trim().split(' ').map(c => c.trim()).toVector.filter(c => !c.isEmpty())

            val both = winning.intersect(yours)

            var amountOfCards = both.length
            while amountOfCards > 0 do
                val indexOfCard = i + amountOfCards 
                if indexOfCard < src.length - 1 then 
                    var added = 1
                    if extras.contains(i) then added = extras.get(i).get + 1
                    if extras.contains(indexOfCard) then
                        val value = extras.get(indexOfCard).get
                        extras = extras.updated(indexOfCard, value + added)
                    else extras = extras.updated(indexOfCard, added)
                amountOfCards -= 1

        var sum = 202
        extras.values.foreach(v => sum += v)

        println(sum)


       

        
