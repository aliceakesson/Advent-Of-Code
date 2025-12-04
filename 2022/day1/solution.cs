using System;
using System.Collections.Generic;
using System.Linq;

class Day1 {
    static void Main(string[] args) {
        Part1();
        Part2();
    }

    static void Part1() {
        string path = System.IO.File.ReadAllText("puzzle.txt");
        List<string> byElves = path.Split("\r\n\r\n").ToList<string>();
        
        int maxSum = 0; 

        foreach (string elf in byElves)
        {
            string[] foods = elf.Split("\n");
            int sum = 0;
            foreach(string food in foods) {
                sum += Int32.Parse(food);  
            }
            if(sum > maxSum)
                maxSum = sum;
        }

        System.Console.WriteLine(maxSum);
    }

    static void Part2() {
        string path = System.IO.File.ReadAllText("puzzle.txt");
        List<string> byElves = path.Split("\r\n\r\n").ToList<string>();
        
        List<int> amountOfCalories = new List<int>();
        int k = 0;
        foreach (string elf in byElves)
        {
            string[] foods = elf.Split("\n");
            foreach(string food in foods) {
                amountOfCalories.Add(0);
                int calories = Int32.Parse(food);
                amountOfCalories[k] += calories;
            }
            k++;
        }

        int maxSum = 0;

        for(int i = 0; i < 3; i++) {
            int max = amountOfCalories[0];
            foreach(int amount in amountOfCalories) {
                if(amount > max)
                    max = amount;
                
            }

            amountOfCalories.Remove(max);
            maxSum += max; 
        }

        System.Console.WriteLine(maxSum);
    }
}