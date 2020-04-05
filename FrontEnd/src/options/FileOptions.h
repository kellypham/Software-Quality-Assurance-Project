#pragma once
#include <string>
#include <map>

#include "Options.h"

/** Key for the User Accounts File setting */
#define FILECONFIG_KEY_ACCOUNTS_FILE "AccountsFile"

/** Key for the Items File setting */
#define FILECONFIG_KEY_ITEMS_FILE "ItemsFile"

/** Key for the Daily Log File setting */
#define FILECONFIG_KEY_LOG_FILE "DailyLogFile"

/**
 * An implementation of Options that reads options from a file
 * The file is to be formatted as a key-value pair, where the
 * key and value is separated by a space, and each entry by a newline.
 * 
 * Spaces in key and value names are not supported.
 */
class FileOptions : public Options
{
	public:
		/** Default constructor */
		FileOptions();

		/**
		 * Makes sure the files all have the correct permissions.
		 * @throw string if one of the files is missing a requisite permission
		 */
		virtual void Validate() override;

		/**
		 * Load options from a file. Outputs an error message if all
		 *  required options are not present in the file.
		 * 
		 * @param filename path to the options file
		 */
		void LoadOptions(std::string filename);

		/**
		 * Get the path to the User Accounts file
		 * @return Path to the User Accounts file
		 */
		virtual std::string AccountsFile() override;

		/**
		 * Get the path to the Items file
		 * @return Path to the Items file
		 */
		virtual std::string ItemsFile() override;

		/**
		 * Get the path to the Daily Log file
		 * @return Path to the Daily Log file
		 */
		virtual std::string DailyLogFile() override;
	
	protected:
		/**
		 * Function used to check whether a key has been set.
		 * @param key Key to verify that it has been set
		*/
		bool IsKeyDefined(std::string key);

		/**
		 * Mapping of key-value pairs storing the configuration
		*/
		std::map<std::string, std::string> configuration;
};