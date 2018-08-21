Good Day!

1) INSTRUCTIONS FOR RUNNING MY PROJECT

My Cache Simulator can be run easily from the command-line by compiling (using 'make' command) and running the CacheSimulator.java class with the specification of five parameters, namely:
 
1) The name of the address file you intend to test
2) The number of blocks in the L1 cache
3) The number of bytes per block in the L1 cache
4) The number of blocks in the L2 cache
5) The number of bytes per block in the L2 cache

Eg. java CacheSimulator.java 8 32 64 64

Please note: To run a configuration which uses the L1Cache ONLY, please leave the last two parameters blank!

2) PROOF THAT MY CACHE SIMULATOR WORKS PROPERLY

I ran my project with the following commands:

Input 1: java CacheSimulator 8 32 64 64

Output 1:
	L1 hits: 5
	L1 misses: 4
	cycles: 4050

Input 2: java CacheSimulator.java 8 32 64 64

Output 2:
	L1 hits: 2
	L1 misses: 6
	L2 hits: 3
	L2 misses: 3
	cycles: 3320


These 2 results are in compliance with the sample set of results provided to the class by our lecturer (Brian DeRenzi) and have also been compared to the results of peers. I can therefore assert that my Cache Simulator works properly. 


Kind Regards
Nkosi Gumede
