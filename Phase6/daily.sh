
FRONT_EXECUTABLE="./Auctionr"
BACK_EXECUTABLE="./Auctionr.jar"

MERGED_LOG_FILE="./log.txt"

casefiles=("./Cases/addcredit.txt" "./Cases/refund.txt")

for casefile in "${casefiles[@]}"
do
	LOG_FILE=`date +%T:%N_%d_%m_%Y`
	OPTIONS_FILE="${LOG_FILE}.opts"
	LOG_FILE="${LOG_FILE}.dailylog"

	cp "options.txt" "${OPTIONS_FILE}"
	echo "${LOG_FILE}" >> "${OPTIONS_FILE}"

	$FRONT_EXECUTABLE $OPTIONS_FILE < $casefile
	rm $OPTIONS_FILE
done

for logfile in ./*.dailylog
do
	cat "${logfile}" >> $MERGED_LOG_FILE
	rm $logfile
done

java -jar ./BackEnd.jar
rm items.txt users.txt log.txt
mv newitems.txt items.txt
mv newusers.txt users.txt