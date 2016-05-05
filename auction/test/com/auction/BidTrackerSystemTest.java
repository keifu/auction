package com.auction;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.auction.Bid;
import com.auction.BidTracker;
import com.auction.BidTrackerSystem;
import com.auction.Item;
import com.auction.User;

public class BidTrackerSystemTest {

	private BidTracker bidTracker;

	@Before
	public void setup() {

		bidTracker = new BidTrackerSystem();

		User john = new User("John");
		User peter = new User("Peter");
		User david = new User("David");
		User kenny = new User("Kenny");

		Item itemOne = new Item("Item 1");
		Item itemTwo = new Item("Item 2");
		Item itemThree = new Item("Item 3");
		Item itemFour = new Item("Item 4");

		// bids for John
		Bid bid = new Bid(Double.valueOf(10.5), itemOne, john);
		bidTracker.recordBid(bid);
		bid = new Bid(Double.valueOf(55.5), itemTwo, john);
		bidTracker.recordBid(bid);
		bid = new Bid(Double.valueOf(70.5), itemThree, john);
		bidTracker.recordBid(bid);

		// bids for Peter
		bid = new Bid(Double.valueOf(5.5), itemOne, peter);
		bidTracker.recordBid(bid);
		bid = new Bid(Double.valueOf(60.5), itemTwo, peter);
		bidTracker.recordBid(bid);

		// bids for David
		bid = new Bid(Double.valueOf(21.5), itemOne, david);
		bidTracker.recordBid(bid);
		
		//bids for Kenny
		//bids for same item
		bid = new Bid(Double.valueOf(21.5), itemFour, kenny);
		bidTracker.recordBid(bid);
		bid = new Bid(Double.valueOf(23.5), itemFour, kenny);
		bidTracker.recordBid(bid);
		
	}
	
	@After
	public void tearDown(){
		bidTracker = null;
	}

	@Test
	public void testGetAllBidsForUser() {
		// bids for John
		User john = new User("John");
		List<Bid> bids = bidTracker.getAllBidsForUser(john);
		
		assertEquals(3, bids.size());
		assertEquals(new Item("Item 1"), bids.get(0).getItem());
		assertEquals(Double.valueOf(10.5), bids.get(0).getBidPrice());
		
		assertEquals(new Item("Item 2"), bids.get(1).getItem());
		assertEquals(Double.valueOf(55.5), bids.get(1).getBidPrice());
		
		assertEquals(new Item("Item 3"), bids.get(2).getItem());
		assertEquals(Double.valueOf(70.5), bids.get(2).getBidPrice());
	}

	@Test
	public void testGetAllBidsForUser_2() {
		// bids for Peter
		User peter = new User("Peter");
		List<Bid> bids = bidTracker.getAllBidsForUser(peter);
		
		assertEquals(2, bids.size());
		assertEquals(new Item("Item 1"), bids.get(0).getItem());
		assertEquals(Double.valueOf(5.5), bids.get(0).getBidPrice());
		
		assertEquals(new Item("Item 2"), bids.get(1).getItem());
		assertEquals(Double.valueOf(60.5), bids.get(1).getBidPrice());
	}

	@Test
	public void testGetAllBidsForUser_3() {
		// bids for David
		User david = new User("David");
		List<Bid> bids = bidTracker.getAllBidsForUser(david);
		
		assertEquals(1, bids.size());
		assertEquals(new Item("Item 1"), bids.get(0).getItem());
		assertEquals(Double.valueOf(21.5), bids.get(0).getBidPrice());
	}

	@Test
	public void testGetAllBidsForUser_No_Bids() {
	
		User randomUser = new User("RandomUser");
		List<Bid> bids = bidTracker.getAllBidsForUser(randomUser);
		
		assertEquals(0, bids.size());
	}

	@Test(expected = NullPointerException.class)
	public void testGetAllBidsForUser_Null_Check() {
		bidTracker.getAllBidsForUser(null);
	}

	@Test
	public void testGetAllBidsForItems() {
		
		User john = new User("John");
		User peter = new User("Peter");
		User david = new User("David");
		
		// bids for item one
		List<Bid> bids = bidTracker.getAllBidsForItem(new Item("Item 1"));
		
		assertEquals(3, bids.size());
		assertEquals(john, bids.get(0).getUser());
		assertEquals(Double.valueOf(10.5), bids.get(0).getBidPrice());
		
		assertEquals(peter, bids.get(1).getUser());
		assertEquals(Double.valueOf(5.5), bids.get(1).getBidPrice());
		
		assertEquals(david, bids.get(2).getUser());
		assertEquals(Double.valueOf(21.5), bids.get(2).getBidPrice());
	}

