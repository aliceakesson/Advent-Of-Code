import scala.io.Source

object Day7:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day7/puzzle.txt").getLines.toVector

        def cti(c: Char): Int = 
            if c.isDigit then (c.toInt - 48)
            else if c.equals('T') then 10
            else if c.equals('J') then 11
            else if c.equals('Q') then 12
            else if c.equals('K') then 13
            else if c.equals('A') then 14
            else 0

        val orderedCards = src
                .map(line => (line.split(" ").map(c=>c.trim()).toVector(0).toVector -> line.split(" ").map(c=>c.trim()).toVector(1)))
                .sortBy(xs => 
                            val card = xs._1
                            if card.forall(_.equals(card.head)) then (7, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.groupBy(identity).mapValues(_.size).values.count(_ == 4) == 1 then (6, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.groupBy(identity).mapValues(_.size).values.count(_ == 2) == 1 && 
                                    card.groupBy(identity).mapValues(_.size).values.count(_ == 3) == 1 then (5, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.groupBy(identity).mapValues(_.size).values.count(_ == 3) == 1 then (4, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.groupBy(identity).mapValues(_.size).values.count(_ == 2) == 2 then (3, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.groupBy(identity).mapValues(_.size).values.count(_ == 2) == 1 then (2, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else (0, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                )
        
        var winnings = 0

        for i <- orderedCards.indices do 
            val rank = i + 1
            val value = orderedCards(i)._2.toInt
            winnings += rank * value

        println(winnings)

    def part2: Unit =
        var src = Source.fromFile("C:/Users/alcea/coding/aoc/2023/day7/puzzle.txt").getLines.toVector

        def cti(c: Char): Int = 
            if c.isDigit then (c.toInt - 48)
            else if c.equals('T') then 10
            else if c.equals('J') then -1
            else if c.equals('Q') then 12
            else if c.equals('K') then 13
            else if c.equals('A') then 14
            else 0

        val orderedCards = src
                .map(line => (line.split(" ").map(c=>c.trim()).toVector(0).toVector -> line.split(" ").map(c=>c.trim()).toVector(1)))
                .sortBy(xs => 
                            val card = xs._1
                            if card.exists(c => card.forall(d => d.equals(c)) || card.count(_.equals(c)) + card.count(_.equals('J')) >= 5) 
                                then (7, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.exists(c => card.count(_.equals(c)) + card.count(_.equals('J')) == 4) then (6, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.exists(c => card.count(_.equals(c)) + card.count(_.equals('J')) == 2 && card.exists(d => !d.equals(c) && !d.equals('J') && card.count(_.equals(d)) == 3)) || 
                                    card.exists(c => card.count(_.equals(c)) + card.count(_ .equals('J')) == 3 && card.exists(d => !d.equals(c) && !d.equals('J') && card.count(_.equals(d)) == 2)) 
                                        then (5, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.exists(c => card.count(_.equals(c)) + card.count(_.equals('J')) == 3) then (4, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.exists(c => card.count(_.equals(c)) == 2 && card.count(_.equals('J')) >= 1) || card.count(_.equals('J')) >= 2 
                                then (3, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else if card.exists(c => card.count(_.equals(c)) == 2) || card.count(_.equals('J')) >= 1 then (2, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                            else (1, cti(card(0)), cti(card(1)), cti(card(2)), cti(card(3)), cti(card(4)))
                )
        
        var winnings = 0

        for i <- orderedCards.indices do 
            val rank = i + 1
            val value = orderedCards(i)._2.toInt
            // println(s"rank: $rank, value: $value, card: ${orderedCards(i)}")
            winnings += rank * value

        println(winnings)