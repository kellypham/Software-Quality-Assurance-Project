package AuctionrBack.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * File implementation of DailyLog class
 */
public class DailyLogFile extends DailyLog
{
	private String filename;
	private ArrayList<LogEntry> entries;
	int nextIndex = 0;

	/**
	 * Initializes the DailyLogFile to read the log entries from a file
	 */
	public DailyLogFile(String file)
	{
		filename = file;
		entries = new ArrayList<>();
	}

	/**
	 * Loads all the Log entries form a file and parses them into LogEntry objects to return
	 * @throws IOException When there's an issue reading the file
	 */
	@Override
	public void Initialize() throws Exception
	{
		FileReader file = new FileReader(filename);
		BufferedReader reader = new BufferedReader(file);

		while(reader.ready())
		{
			String line = reader.readLine();
			
			if (line.isEmpty()) continue;
			
			String[] lineParts = line.split("\\s+");
			String[] args = new String[lineParts.length-1];

			if (args.length != 0)
			{
				System.arraycopy(lineParts, 1, args, 0, args.length);
			}
			
			LogEntry entry = new LogEntry(lineParts[0], args);

			entries.add(entry);
		}

		reader.close();
	}

	/**
	 * Returns the next LogEntry object in the list
	 * @return The next LogEntry
	 */
	@Override
	public LogEntry NextItem()
	{
		LogEntry result = entries.get(nextIndex);
		nextIndex++;

		return result;
	}

	/**
	 * Returns true if the log is empty, false otherwise
	 * @return Whether the daily log has entries to return
	 */
	@Override
	public boolean IsEmpty()
	{
		return entries.size() == nextIndex;
	}
	
}