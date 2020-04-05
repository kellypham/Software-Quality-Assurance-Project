#pragma once

#include <string>

#include "../Command.h"
#include "../../models/Item.h"
#include "../../storage/ItemStorage.h"
#include "../../storage/UserStorage.h"
#include "../../storage/Exceptions.h"
#include "../../ApplicationState.h"

/** Implementation of Bid Class for bidding items */
class Bid : public Command
{

    public:
        /**
         * Initializes application state to access user information, user
         * storage and item storage to validate
         * @param application State of the application
         * @param user storage to search users
         * @param item storage to search items
         */
        Bid(ApplicationState* application, UserStorage* userStorage, ItemStorage* itemStorage);

        /**
         * Reads item name, seller name, and new bid amount from the user
         */
        virtual void GetInputs() override;

        /**
         * Generate log contests that are to be written to log file.
         */
        virtual std::string GetLogContents() override;

        /**
         * Function to check if a user has sufficient permissions to run this command.
         * @param user User to validate permissions for
         * @return false if the seller is SELL_STANDARD
         * @return true for all other conditions
         */
        bool IsPermitted(User* user) override;
        
        virtual void Execute() override;

    private:
        /** Application state */
        ApplicationState* application;

        /** User storage access */
        UserStorage* userStorage;

        /** Item storage access */
        ItemStorage* itemStorage;

        /** Item name to bid*/
        std::string itemName;

        /** Seller's name that auctions the item */
        std::string sellerName;

        /** The amount of the bid that is bidded by the buyer */
        int newBid;

};