	@Test
	public void testGetAllBidsForItems_2() {
	
		User john = new User("John");
		User peter = new User("Peter");
		List<Bid> bids = bidTracker.getAllBidsForItem(new Item("Item 2"));
		
		// bids for item two
		assertEquals(2, bids.size());
		assertEquals(john, bids.get(0).getUser());
		assertEquals(Double.valueOf(55.5), bids.get(0).getBidPrice());
		
		assertEquals(peter, bids.get(1).getUser());
		assertEquals(Double.valueOf(60.5), bids.get(1).getBidPrice());
	}

	@Test
	public void testGetAllBidsForItems_3() {
		User john = new User("John");
		
		// bids for item three
		List<Bid> bids = bidTracker.getAllBidsForItem(new Item("Item 3"));
		
		assertEquals(1, bids.size());
		assertEquals(john, bids.get(0).getUser());
		assertEquals(Double.valueOf(70.5), bids.get(0).getBidPrice());
	}

	@Test
	public void testGetAllBidsForItems_No_Bids() {
		List<Bid> bids = bidTracker.getAllBidsForItem(new Item("RandomItem"));
		
		//no bids
		assertEquals(0, bids.size());
	}

	@Test(expected = NullPointerException.class)
	public void testGetAllBidsForItems_Null_Item() {
		bidTracker.getAllBidsForItem(null);
	}

	@Test
	public void testGetWinningBid() {
		//winning bid for item one
		Bid winningBid = bidTracker.getWinningBid(new Item("Item 1"));
		
		assertEquals(Double.valueOf(21.5), winningBid.getBidPrice());
		assertEquals("David", winningBid.getUser().getUserName());
	}

	@Test
	public void testGetWinningBid_2() {
		// winning bid for item two
		Bid winningBid = bidTracker.getWinningBid(new Item("Item 2"));
		
		assertEquals(Double.valueOf(60.5), winningBid.getBidPrice());
		assertEquals("Peter", winningBid.getUser().getUserName());
	}

	@Test
	public void testGetWinningBid_3() {
		// winning bid for item three
		Bid winningBid = bidTracker.getWinningBid(new Item("Item 3"));
		
		assertEquals(Double.valueOf(70.5), winningBid.getBidPrice());
		assertEquals("John", winningBid.getUser().getUserName());
	}

	@Test
	public void testGetWinningBid_No_Bids() {
		Bid winningBid = bidTracker.getWinningBid(new Item("RandomItem"));
		assertNull(winningBid);
	}

	@Test(expected = NullPointerException.class)
	public void testGetWinningBid_Null_Check() {
		bidTracker.getWinningBid(null);
	}

	@Test
	public void testGetAllItemsForUser() {
		// Items for John
		User john = new User("John");		
		List<Item> items = bidTracker.getAllItemsForUser(john);
		
		assertEquals(3, items.size());
		assertEquals(new Item("Item 1"), items.get(0));
		assertEquals(new Item("Item 2"), items.get(1));
		assertEquals(new Item("Item 3"), items.get(2));
	}

	@Test
	public void testGetAllItemsForUser_2() {
		// Items for Peter
		User peter = new User("Peter");
		List<Item> items = bidTracker.getAllItemsForUser(peter);
		
		assertEquals(2, items.size());
		assertEquals(new Item("Item 1"), items.get(0));
		assertEquals(new Item("Item 2"), items.get(1));
	}

	@Test
	public void testGetAllItemsForUser_3() {
		// Items for David
		User david = new User("David");
		List<Item> items = bidTracker.getAllItemsForUser(david);
		
		assertEquals(1, items.size());
		assertEquals(new Item("Item 1"), items.get(0));
	}

	@Test
	public void testGetAllItemsForUser_No_Items() {
		User keith = new User("RandomUser");
		List<Item> items = bidTracker.getAllItemsForUser(keith);
		
		assertEquals(0, items.size());
	}
	
	@Test
	public void testGetAllItemsForUser_Duplicate() {
		
		//Kenny made 2 bids for the same item
		//so should only return 1 item
		User kenny = new User("Kenny");
		List<Item> items = bidTracker.getAllItemsForUser(kenny);
		
		assertEquals(1, items.size());
		assertEquals(new Item("Item 4"), items.get(0));
	}

	@Test(expected = NullPointerException.class)
	public void testAllItemsForUser_Null_Check() {
		bidTracker.getAllItemsForUser(null);
	}

}
