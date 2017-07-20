#!/bin/bash
# Requires one argument: the property file to read
# BASE_DIR points to the test-analysis-toolkit directory
cd "`dirname "$0"`"
BASE_DIR="`dirname $PWD`"
echo "Reading the properties file"
. $1

set -x
set -e

echo "Running the tests"
cd $PROJECT_DIR
for TEST_NUM in {0..3857}
do
    # First timing result is always very slow, so keep the third result
    for I in {1..3}
    do
	TIME=`/usr/bin/time -f'%e' java $PROJECT_JVM_ARGS -cp $UNINSTRUMENTED_CLASSPATH junit.textui.TestRunner org.joda.time.random.SingleMethod$TEST_NUM 2>&1 | tail -n1`
    done
    echo $TIME >> ~/research/test-analysis-toolkit/test-subjects/joda-time-results/singleMethodTimes.csv
done
