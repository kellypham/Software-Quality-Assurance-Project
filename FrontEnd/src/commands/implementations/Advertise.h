#include <string>

#include "../Command.h"
#include "../../models/User.h"
#include "../../models/Item.h"
#include "../../storage/ItemStorage.h"
#include "../../storage/UserStorage.h"
#include "../../ApplicationState.h"

/** Implementation of Advertise Class for advertising items */
class Advertise : public Command
{
    public:
        /**
         * Initializes application state to access user information, user
         * storage and item storage to validate
         * @param application State of the application
         * @param user storage to search users
         * @param item storage to search items
         */
        Advertise(ApplicationState* application, UserStorage* userStorage, ItemStorage* itemStorage);

        /**
         * Reads item name, number of days of auction, and minimum bid from the user
         */
        virtual void GetInputs() override;

        /**
         * Generate log contests that are to be written to log file.
         */
        virtual std::string GetLogContents() override;

        /**
         * Function to check if a user has sufficient permissions to run this command.
         * @param user User to validate permissions for
         * @return false if the seller is BUY_STANDARD
         * @return true for all other conditions
         */
        bool IsPermitted(User* user) override;

    private:
        /** Application state */
        ApplicationState* application;

        /** User storage access */
        UserStorage* userStorage;

        /** Item storage access */
        ItemStorage* itemStorage;

        /** Item name to be advertised*/
        std::string itemName;

        /** Number of days to auction the item */
        int daysToAuction;

        /** Minimum bid amount that is set by the seller */
        int minimumBid;
};
