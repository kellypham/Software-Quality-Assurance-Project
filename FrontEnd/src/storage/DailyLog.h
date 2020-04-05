#pragma once
#include <string>

/**
 * Abstract Class definition for actions effecting the daily log file
 */
class DailyLog
{
	public:
		/**
		 * Buffer the entry parameter to be written as a line
		 *  to the daily log file.
		 * @param entry Line to add to the log file
		 */
		virtual void AddEntry(std::string entry) = 0;

		/**
		 * Write all lines buffered by AddEntry to the log file
		 */
		virtual void WriteChanges() = 0;

		/**
		 * Clear all entries to be written to the daily log file
		 */
		virtual void ClearBuffer() = 0;
};