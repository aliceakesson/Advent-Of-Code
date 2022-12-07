import scala.io.Source

object Day7:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var directories = Vector.empty[String]
        var sumCount = Vector.empty[Int]
        val src = Source.fromFile("C:/Users/alcea/coding/aoc22/day7/puzzle.txt").getLines.toVector
        var rowCount = 0
        var dirCount = -1

        def addFileSizes(xs: Vector[String]): Unit = 
            xs.foreach( x => 
                sumCount = sumCount.updated(dirCount, sumCount(dirCount) + getFileSize(x))
            )

        def getFileSize(s: String): Int = 
            try 
                if s.startsWith("$") || s.startsWith("dir") then 0 
                else 
                    s.substring(0, s.indexOf(" ")).toInt
            catch
                case nfe: NumberFormatException => 0
        
        def getRows(dirName: String): Vector[String] = 
            if dirName == ".." then Vector.empty[String] 
            else 
                val r1 = src.dropWhile(p => !(p.startsWith("$ cd") && p.substring(p.lastIndexOf(" ")) == dirName))
                val r2 = r1.drop(2).takeWhile(p => !p.startsWith("$ cd"))
                r2


        src.foreach( row =>
            if row.startsWith("$ cd") then
                val dir = row.substring(row.lastIndexOf(" "))
                if dir != ".." && !directories.contains(dir) then 
                    directories = directories :+ dir
                    sumCount = sumCount :+ 0
                    dirCount += 1
                
                addFileSizes(getRows(dir))

                getRows(dir).foreach( r1 => 
                    if r1.startsWith("dir") then
                        val d1 = r1.substring(r1.lastIndexOf(" "))
                        addFileSizes(getRows(d1))
                        getRows(d1).foreach(r2 => 
                            if r2.startsWith("dir") then
                                val d2 = r2.substring(r2.lastIndexOf(" "))
                                addFileSizes(getRows(d2))
                                getRows(d2).foreach( r3 => 
                                    if r3.startsWith("dir") then 
                                        val d3 = r3.substring(r3.lastIndexOf(" "))
                                        addFileSizes(getRows(d3))
                                        getRows(d3).foreach( r4 =>
                                            val d4 = r4.substring(r4.lastIndexOf(" "))
                                            addFileSizes(getRows(d4))  
                                            getRows(d4).foreach( r5 => 
                                                val d5 = r5.substring(r5.lastIndexOf(" "))
                                                addFileSizes(getRows(d5))    
                                                getRows(d5).foreach( r6 => 
                                                    val d6 = r6.substring(r6.lastIndexOf(" "))
                                                    addFileSizes(getRows(d6))   
                                                )
                                            )
                                        )
                                )
                        )  
                )
            rowCount += 1
        )
        println(sumCount.filter(_ <= 100000).sum)

    def part2: Unit = println("")
       
