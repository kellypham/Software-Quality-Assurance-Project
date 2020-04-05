#pragma once

#include <string>

/** Enum for user types */
enum UserType
{
	ADMIN,
	FULL_STANDARD,
	BUY_STANDARD,
	SELL_STANDARD
};

/**
 * Class storing user data
 */
class User
{
	public:
		/**
		 * Accessor for user's name.
		 * @return User's name
		 */
		std::string GetName();
		/**
		 * Mutator for user's name
		 * @param name New name value
		*/
		void SetName(std::string name);

		/**
		 * Accessor for user's type.
		 * @return User's type from UserTypes enum
		 */
		UserType GetType();
		/**
		 * Mutator for user's type
		 * @param name New user type
		*/
		void SetType(UserType type);

		/**
		 * Accessor for user's credit.
		 * @return User's credit
		 */
		int GetCredit();
		/**
		 * Mutator for user's credit
		 * @param name New credit value
		*/
		void SetCredit(int credit);

		/**
		 * Accessor for user's admin type.
		 * @return True if User is an Admin
		 */
		bool IsAdmin();

	private:
		/** User's name */
		std::string name;
		/** User's type */
		UserType type;
		/** Amount of credit the user currently has */
		int credit;
};