import string
from itertools import izip

outfile = open("allSingleMethodResults.csv", "w")
results = open("singleMethodResults.csv", "r")
times = open("singleMethodTimes.csv", "r")

for line in izip(results, times):
    outfile.write(string.rstrip(line[0],"\n") + "," + string.rstrip(line[1],"\n") + "\n")

outfile.close()
results.close()
times.close()

