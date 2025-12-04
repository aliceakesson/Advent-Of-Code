import scala.io.Source

object Day10:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day10/puzzle.txt").getLines.toVector
        var x = 1
        var cycle = 1
        var sum = 0

        val cycles = Vector(20, 60, 100, 140, 180, 220)

        src.foreach( row =>  
            if row.startsWith("addx") then 
                if cycles.contains(cycle) then sum += (x * cycle)
                else if cycles.contains(cycle+1) then sum += (x * (cycle+1))
                cycle += 2
                try 
                    x += row.substring(row.lastIndexOf(" ")).trim().toInt
                catch
                    case nfe: NumberFormatException => println()
            else 
                if cycles.contains(cycle) then sum += (x * cycle)
                cycle += 1   
        )
        
        println(sum)

    def part2: Unit =
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day10/puzzle.txt").getLines.toVector
        var x = 1
        var cycle = 1

        var positions = Array.ofDim[Char](6, 40)
        var xPos = 0
        var yPos = 0 

        var spritePositions = Vector.fill(40)('.')

        def updateSpritePositons: Unit = 
            spritePositions = Vector.fill(40)('.')

            var add = x
            if add < 1 then add = 1 else if add > 38 then add = 38

            spritePositions = spritePositions.updated(add-1, '#')
            spritePositions = spritePositions.updated(add, '#')
            spritePositions = spritePositions.updated(add+1, '#')

        def posIsBeingDrawn(pos: Int): Boolean = 
            spritePositions(pos) == '#'

        def addCharacter: Unit = 
            if posIsBeingDrawn(xPos) then positions(yPos)(xPos) = '#'
            else positions(yPos)(xPos) = '.'
            
            xPos += 1
            if xPos > 39 then 
                xPos = 0
                yPos += 1

        for i <- 0 until positions.length do 
            for j <- 0 until positions(0).length do 
                positions(i)(j) = '.'

        updateSpritePositons

        src.foreach( row =>  
            if row.startsWith("addx") then 
                addCharacter
                cycle += 1
                addCharacter
                cycle += 1
                try 
                    x += row.substring(row.lastIndexOf(" ")).trim().toInt
                    updateSpritePositons
                catch
                    case nfe: NumberFormatException => throw new Exception
            else 
                cycle += 1   
                addCharacter
        )

        println(positions.map( p => p.mkString).mkString("\n"))        
