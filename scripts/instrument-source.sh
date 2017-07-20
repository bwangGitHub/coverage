#!/bin/bash
# Requires one argument: the name of the property file to read
# BASE_DIR points to the test-analysis-toolkit directory
cd "`dirname "$0"`"
BASE_DIR="`dirname $PWD`"
echo "Reading the properties file"
. $1

echo "Deleting old results"
rm -rf $INST_SRC_ROOT_DIR
rm -rf $INST_BUILD_ROOT_DIR
rm -f $PROJECT_DIR/*.clf
rm -rf $CODECOVER_RESULT_DIR
mkdir $CODECOVER_RESULT_DIR

echo "Instrumenting and building the source files"
if [ $PROJECT == "joda-time" ]; then
    mkdir "$INST_SRC_ROOT_DIR"
    cp -r $SRC_ROOT_DIR/* $INST_SRC_ROOT_DIR
    rm -rf $INST_SRC_DIR
    $CODECOVER_COMMAND instrument \
    --root-directory $SRC_DIR \
    --destination $INST_SRC_DIR \
    --container $CONTAINER \
    --language java \
    --charset UTF-8
    mkdir "$INST_BUILD_ROOT_DIR"
    mkdir "$INST_BUILD_DIR"
    ant -f $ANT_FILE cc.compile
    mv $INST_BUILD_ROOT_DIR/org $INST_BUILD_ROOT_DIR/classes/
    cp -r $BUILD_ROOT_DIR/conf $INST_BUILD_ROOT_DIR
    cp $SRC_ROOT_DIR/main/java/org/joda/time/format/*.properties $INST_BUILD_ROOT_DIR/classes/org/joda/time/format
    cp -r $BUILD_ROOT_DIR/classes/org/joda/time/tz/data $INST_BUILD_ROOT_DIR/classes/org/joda/time/tz/
elif [ $PROJECT == "apache-poi" ]; then
    mkdir "$INST_SRC_ROOT_DIR"
    cp -r $SRC_ROOT_DIR/* $INST_SRC_ROOT_DIR
    rm -rf $INST_SRC_DIR
    $CODECOVER_COMMAND instrument \
    --root-directory $SRC_DIR \
    --destination $INST_SRC_DIR \
    --container $CONTAINER \
    --language java \
    --charset UTF-8
    mkdir "$INST_BUILD_ROOT_DIR"
    mkdir "$INST_BUILD_DIR"
    ant -f $ANT_FILE cc-compile
elif [ $PROJECT == "jfreechart" ]; then
    # CodeCover can only instrument source in one directory, so merge source
    cp -r $PROJECT_DIR/experimental/* $PROJECT_DIR/source
    # Now make target directories
    mkdir "$PROJECT_DIR/instSource"
    cp -r $PROJECT_DIR/source/* $PROJECT_DIR/instSource
    rm -rf $PROJECT_DIR/instSource/org/jfree/chart
    mkdir "$PROJECT_DIR/instExperimental"
    cp -r $PROJECT_DIR/experimental/* $PROJECT_DIR/instExperimental
    rm -rf $PROJECT_DIR/instExperimental/org/jfree/experimental/chart
    # Now instrument
    $CODECOVER_COMMAND instrument \
    --root-directory $PROJECT_DIR/source  \
    --destination $PROJECT_DIR/instSource \
    --container $CONTAINER \
    --language java \
    --charset UTF-8
    # Now move instrumented experimental code back out
    mv $PROJECT_DIR/instSource/org/jfree/experimental/chart $PROJECT_DIR/instExperimental/org/jfree/experimental/chart 
    rm -rf $PROJECT_DIR/instSource/org/jfree/experimental
    ant -f $ANT_FILE cc-compile-tests
elif [ $PROJECT == "hsqldb" ]; then
    mkdir "$INST_SRC_ROOT_DIR"
    $CODECOVER_COMMAND instrument \
    --root-directory $SRC_DIR \
    --destination $INST_SRC_DIR \
    --container $CONTAINER \
    --language java \
    --charset UTF-8
    cd $SRC_DIR
    find . -name "*.properties" | cpio -pdm $INST_SRC_DIR
    find . -name "*.sql" | cpio -pdm $INST_SRC_DIR
    cd -
    mkdir "$INST_BUILD_ROOT_DIR"
    mkdir "$INST_BUILD_DIR"
    ant -f $PROJECT_DIR/build/build.xml cc-hsqldb
    ant -f $PROJECT_DIR/build/build.xml cc-preprocessor
    ant -f $PROJECT_DIR/build/build.xml cc-sqltool
elif [ $PROJECT == "closure" ]; then
    # CodeCover can't instrument multiple source dirs so we have to merge them
    # Start by uniquely identifying the source files under the four Rhino dirs
    RHINO_SRC_FILES=`find $PROJECT_DIR/lib/rhino/src -name "*.java"`
    for RFILE in $RHINO_SRC_FILES
    do
        DIRNAME=`dirname $RFILE`
	FILENAME=`basename $RFILE`
	cp $RFILE $DIRNAME/RHINO-SRC-$FILENAME
    done
    RHINO_DEP_FILES=`find $PROJECT_DIR/lib/rhino/deprecatedsrc -name "*.java"`
    for RFILE in $RHINO_DEP_FILES
    do
        DIRNAME=`dirname $RFILE`
	FILENAME=`basename $RFILE`
	cp $RFILE $DIRNAME/RHINO-DEP-$FILENAME
    done
    RHINO_TOOL_FILES=`find $PROJECT_DIR/lib/rhino/toolsrc -name "*.java"`
    for RFILE in $RHINO_TOOL_FILES
    do
        DIRNAME=`dirname $RFILE`
	FILENAME=`basename $RFILE`
	cp $RFILE $DIRNAME/RHINO-TOO-$FILENAME
    done
    RHINO_XML_FILES=`find $PROJECT_DIR/lib/rhino/xmlimplsrc -name "*.java"`
    for RFILE in $RHINO_XML_FILES
    do
        DIRNAME=`dirname $RFILE`
	FILENAME=`basename $RFILE`
	cp $RFILE $DIRNAME/RHINO-XML-$FILENAME
    done
    # Uniquely identify the files in the gen/ directory
    GEN_SRC_FILES=`find $PROJECT_DIR/gen -name "*.java"`
    for GFILE in $GEN_SRC_FILES
    do
        DIRNAME=`dirname $GFILE`
	FILENAME=`basename $GFILE`
	cp $GFILE $DIRNAME/GEN-SRC-$FILENAME
    done
    # Now move all files with the prefix RHINO to closure/src/org
    # To preserve structure the easy way, cp the whole directory and delete non-prefixed files
    # Delete must be done after each copy to prevent conflicts
    rm -rf $PROJECT_DIR/src/org
    mkdir $PROJECT_DIR/src/org
    cp -r $PROJECT_DIR/lib/rhino/src/org/* $PROJECT_DIR/src/org
    rm `find $PROJECT_DIR/src/org -type f -and -not -name "RHINO-*.java"`
    cp -r $PROJECT_DIR/lib/rhino/deprecatedsrc/org/* $PROJECT_DIR/src/org
    rm `find $PROJECT_DIR/src/org -type f -and -not -name "RHINO-*.java"` 
    cp -r $PROJECT_DIR/lib/rhino/toolsrc/org/* $PROJECT_DIR/src/org
    rm `find $PROJECT_DIR/src/org -type f -and -not -name "RHINO-*.java"` 
    cp -r $PROJECT_DIR/lib/rhino/xmlimplsrc/org/* $PROJECT_DIR/src/org
    rm `find $PROJECT_DIR/src/org -type f -and -not -name "RHINO-*.java"` 
    # Now delete all of the prefixed files in the original directory
    rm `find $PROJECT_DIR/lib/rhino -type f -name "RHINO-*.java"` 
    # Move the files with the prefix GEN to the closure/src/com
    # We can't use the same cp trick because there are already files in closure/src/com
    cd $PROJECT_DIR/gen
    GEN_SRC_FILES=`find com/google/ -name "GEN-*.java"`
    for GFILE in $GEN_SRC_FILES
    do
	mv $GFILE $PROJECT_DIR/src/$GFILE
    done
    cd -
    # Run the instrumentation command
    mkdir "$INST_SRC_ROOT_DIR"
    $CODECOVER_COMMAND instrument \
    --root-directory $SRC_DIR \
    --destination $INST_SRC_DIR \
    --container $CONTAINER \
    --language java \
    --charset UTF-8 \
    --exclude com/google/javascript/jscomp/InstrumentFunctions.java \
    --exclude org/mozilla/javascript/tools/debugger/RHINO-TOO-Dim.java
    # Prepare the build directories
    rm -rf $INST_BUILD_ROOT_DIR
    mkdir -p "$INST_BUILD_DIR"
    rm -rf $INST_GEN_ROOT_DIR
    mkdir "$INST_GEN_ROOT_DIR"
    rm -rf $INST_LIB_ROOT_DIR
    mkdir "$INST_LIB_ROOT_DIR"
    cp -r $PROJECT_DIR/lib/* $INST_LIB_ROOT_DIR
    rm -rf $INST_LIB_ROOT_DIR/rhino/src/org
    rm -rf $INST_LIB_ROOT_DIR/rhino/deprecatedsrc/org
    rm -rf $INST_LIB_ROOT_DIR/rhino/toolsrc/org
    rm -rf $INST_LIB_ROOT_DIR/rhino/xmlimplsrc/org
    # Split up the instrumented files
    mkdir $INST_LIB_ROOT_DIR/rhino/src/org
    cp -r $INST_SRC_DIR/org/* $INST_LIB_ROOT_DIR/rhino/src/org
    rm `find $INST_LIB_ROOT_DIR/rhino/src/org -type f -and -not -name "RHINO-SRC-*.java"`
    mkdir $INST_LIB_ROOT_DIR/rhino/deprecatedsrc/org
    cp -r $INST_SRC_DIR/org/* $INST_LIB_ROOT_DIR/rhino/deprecatedsrc/org
    rm `find $INST_LIB_ROOT_DIR/rhino/deprecatedsrc/org -type f -and -not -name "RHINO-DEP-*.java"`
    mkdir $INST_LIB_ROOT_DIR/rhino/toolsrc/org
    cp -r $INST_SRC_DIR/org/* $INST_LIB_ROOT_DIR/rhino/toolsrc/org
    rm `find $INST_LIB_ROOT_DIR/rhino/toolsrc/org -type f -and -not -name "RHINO-TOO-*.java"`
    mkdir $INST_LIB_ROOT_DIR/rhino/xmlimplsrc/org
    cp -r $INST_SRC_DIR/org/* $INST_LIB_ROOT_DIR/rhino/xmlimplsrc/org
    rm `find $INST_LIB_ROOT_DIR/rhino/xmlimplsrc/org -type f -and -not -name "RHINO-XML-*.java"`  
    cd $PROJECT_DIR/instSrc
    GEN_SRC_FILES=`find com/google/ -name "GEN-*.java"`
    for GFILE in $GEN_SRC_FILES
    do
        mkdir -p $INST_GEN_ROOT_DIR/`dirname $GFILE`
	mv $INST_SRC_ROOT_DIR/$GFILE $INST_GEN_ROOT_DIR/$GFILE
    done
    cd -
    # Strip the prefixes
    RHINO_FILES=`find $PROJECT_DIR/instLib/rhino/ -name "RHINO-*"`
    for RFILE in $RHINO_FILES
    do
        DIRNAME=`dirname $RFILE`
	NEW_FILENAME=`basename $RFILE | cut -c11-`
	mv $RFILE $DIRNAME/$NEW_FILENAME
    done
    GEN_FILES=`find $PROJECT_DIR/instGen/ -name "GEN-*"`
    for GFILE in $GEN_FILES
    do
        DIRNAME=`dirname $GFILE`
	NEW_FILENAME=`basename $GFILE | cut -c9-`
	mv $GFILE $DIRNAME/$NEW_FILENAME
    done
    # Delete the org files in the /src and instSrc/ dirs to clean up
    rm -rf $PROJECT_DIR/src/org
    rm -rf $PROJECT_DIR/instSrc/org
    cd $PROJECT_DIR
    find . -name "GEN-*" -exec rm {} \;
    # Copy over the excluded source files that fail when instrumented
    cp $SRC_ROOT_DIR/com/google/javascript/jscomp/InstrumentFunctions.java $INST_SRC_ROOT_DIR/com/google/javascript/jscomp/InstrumentFunctions.java
    cp $PROJECT_DIR/lib/rhino/toolsrc/org/mozilla/javascript/tools/debugger/Dim.java $PROJECT_DIR/instLib/rhino/toolsrc/org/mozilla/javascript/tools/debugger/Dim.java
    # Build the instrumented code
    ant -f $PROJECT_DIR/build.xml cc-compile
fi
