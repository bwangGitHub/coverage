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

echo "Evaluating single methods"

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
echo "gnt" >&3
read NUM_TESTS <&4
let NUM_TESTS-=1
echo $NUM_TESTS
echo "Gathering suite data"
DATA_FILE=$RESULT_DIR/singleMethodResults.csv
echo "method.name,stmt.coverage,branch.coverage,mc.coverage,mutants.killed,mutants.covered" > $DATA_FILE

for I in $(seq 2538 $NUM_TESTS)
do
    CURRENT_TEST_NAME=SingleMethod$I
    CURRENT_TEST_DIR=$RESULT_DIR/SingleMethods/SingleMethod$I
    mkdir -p $CURRENT_TEST_DIR
    FILE_HEADER="package $MASTER_SUITE_PACKAGE.random;\nimport junit.framework.*;\npublic class $CURRENT_TEST_NAME extends TestCase {\nstatic TestSuite randomSuite;\npublic $CURRENT_TEST_NAME (String testname) {super(testname);}";

    echo "Making test suite"
    echo "gts method $I" >&3
    read ACK <&4
    echo "gtn" >&3
    read TEST_NAME <&4
    echo -n $TEST_NAME, >> $DATA_FILE

    echo "Compiling test suite"
    echo "wts $RANDOM_SUITE_SRC_DIR/$CURRENT_TEST_NAME.java $FILE_HEADER" >&3
    read ACK <&4
    cp $RANDOM_SUITE_SRC_DIR/$CURRENT_TEST_NAME.java $CURRENT_TEST_DIR
    ant -f $ANT_FILE $COMPILE_COMMAND

    echo "Measuring coverage"
    set +e
    $BASE_DIR/scripts/measure-coverage.sh $1 "$MASTER_SUITE_PACKAGE.random.$CURRENT_TEST_NAME" > $CURRENT_TEST_DIR/coverage.log
    if [ $? -eq 0 ]; then
	cp -r $RESULT_DIR/CodeCover/codecover-results.html-files $RESULT_DIR/SingleMethods/SingleMethod$I/codecover-results.html-files
	cp $RESULT_DIR/CodeCover/codecover-results.html $RESULT_DIR/SingleMethods/SingleMethod$I/codecover-results.html
	echo "pcl $RESULT_FILE" >&3
	read COVERAGE_SCORE <&4
	echo -n $COVERAGE_SCORE, >> $DATA_FILE
    else
	echo -n "0.0,0.0,0.0," >> $DATA_FILE
    fi
    set -e
    
    echo "Computing mutant kill score excluding equivalent mutants"
    echo "cks exc" >&3
    read KILL_SCORE <&4
    echo $KILL_SCORE >> $DATA_FILE
done

