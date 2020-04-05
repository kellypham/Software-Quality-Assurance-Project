#pragma once
#include "Options.h"

/** Name of the environment variable mapping to the User Accounts file */
#define ENV_ACCOUNTS_FILE "CSCI3060_ACCOUNTS_FILE"

/** Name of the environment variable mapping to the Items file */
#define ENV_ITEMS_FILE "CSCI3060_ITEMS_FILE"

/** Name of the environment variable mapping to the Daily Log file */
#define ENV_LOG_FILE "CSCI3060_LOG_FILE"

/**
 * Options implementation that reads values from the environment variables
 */
class EnvOptions : public Options
{
	public:
		/**
		 * Makes sure the env variables for the files are set
		 * @throw string if one of the required variables aren't set
		 */
		virtual void Validate() override;

		/**
		 * Get the path to the accounts file
		 * @return A path to the user accounts file
		 */
		virtual std::string AccountsFile() override;


		/**
		 * Get the path to the Items file
		 * @return A path to the Items file
		 */
		virtual std::string ItemsFile() override;

		/**
		 * Get the path to the Daily Log file
		 * @return A path to the Daily Log file
		 */
		virtual std::string DailyLogFile() override;
};