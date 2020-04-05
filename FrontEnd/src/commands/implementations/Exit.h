#pragma once
#include "../Command.h"

#include "../../ApplicationState.h"

class Exit : public Command
{
	public:
		Exit(ApplicationState* application);
		void Execute() override;

		bool IsLoginRequired() override;
	private:
		ApplicationState* application;
};