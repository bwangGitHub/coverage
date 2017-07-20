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

# I don't think this does anything because Javalanche overrides max memory
export ANT_OPTS="-Xmx8192m -XX:MaxPermSize=256m -Xss8192m"

echo "Deleting old results"
rm -rf "$PROJECT_DIR/mutation-files"

echo "Cleaning the database"
cd $PROJECT_DIR
ant -f javalanche.xml $JAVALANCHE_ARGS deleteMutations #1>$LOG_DIR/javalanche_del_mutations_stdout.log 2>$LOG_DIR/javalanche_del_mutations_stderr.log

# echo "Running Javalanche's test tasks"
# cd $PROJECT_DIR
# cp "$TEMPLATE_DIR/javalanche.xml" $PROJECT_DIR
# ant -f javalanche.xml $JAVALANCHE_ARGS csc.test #1>$LOG_DIR/javalanche_test_stdout.log 2>$LOG_DIR/javalanche_test_stderr.log

echo "Setting up Javalanche"
cd $PROJECT_DIR
ant -f javalanche.xml $JAVALANCHE_ARGS schemaexport scanProject #1>$LOG_DIR/javalanche_setup_stdout.log 2>$LOG_DIR/javalanche_setup_stderr.log
# echo "Check the exclude file to make sure it is correct ($PROJECT_DIR/mutation-files/exclude.txt) then press enter to continue"
# read DISCARD

# echo "Rebuilding Javalanche without System.exit call"
# sed s/"System.exit(1);"/"\/\/System.exit(1);"/ < $MUTATION_SCANNER_FILE > $BASE_DIR/temp.java
# if [ $? != 0 ]; then
#     echo "Aborting: sed failed, cannot rebuild Javalanche"
#     exit 1
# fi
# mv $BASE_DIR/temp.java $MUTATION_SCANNER_FILE
# cd $BASE_DIR/javalanche-src
# ./makeDist.sh
# cp -r "$BASE_DIR/javalanche-src/javalanche-0.4" $BASE_DIR
# cp $BONE_CP_JARS "$BASE_DIR/javalanche-0.4/lib"
# rm -r "$BASE_DIR/javalanche-0.4/src"
# cp -r "$BASE_DIR/javalanche/src" "$BASE_DIR/javalanche-0.4/"
# rm -r "$BASE_DIR/javalanche"
# mv "$BASE_DIR/javalanche-0.4" "$BASE_DIR/javalanche"

echo "Identifying potential mutations"
cd $PROJECT_DIR
ant -v -f javalanche.xml $JAVALANCHE_ARGS scan #1>$LOG_DIR/javalanche_scan_stdout.log 2>$LOG_DIR/javalanche_scan_stderr.log

# echo "Rebuilding Javalanche with System.exit call"
# sed s/"\/\/System.exit(1);"/"System.exit(1);"/ < $MUTATION_SCANNER_FILE > $BASE_DIR/temp.java
# if [ $? != 0 ]; then
#     echo "Aborting: sed failed, cannot rebuild Javalanche"
#     exit 1
# fi
# mv $BASE_DIR/temp.java $MUTATION_SCANNER_FILE
# cd $BASE_DIR/javalanche-src
# ./makeDist.sh
# cp -r "$BASE_DIR/javalanche-src/javalanche-0.4" $BASE_DIR
# cp $BONE_CP_JARS "$BASE_DIR/javalanche-0.4/lib"
# rm -r "$BASE_DIR/javalanche-0.4/src"
# cp -r "$BASE_DIR/javalanche/src" "$BASE_DIR/javalanche-0.4/"
# rm -r "$BASE_DIR/javalanche"
# mv "$BASE_DIR/javalanche-0.4" "$BASE_DIR/javalanche"

exit 0

if [ $RUN_IN_PARALLEL = "n" ]; then
    echo "Running the mutations in one thread"
    cd $PROJECT_DIR
    ant -f javalanche.xml $JAVALANCHE_ARGS createTasks #1>$LOG_DIR/javalanche_create_tasks_stdout.log 2>$LOG_DIR/javalanche_create_tasks_stderr.log
    ant -f javalanche.xml $JAVALANCHE_ARGS -Dmutation.dir="$PROJECT_DIR/mutation-files/" csc.run #1>$LOG_DIR/javalanche_run_stdout.log 2>$LOG_DIR/javalanche_run_stderr.log
else
   echo "Preparing to run mutations"
   cd $PROJECT_DIR
   ant -f javalanche.xml $JAVALANCHE_ARGS createTasks #1>$LOG_DIR/javalanche_create_tasks_stdout.log 2>$LOG_DIR/javalanche_create_tasks_stderr.log
   for I in 1 2 3 4
   do
       mkdir $PROJECT_DIR/mutation-files/$I
   done
   MUTATION_FILES=`find $PROJECT_DIR/mutation-files -name 'mutation-task-org*'`
   I=1
   for MFILE in $MUTATION_FILES
   do
       mv $MFILE $PROJECT_DIR/mutation-files/$I
       let I=I%4+1
   done

   echo "Running the mutations in four threads"
   cd $PROJECT_DIR
   for I in 1 2 3 4
   do
       ant -f javalanche.xml $JAVALANCHE_ARGS -Dmutation.dir="$PROJECT_DIR/mutation-files/$I" csc.run.parallel #1>$LOG_DIR/javalanche_run_"$I"_stdout.log 2>$LOG_DIR/javalanche_run_"$I"_stderr.log &
   done
   wait 
fi

echo "Analyzing results"
cd $PROJECT_DIR
echo $UNINSTRUMENTED_CLASSPATH
ant -f javalanche.xml $JAVALANCHE_ARGS analyzeResults #1>$LOG_DIR/javalanche_analysis_stdout.log 2>$LOG_DIR/javalanche_analysis_stderr.log
