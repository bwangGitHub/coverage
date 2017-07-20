#!/bin/bash

# echo "Running linear regression on the results"
# cd $BASE_DIR/scripts/analysis
# cp "$RESULT_DIR/randomSuiteResults.csv" "$RESULT_DIR/temp.csv"
# sed -i 1d "$RESULT_DIR/temp.csv"
# sort -R "$RESULT_DIR/temp.csv" > "$RESULT_DIR/randomSuiteResultsPermuted.csv"
# rm "$RESULT_DIR/temp.csv"
# octave linear_regression.m "$RESULT_DIR/randomSuiteResultsPermuted.csv" 1>$LOG_DIR/linear_regression_stdout.log 2>$LOG_DIR/linear_regression_stderr.log