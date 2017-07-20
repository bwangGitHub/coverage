#!/bin/bash
# Requires one argument: the name of the property file to read
set -x
set -e

if [ -z "$1" ]; then
    echo "Aborting: no property file given"
    exit 1
fi

# BASE_DIR points to the test-analysis-toolkit directory
cd "`dirname "$0"`"
BASE_DIR="`dirname $PWD`"
echo "Reading the properties file"
. ./$1

cd $PROJECT_DIR
java $PROJECT_JVM_ARGS -cp $UNINSTRUMENTED_CLASSPATH org.pitest.mutationtest.MutationCoverageReport \
--reportDir $PIT_RESULT_DIR \
--targetClasses $MASTER_SUITE_PACKAGE.* \
--mutableCodePaths $BUILD_DIR \
--sourceDirs $SRC_DIR \
--jvmArgs $PROJECT_JVM_ARGS \
-verbose
cd -
