#include "DailyLogFile.h"

#include <string>
#include <fstream>

DailyLogFile::DailyLogFile(std::string filename)
{
	this->filename = filename;
}
		
void DailyLogFile::AddEntry(std::string entry)
{
	this->entries.push_back(entry);
}

void DailyLogFile::WriteChanges()
{

	std::ofstream file;
	file.open(this->filename, std::fstream::out | std::fstream::app);

	for (int i = 0; i < this->entries.size(); i++)
	{
		std::string logEntry = this->entries[i];
		file << logEntry << '\n';
		// file.write((logEntry + "\n").c_str(), logEntry.size() + 1);
	}

	file.close();
}

void DailyLogFile::ClearBuffer()
{
	this->entries.clear();
}