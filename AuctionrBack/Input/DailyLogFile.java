package AuctionrBack.Input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Queue;

public class DailyLogFile extends DailyLog
{
	private String filename;
	private Queue<LogEntry> entries;

	public DailyLogFile(String file)
	{
		filename = file;
	}

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

	@Override
	public LogEntry NextItem()
	{
		return entries.remove();
	}

	@Override
	public boolean IsEmpty()
	{
		return entries.size() == 0;
	}
	
}