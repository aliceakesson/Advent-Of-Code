import scala.io.Source

object Day8:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day8/puzzle.txt").getLines.toVector

        val amountOfRows = src.length
        val amountOfColumns = src(0).length
        var visibileTrees = Array.ofDim[Int](amountOfRows, amountOfColumns)

        for i <- 0 until amountOfRows do 
            for j <- 0 until amountOfColumns do 
                visibileTrees(i)(j) = 0

        for i <- 0 until amountOfRows do 
            for j <- 0 until amountOfColumns do 
                if i == 0 || j == 0 || i == amountOfRows-1 || j == amountOfColumns-1 then visibileTrees(i)(j) = 1
                else 
                    var isHidden = false
                    var isVisible = false
                    for x <- 0 until j do 
                        if src(i)(x) >= src(i)(j) then isHidden = true
                    if !isHidden then visibileTrees(i)(j) = 1
                    
                    if isHidden then
                        isHidden = false
                        if j == amountOfColumns-2 && src(i)(j+1) < src(i)(j) then isVisible = true
                        if isVisible then visibileTrees(i)(j) = 1
                        else 
                            isHidden = false
                            for x <- (j+1) until amountOfColumns do 
                                if src(i)(x) >= src(i)(j) then isHidden = true
                            if !isHidden then visibileTrees(i)(j) = 1
                            
                    if isHidden && !isVisible then  
                        isHidden = false
                        for x <- 0 until i do 
                            if src(x)(j) >= src(i)(j) then isHidden = true
                        if !isHidden then visibileTrees(i)(j) = 1
                        
                        if isHidden then
                            isHidden = false
                            isVisible = false
                            if i == amountOfRows-2 && src(i+1)(j) < src(i)(j) then isVisible = true
                            if isVisible then visibileTrees(i)(j) = 1
                            else 
                                for x <- i+1 until amountOfRows do 
                                    if src(x)(j) >= src(i)(j) then isHidden = true
                                if !isHidden then visibileTrees(i)(j) = 1

        println(visibileTrees.toVector.map( a => a.count( b => b == 1 )).sum)

    def part2: Unit =
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day8/puzzle.txt").getLines.toVector

        val amountOfRows = src.length
        val amountOfColumns = src(0).length
        var visibileTrees = Array.ofDim[Int](amountOfRows, amountOfColumns)

        for i <- 0 until amountOfRows do 
            for j <- 0 until amountOfColumns do 
                visibileTrees(i)(j) = 0


        def calculateScore(r: Int, c: Int): Int = 
            var top = 0
            var down = 0
            var left = 0
            var right = 0

            if r == 0 || c == 0 || r == amountOfRows-1 || c == amountOfColumns-1 then 0
            else 
                var result = 1

                var x = r-1
                while x > 0 && src(x)(c) < src(r)(c) do 
                    x -= 1
                result *= (r-x)

                x = c-1
                while x > 0 && src(r)(x) < src(r)(c) do 
                    x -= 1
                result *= (c-x)

                if r == amountOfRows-2 then
                    result *= 1
                else 
                    x = r+1
                    while x < amountOfRows && src(x)(c) < src(r)(c) do
                        x += 1
                    if x == amountOfRows then result *= (x-1-r) else result *= (x-r)

                if c == amountOfColumns-2 then
                    result *= 1
                else 
                    x = c+1
                    while x < amountOfColumns && src(r)(x) < src(r)(c) do
                        x += 1
                    if x == amountOfColumns then result *= (x-1-c) else result *= (x-c)

                result

        for i <- 0 until amountOfRows do 
            for j <- 0 until amountOfColumns do 
                visibileTrees(i)(j) = calculateScore(i, j)

        println(visibileTrees.toVector.map( a => a.max ).max)
       
