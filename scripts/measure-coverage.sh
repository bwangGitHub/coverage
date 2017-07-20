#!/bin/bash
# Requires two arguments: the property file to read and the test suite to run
# BASE_DIR points to the test-analysis-toolkit directory
cd "`dirname "$0"`"
BASE_DIR="`dirname $PWD`"
echo "Reading the properties file"
. $1

set -x
set -e

echo "Deleting old coverage results"
rm -f $PROJECT_DIR/*.clf
rm -rf $CODECOVER_RESULT_DIR
mkdir $CODECOVER_RESULT_DIR

echo "Copying the container file"
cp $TEMPLATE_DIR/test-session-container-$PROJECT.xml $CONTAINER
#$CODECOVER_COMMAND touch-container --container $CONTAINER

echo "Running the tests"
cd $PROJECT_DIR
java $PROJECT_JVM_ARGS -cp $INSTRUMENTED_CLASSPATH junit.textui.TestRunner $2

echo "Analyzing the coverage log files"
COVERAGE_LOGS=`find $PROJECT_DIR -name "*.clf"`
if [[ -z "$COVERAGE_LOGS" ]]; then 
    echo "No coverage files found; exiting early"
    exit 1
fi

I=1
for LOG in $COVERAGE_LOGS
do
    $CODECOVER_COMMAND analyze \
    --container $CONTAINER \
    --coverage-log $LOG \
    --name TestSession$I \
    --comment "none"
    let I=I+1
done

if [ $I -gt 2 ]; then
    echo "Merging the analyses"
    SESSION_ARGS="--session TestSession1"
    J=2
    while [ $J -lt $I ]; do
        SESSION_ARGS="$SESSION_ARGS --session TestSession$J"
        let J=J+1
    done
    $CODECOVER_COMMAND merge-sessions \
    --container $CONTAINER \
    $SESSION_ARGS \
    --name "AllSessions" \
    --comment "All Sessions"
    echo "Generating the report"
    $CODECOVER_COMMAND info \
    --session "AllSessions" \
    --container $CONTAINER
    $CODECOVER_COMMAND report \
    --container $CONTAINER \
    --destination $RESULT_FILE \
    --session "AllSessions" \
    --template $BASE_DIR/codecover/report-templates/HTML_Report_hierarchic.xml
else
    echo "Generating the report"
    $CODECOVER_COMMAND info \
    --session "TestSession1" \
    --container $CONTAINER

    $CODECOVER_COMMAND report \
    --container $CONTAINER \
    --destination $RESULT_FILE \
    --session "TestSession1" \
    --template $BASE_DIR/codecover/report-templates/HTML_Report_hierarchic.xml
fi