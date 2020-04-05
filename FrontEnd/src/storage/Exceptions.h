#pragma once

#include <exception>
#include <string>

/**
 * Enum describing types of errors in the storage module
 */
enum ExceptionType
{
	/** Signifies that no matching resource was found */
	NOT_FOUND
};

/**
 * General exception class for the storage module
 */
class Exception : public virtual std::exception
{
	public:
		/**
		 * Constructor for the exception, initializes the exception type and message
		 * @param type The type of error
		 * @param message Message for further error details
		 */
		Exception(ExceptionType type, std::string message);

		virtual ~Exception() throw();

		/**
		 * An error message describing the exception
		 * @return A string describing the error that occurred
		 */
		virtual const char* what() const throw ();

		/**
		 * Returns an error number that directly corresponds to a value
		 *  in the ExceptionType enum
		 * @return An error number corresponding to an ExceptionType
		 */
		virtual int getErrorNumber() const throw();

	protected:
		/** string returned by what() */
		std::string message;

		/** ExceptionType converted to integer and returned in getErrorNumber */
		ExceptionType type;
};

/**
 * Exception returned form UserStorage classes to signify an error.
 */
class UserStorageException : public Exception
{
	public:
		/**
		 * Constructor for the exception, initializes the exception type and message
		 * @param type The type of error
		 * @param message Message for further error details
		 */
		UserStorageException(ExceptionType type, std::string message);
};

/**
 * Exception returned form ItemStorage classes to signify an error.
 */
class ItemStorageException : public Exception
{
	public:
		/**
		 * Constructor for the exception, initializes the exception type and message
		 * @param type The type of error
		 * @param message Message for further error details
		 */
		ItemStorageException(ExceptionType type, std::string message);
};
