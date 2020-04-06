RUNS=5

for ((i=0; i<RUNS; i++)); do
	./daily.sh
done

#This is weekly item logs
cp items.txt weeklyItems.txt
mv weeklyItems.txt WeeklyItemsLog