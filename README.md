# traffic-counter-service

## Language/Framework

- Scala/Sbt

## Problem statement

An automated traffic counter sits by a road and counts the number of cars that go past. Every half-hour the counter outputs the number of cars seen and resets the counter to zero. You are part of a development team that has been asked to implement a system to manage this data - the first task required is as follows :
Write a program that reads a file, where each line contains a timestamp (in yyyy-mm-dd​T​hh:mm:ss​ format) for the beginning of a half-hour and the number of cars seen that half hour. An example file is included on page 2.
The program should output:
● The number of cars seen in total
● A sequence of lines where each line contains a date (in ​yyyy-mm-dd​ format) and the
number of cars seen on that day (eg. 2016-11-23 289) for all days listed in the input file.
● A sequence of lines where each line contains a timestamp (in ​yyyy-mm-dd​T​hh:mm:ss format) and the number of cars seen in that half hour for the 3 half hours that saw the
most cars.
  
### SBT

Use `sbt test` for running the unit tests (after cloning the repo and also assuming scala/sbt is available), the test at the end once when successfully completed will generate the `output.txt` file in the project root directory.

Note: The input files are read from the test/resources folder
    
