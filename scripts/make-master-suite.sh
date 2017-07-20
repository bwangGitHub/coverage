#!/bin/bash
# Requires one argument: the name of the property file to read
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
cp "$TEMPLATE_DIR/MasterSuiteGenerator.java" $MASTER_SUITE_DEST
sed s/"MASTER_SUITE_PACKAGE"/"$MASTER_SUITE_PACKAGE"/ < "$MASTER_SUITE_DEST/MasterSuiteGenerator.java" > "$MASTER_SUITE_DEST/MasterSuiteGeneratorTemp.java"
if [ $? != 0 ]; then
    echo "Aborting: sed failed, cannot generate master suite (package name)"
    exit 1
fi
mv $MASTER_SUITE_DEST/MasterSuiteGeneratorTemp.java $MASTER_SUITE_DEST/MasterSuiteGenerator.java
sed s/"MASTER_SUITE_PACKAGE"/"$MASTER_SUITE_PACKAGE;"/ < "$MASTER_SUITE_DEST/MasterSuiteGenerator.java" > "$MASTER_SUITE_DEST/MasterSuiteGeneratorTemp.java"
if [ $? != 0 ]; then
    echo "Aborting: sed failed, cannot generate master suite (package name 2)"
    exit 1
fi
mv $MASTER_SUITE_DEST/MasterSuiteGeneratorTemp.java $MASTER_SUITE_DEST/MasterSuiteGenerator.java
sed s\{"TEST_SRC_DIR"\{"$TEST_SRC_DIR/"\{ < "$MASTER_SUITE_DEST/MasterSuiteGenerator.java" > "$MASTER_SUITE_DEST/MasterSuiteGeneratorTemp.java"
if [ $? != 0 ]; then
    echo "Aborting: sed failed, cannot generate master suite (source directory)"
    exit 1
fi
mv $MASTER_SUITE_DEST/MasterSuiteGeneratorTemp.java $MASTER_SUITE_DEST/MasterSuiteGenerator.java
sed s/"OFFSET"/"$OFFSET"/ < "$MASTER_SUITE_DEST/MasterSuiteGenerator.java" > "$MASTER_SUITE_DEST/MasterSuiteGeneratorTemp.java"
if [ $? != 0 ]; then
    echo "Aborting: sed failed, cannot generate master suite (offset)"
    exit 1
fi
mv $MASTER_SUITE_DEST/MasterSuiteGeneratorTemp.java $MASTER_SUITE_DEST/MasterSuiteGenerator.java

ant -f $ANT_FILE $COMPILE_COMMAND
java -cp "$UNINSTRUMENTED_CLASSPATH" $MASTER_SUITE_PACKAGE.MasterSuiteGenerator
ant -f $ANT_FILE $COMPILE_COMMAND
