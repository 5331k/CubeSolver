
                         Cubes Puzzle Task

Create a computer program that solves the given cubes puzzles and
prints the solution(s) in ASCII format into a file in unfolded format
as given below as an example.

 - Develop the solution in Java using maven as build tool.
 - The core of the solution should be written without any add-ons to
   the core of the programming language. Use only the defined language
   standard (e.g. you can use all features that you find in a standard JDK).
   This means that you can use external libraries, but your program
   should still produce the wanted solutions after removing those
   parts. For example, if you would decide to use a logging library
   then your program would still work correctly after removing all
   parts that use the logging library. You can also use JUnit.
 - Optimize for speed of development and quality of development. Do
   not optimize for runtime execution speed. The problem is small and
   you can afford to waste a few milliseconds. It is better to focus
   on getting a working solution faster.

Have a look at the following link for some background information:
http://www.happycube.com/

Here is an example:
The pieces of the blue cube look as follows:

    []    []  []  []    []  
  [][][]  [][][][][]  [][][][]
[][][][][]  [][][]  [][][][] 
  [][][]  [][][][][]  [][][][]
    []    []  []  []    []  
  []  []    []  []    []  [] 
[][][][]  [][][][][]  [][][][]
  [][][][]  [][][]  [][][][]  
[][][][]  [][][][][]  [][][][]
[][]  []  []  []    [][]  [][]

One of the solutions in "unfolded form" looks as follows:
	 
    []        []  []  []  []  
  [][][]  [][][][][]  [][][] 
[][][][][]  [][][]  [][][][][]
  [][][]  [][][][][]  [][][] 
    []      []  [][]    []  
          []  []       
          [][][][][]     
            [][][]      
          [][][][][]     
            []  []      
          []  []  []     
          [][][][][]     
            [][][]      
          [][][][][]     
          []  []  []     
            []  []      
          [][][][]      
            [][][][]     
          [][][][]      
          [][]  []   	 


In the base task you are only required to find one solution and to
print it into a file. 