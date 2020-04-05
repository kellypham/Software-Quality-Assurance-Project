#include "Item.h"

#include <string>

std::string Item::GetName()
{
	return this->name;
}
void Item::SetName(std::string name)
{
	this->name = name;
}

std::string Item::GetSellerName()
{
	return this->sellerName;
}
void Item::SetSellerName(std::string name)
{
	this->sellerName = name;
}

std::string Item::GetHighestBidderName()
{
	return this->highestBidderName;
}
void Item::SetHighestBidderName(std::string name)
{
	this->highestBidderName = name;
}

int Item::GetDaysRemaining()
{
	return this->daysRemaining;
}
void Item::SetDaysRemaining(int days)
{
	this->daysRemaining = days;
}

int Item::GetHighestBid()
{
	return this->highestBid;
}
void Item::SetHighestBid(int bid)
{
	this->highestBid = bid;
}