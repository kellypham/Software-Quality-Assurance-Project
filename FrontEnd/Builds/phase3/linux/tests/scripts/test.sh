#! /bin/bash

EXECUTABLE_FILE="../../Auctionr"

INPUT_FILE_NAME="input.txt"
EXPECTED_OUTPUT_FILE_NAME="output.txt"
OPTIONS_FILE_NAME="options.txt"

optionsFile="./files/options.txt"

testDirectory="../${1}/${2}"

inputFilePath="${testDirectory}/${INPUT_FILE_NAME}"
expectedOutputFilePath="${testDirectory}/${EXPECTED_OUTPUT_FILE_NAME}"

#Custom options for a test case, stored in the test's directory
testCaseOptionsFile="${testDirectory}/${OPTIONS_FILE_NAME}"

# Validate params exist 
if [ -z "$1" ]
then
	echo Error: Param 1 cannot be empty
	exit -1
fi

if [ -z "$2" ]
then
	echo Error: Param 2 cannot be empty
	exit -1
fi

if [ ! -z $3 ]
then
	optionsFile=$3;
elif [ -f "$testCaseOptionsFile" ]
then
	optionsFile=$testCaseOptionsFile
fi

# Validate files and directories exist
if [ ! -d "$testDirectory" ]
then
	echo "Error: Test directory ${testDirectory} does not exist"
	exit -2
fi

if [ ! -f "$inputFilePath" ]
then
	echo "Error: Test directory ${testDirectory} does not contain file ${inputFilePath}"
	exit -2
fi

if [ ! -f "$expectedOutputFilePath" ]
then
	echo "Error: Test directory ${testDirectory} does not exist contain file ${expectedOutputFilePath}"
	exit -2
fi

if [ ! -f "$EXECUTABLE_FILE" ]
then
	echo "Error: Executable ${EXECUTABLE_FILE} does not exist"
	exit -2
fi

if [ ! -f "$optionsFile" ]
then
	echo "Error: Options file ${optionsFile} does not exist"
	exit -2
fi

#Clear the log file from previous runs
if [ -f "$LOG_FILE" ]
then
	rm ${LOG_FILE}
fi

#Execute program and read output

#Preserve formatting in file/program output
IFS=

programOutput=`timeout 3 $EXECUTABLE_FILE $optionsFile < $inputFilePath`

if [ $? == 124 ]
then
	echo "Timed out"
	exit 1
fi

#diff contents of expected output file with program output
outputDifference=`echo \"$programOutput\" | diff ${expectedOutputFilePath} -`

if [ "$outputDifference" != "" ]
then
	echo "Difference between expected and given output:"
	echo $outputDifference
	exit 1
else
	echo "Passed"
	exit 0
fi