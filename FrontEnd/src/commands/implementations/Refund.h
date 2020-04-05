#pragma once

#include <string>

#include "../Command.h"
#include "../../models/User.h"
#include "../../storage/UserStorage.h"
#include "../../storage/Exceptions.h"

/** Implementation of Refund Class for making refunds */
class Refund : public Command
{
    public:
        /**
         * Initializes the user storage to validate buyer and seller
         * @param user storage to search users
         */
        Refund(UserStorage* userStorage);

        /**
         * Reads buyer name, seller name, and the amount of the refund credit
         */
        virtual void GetInputs() override;

        /**
         * Generate log contests that are to be written to log file.
         */
        virtual std::string GetLogContents() override;

        virtual void Execute() override;

    private:
        /** User storage access */
        UserStorage* userStorage;

        /** Buyer's name who gets refunded */
        std::string buyerName;

        /** Seller's name that issues the refund */
        std::string sellerName;

        /** The amount of the refund credit */
        int refundCredit;
};
