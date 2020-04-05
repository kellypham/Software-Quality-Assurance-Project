
EXPECTED_LOG_FILE="log.txt"

# Validate params exist 
if [ -z "$1" ]
then
	echo Error: Param 1 cannot be empty
	exit 1
fi

if [ -z "$2" ]
then
	echo Error: Param 2 cannot be empty
	exit 1
fi

if [ -z "$3" ]
then
	echo Error: Must specify a log file for Parameter 3
	exit 1
fi

#preserve spacing and newlines
IFS=

expectedLogFilePath="../${1}/${2}/${EXPECTED_LOG_FILE}"
logFilePath=$3

if [ ! -f "$expectedLogFilePath" ]
then
	echo $expectedlogFilePath does not contain $EXPECTED_LOG_FILE
	exit 0
fi

if [ ! -f "$logFilePath" ]
then
	echo Error: File $logFilePath not found
	exit 1
fi

expectedLogContents=`cat $expectedLogFilePath`
logContents=`cat $logFilePath`
matchingLogEntry=`echo $logContents | grep $expectedLogContents`

if [ -z "$matchingLogEntry" ]
then
	echo Log entry \"$expectedLogContents\" not found in \"$logFilePath\"
	exit 1
else
	exit 0
fi