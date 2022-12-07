import scala.io.Source

object Day7:
    @main
    def run: Unit =
        part1
        part2

    def part1: Unit = 
        var paths = Vector.empty[String]
        var sumCount = Vector.empty[Int]
        val src = Source.fromFile("C:/Users/alcea/coding/aoc/2022/day7/puzzle.txt").getLines.toVector
        var dirCount = -1
        var path = ""
        var defaultPath = "/"

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
        
        // def getRows(dirName: String): Vector[String] = 
        //     if dirName == ".." then Vector.empty[String] 
        //     else 
        //         val r1 = src.dropWhile(p => !(p.startsWith("$ cd") && p.substring(p.lastIndexOf(" ")) == dirName))
        //         val r2 = r1.drop(2).takeWhile(p => !p.startsWith("$ cd"))
        //         r2

        def getRows(path: String): Vector[String] = 
            var result = Vector.empty[String]
            if path != defaultPath then 
                val amountOfDirs = path.count(_ == defaultPath(0))
                var pathLeft = path.substring(1)
                var rows = src

                if amountOfDirs > 1 then   
                    rows = src.dropWhile(p => !(p.startsWith("$ cd") && p.substring(p.lastIndexOf(" ")).trim() == pathLeft.substring(0, pathLeft.indexOf(defaultPath))))
                    for i <- 2 to amountOfDirs do
                        pathLeft = pathLeft.substring(pathLeft.indexOf(defaultPath)+1)
                        if i == amountOfDirs then
                            rows = rows.drop(1).dropWhile(p => !(p.startsWith("$ cd") && p.substring(p.lastIndexOf(" ")).trim() == pathLeft)) 
                        else 
                            rows = rows.drop(1).dropWhile(p => !(p.startsWith("$ cd") && p.substring(p.lastIndexOf(" ")).trim() == pathLeft.substring(0, pathLeft.indexOf(defaultPath)))) 
                else if amountOfDirs == 1 then 
                    rows = src.dropWhile(p => !(p.startsWith("$ cd") && p.substring(p.lastIndexOf(" ")).trim() == pathLeft))
                result = rows.drop(1).takeWhile(p => !p.startsWith("$ cd"))
            else 
                result = src.drop(1).takeWhile(p => !p.startsWith("$ cd"))
            result

        src.foreach( row =>
            if row.startsWith("$ cd") then
                val dir = row.substring(row.lastIndexOf(" ")).trim()
                if dir != ".." then 
                    if dir != defaultPath then
                        if path != defaultPath then path += defaultPath
                        path += dir
                    else if dir == defaultPath then
                        path = defaultPath
                else if dir == ".." then
                    if path.lastIndexOf(defaultPath) == 0 then path = defaultPath
                    else path = path.substring(0, path.lastIndexOf(defaultPath))
                
                if !paths.contains(path) then
                    sumCount = sumCount :+ 0
                    dirCount += 1
                    paths = paths :+ path

                    addFileSizes(getRows(path))

                    getRows(path).foreach( r1 => 
                        if r1.startsWith("dir") then
                            var p1 = ""
                            if path == defaultPath then p1 = s"$path${r1.substring(r1.lastIndexOf(" ")).trim()}" 
                            else p1 = s"$path/${r1.substring(r1.lastIndexOf(" ")).trim()}" 
                            addFileSizes(getRows(p1))

                            getRows(p1).foreach(r2 => 
                                if r2.startsWith("dir") then
                                    val p2 = s"$p1/${r2.substring(r2.lastIndexOf(" ")).trim()}" 
                                    addFileSizes(getRows(p2))

                                    getRows(p2).foreach( r3 => 
                                        if r3.startsWith("dir") then 
                                            val p3 = s"$p2/${r3.substring(r3.lastIndexOf(" ")).trim()}" 
                                            addFileSizes(getRows(p3))
                                            
                                            getRows(p3).foreach( r4 =>
                                                val p4 = s"$p2/${r4.substring(r4.lastIndexOf(" ")).trim()}" 
                                                addFileSizes(getRows(p4)) 

                                                getRows(p4).foreach( r5 => 
                                                    val p5 = s"$p2/${r5.substring(r5.lastIndexOf(" ")).trim()}" 
                                                    addFileSizes(getRows(p5)) 

                                                    getRows(p5).foreach( r6 => 
                                                        val p6 = s"$p2/${r6.substring(r6.lastIndexOf(" ")).trim()}" 
                                                        addFileSizes(getRows(p6))   
                                                    )
                                                )
                                            )
                                    )
                            )  
                    )
        )
        println(sumCount.filter(_ <= 100000).sum)

    def part2: Unit = println("")
       
