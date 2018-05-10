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

Example:

For the input file named `test_dataset.txt` in test/resources folder, the output that will get generated in the `output.txt` file for the above input file is

No. of cars seen in total : 398  
No. of cars seen in each day :   
2016-12-08 134  
2016-12-09 4  
2016-12-05 81  
2016-12-01 179  
3 half hours that saw the most cars :  
2016-12-01T07:30:00 46  
2016-12-01T08:00:00 42  
2016-12-08T18:00:00 33  
