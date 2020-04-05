LOG_FILE_NAME="log.txt"

SUCCESS_FILE="../logs/log_succeeded.txt"
FAILURES_FILE="../logs/log_failed.txt"

if [ -f "$SUCCESS_FILE" ]
then
	rm $SUCCESS_FILE
fi

if [ -f "$FAILURES_FILE" ]
then
	rm $FAILURES_FILE
fi

echo > $SUCCESS_FILE
echo > $FAILURES_FILE

for testFolder in ../*/
do
	if [ "$testFolder" == "../scripts/" ] || [ "$testFolder" == "../logs/" ]
	then
		continue
	fi

	for testCaseFolder in $testFolder/*/
	do
		# Remove path from testFolder and testCase's path
		#  since test.sh doesn't take full paths as input
		testCommand=`basename $testFolder`
		testCase=`basename $testCaseFolder`

		logPath="../${testCommand}/${testCase}/${LOG_FILE_NAME}"
		if [ -f "$logPath" ]
		then
			result=`./check-log.sh $testCommand $testCase ./files/log.txt`

			if [ $? == 0 ]
			then
				echo "$testCommand $testCase Passed"
				echo "$testCommand $testCase" >> $SUCCESS_FILE
			else
				echo "$testCommand $testCase Failed"
				echo "$testCommand $testCase" >> $FAILURES_FILE
			fi
		fi
	done
done