#pragma once
#include <vector>

#include "DailyLog.h"

/**
 * Implementation of DailyLog that outputs data to a file
 */
class DailyLogFile : public DailyLog
{
	public:
		/**
		 * Constructor for DailyLogFile. Initializes the filename to write to
		 * @param filename Path to the daily log file. Will be created if it doens't exist
		 */
		DailyLogFile(std::string filename);

		/**
		 * Buffer the entry parameter to be written as a line
		 *  to the daily log file.
		 * @param entry Line to add to the log file
		 */
		virtual void AddEntry(std::string entry) override;

		/**
		 * Write all lines buffered by AddEntry to the log file
		 */
		virtual void WriteChanges() override;

		/**
		 * Clear all entries to be written to the daily log file
		 */
		virtual void ClearBuffer() override;
	
	private:
		/** Buffer for log entries */
		std::vector<std::string> entries;
		
		/** Path to the daily log file */
		std::string filename;
};