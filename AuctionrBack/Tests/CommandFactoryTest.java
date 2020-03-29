package AuctionrBack.Tests;

import org.junit.Test;

import AuctionrBack.Commands.Command;
import AuctionrBack.Commands.CommandFactory;
import AuctionrBack.Commands.Implementation.AddCredit;
import AuctionrBack.Commands.Implementation.Advertise;
import AuctionrBack.Commands.Implementation.Bid;
import AuctionrBack.Commands.Implementation.CreateCommand;
import AuctionrBack.Commands.Implementation.DeleteCommand;
import AuctionrBack.Commands.Implementation.Refund;
import AuctionrBack.Input.LogEntry;

import static org.junit.Assert.*;

public class CommandFactoryTest
{
	@Test
	public void ReturnAddCredit()
	{
		RunTest("06", AddCredit.class);
	}

	@Test
	public void ReturnAdvertise()
	{
		RunTest("03", Advertise.class);
	}

	@Test
	public void ReturnBid()
	{
		RunTest("04", Bid.class);
	}

	@Test
	public void ReturnCreate()
	{
		RunTest("01", CreateCommand.class);
	}

	@Test
	public void ReturnDelete()
	{
		RunTest("02", DeleteCommand.class);
	}

	@Test
	public void ReturnRefund()
	{
		RunTest("05", Refund.class);
	}

	@Test
	public void ReturnNull()
	{
		LogEntry entry = new LogEntry("NonExistentCode", new String[] {});
		CommandFactory factory = new CommandFactory(null, null);

		Command cmd = factory.Create(entry);

		assertEquals(null, cmd);
	}
	
	public void RunTest(String commandCode, Class expectedType)
	{
		LogEntry entry = new LogEntry(commandCode, new String[] {"0", "0", "0", "0"});
		CommandFactory factory = new CommandFactory(null, null);

		Command cmd = factory.Create(entry);

		assertNotNull("Factory returned unexpected null for code " + commandCode, cmd);
		assertEquals(
			"Command code " + commandCode + " did not return type " + expectedType.getName(),
			expectedType, cmd.getClass()
		);
	}

}