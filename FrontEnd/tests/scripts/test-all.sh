SUCCESS_FILE="../logs/succeeded.txt"
FAILURES_FILE="../logs/failed.txt"

DETAILS_FOLDER="../logs/differences"

if [ -f "$SUCCESS_FILE" ]
then
	rm $SUCCESS_FILE
fi

if [ -f "$FAILURES_FILE" ]
then
	rm $FAILURES_FILE
fi

if [ -f "./files/log.txt" ]
then
	rm ./files/log.txt
fi

rm ${DETAILS_FOLDER}/*.txt

echo > $SUCCESS_FILE
echo > $FAILURES_FILE

IFS=

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
		
		result=`./test.sh $testCommand $testCase`

		if [ $? == 0 ]
		then
			echo "$testCommand $testCase Passed"
			echo "$testCommand $testCase" >> $SUCCESS_FILE
		else
			echo "$testCommand $testCase Failed"
			echo "$testCommand $testCase" >> $FAILURES_FILE
			echo $result > $DETAILS_FOLDER/${testCommand}_${testCase}.txt
		fi
	done
done