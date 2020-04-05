#pragma once

#include <string>

/**
 * Class for Item information
 */
class Item
{
	public:
		/**
		 * Accessor for the Item's name
		 * @return Item's name
		 */
		std::string GetName();
		/**
		 * Mutator for Item's name
		 * @param name New name of the item
		 */
		void SetName(std::string name);

		/**
		 * Accessor for the name of this item's seller
		 * @return Item's seller name
		 */
		std::string GetSellerName();
		/**
		 * Mutator for Item's seller's name
		 * @param name New name of the seller of the item
		 */
		void SetSellerName(std::string name);

		/**
		 * Accessor for the name of the highest bidder on this item
		 * @return Item's highest bidder's name
		 */
		std::string GetHighestBidderName();
		/**
		 * Mutator for name of the highest bidder on the Item
		 * @param name Name of the new highest bidder on the Item
		 */
		void SetHighestBidderName(std::string name);

		/**
		 * Accessor for the amount of days remaining to bid on this item
		 * @return Days remaining to bid on this item
		 */
		int GetDaysRemaining();
		/**
		 * Mutator for Item's days remaining on this auction
		 * @param name New amount of days remaining
		 */
		void SetDaysRemaining(int days);

		/**
		 * Accessor for the highest bid on this item
		 * @return Item's highest bid
		 */
		int GetHighestBid();
		/**
		 * Mutator for the highest bid on the Item
		 * @param name New highest bid
		 */
		void SetHighestBid(int bid);

	private:
		/** Name of the item */
		std::string name;
		/** Name of the item's seller */
		std::string sellerName;
		/** Name of the item's highest bidder */
		std::string highestBidderName;
		/** Number of days remaining to bid on this item */
		int daysRemaining;
		/** Highest bid on this item */
		int highestBid;
};