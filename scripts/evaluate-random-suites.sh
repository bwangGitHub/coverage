#!/bin/bash
if [ -z "$1" ]; then
    echo "Aborting: you must specify a property file to read"
    exit 1
fi

cd "`dirname "$0"`"
BASE_DIR="`dirname $PWD`"
echo "Set base directory to $BASE_DIR"

echo "Reading the properties file"
. ./$1

echo "Setting shell settings"
set -x
set -e

echo "Evaluating random suites"

echo "Rebuilding evaluation code"
cd $PROJECT_DIR
ant -f $BASE_DIR/scripts/build.xml compile

echo "Setting up FIFOs"
rm -f tojava
rm -f fromjava
mkfifo tojava
mkfifo fromjava

echo "Initializing Java program"
rm -f $BASE_DIR/scripts/java.log
rm -f $BASE_DIR/scripts/java.log.*
java -cp "$INSTRUMENTED_CLASSPATH" -Xmx4096m $PROJECT_JVM_ARGS ca.uwaterloo.swag.tat.CommandParser $MASTER_SUITE_PACKAGE.$MASTER_SUITE_NAME $PIT_RESULT_DIR/run-pit-err.log $BASE_DIR/scripts/java.log <tojava >fromjava &
JAVA_PID=$!
trap "rm -f tojava; rm -f fromjava; kill $JAVA_PID" EXIT
exec 3>tojava
exec 4<fromjava
read ACK <&4
echo "gms all" >&3
read ACK <&4

echo "Gathering suite data"
DATA_FILE=$RESULT_DIR/randomSuiteResults.csv
echo "suite.size,stmt.coverage,branch.coverage,mc.coverage,mutants.killed,mutants.covered" > $DATA_FILE

if [[ $PROJECT = "apache-poi" || $PROJECT = "jfreechart" ]]; then
    LOOP_RANGE="3 10 30 100 300 1000"
elif [[ $PROJECT = "hsqldb" ]]; then
    LOOP_RANGE="3 10 30 100 300"
elif [[ $PROJECT = "joda-time" || $PROJECT = "closure" ]]; then
    LOOP_RANGE="3 10 30 100 300 1000 3000"
fi

for SIZE in $LOOP_RANGE
do
    for SUITE_NUM in {0..999}
    do
        CURRENT_TEST_NAME=Random"$SIZE"Methods$SUITE_NUM
        CURRENT_TEST_DIR=$RESULT_DIR/random$SIZE/methods$SUITE_NUM
        mkdir -p $CURRENT_TEST_DIR
        FILE_HEADER="package $MASTER_SUITE_PACKAGE.random;\nimport junit.framework.*;\npublic class $CURRENT_TEST_NAME extends TestCase {\nstatic TestSuite randomSuite;\npublic $CURRENT_TEST_NAME (String testname) {super(testname);}";

        echo "Making test suite"
        echo "gts suite $SIZE" >&3
	read ACK <&4
        echo "wts $RANDOM_SUITE_SRC_DIR/$CURRENT_TEST_NAME.java $FILE_HEADER" >&3
        read ACK <&4

        echo -n $SIZE, >> $DATA_FILE

        echo "Compiling test suite"
        ant -f $ANT_FILE $COMPILE_COMMAND

        echo "Measuring coverage"
        $BASE_DIR/scripts/measure-coverage.sh $1 "$MASTER_SUITE_PACKAGE.random.$CURRENT_TEST_NAME" > $CURRENT_TEST_DIR/coverage.log
        cp $BASE_DIR/test-subjects/joda-time-results/CodeCover/codecover-results.html $BASE_DIR/test-subjects/joda-time-results/random"$SIZE"/methods"$SUITE_NUM"/codecover-results.html
        echo "pcl $RESULT_FILE" >&3
        read COVERAGE_SCORE <&4
        echo -n $COVERAGE_SCORE, >> $DATA_FILE

        echo "Computing mutant kill score excluding equivalent mutants"
	echo "cks exc" >&3
        read KILL_SCORE <&4
        echo $KILL_SCORE >> $DATA_FILE
    done
done

echo "exit" >&3
echo "Closing FIFOs"
exec 3>&-
exec 4>&-
wait
