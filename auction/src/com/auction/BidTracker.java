package com.auction;
import java.util.List;

/**
 * An interface for a bid tracking system which records and retrieves user's bid
 * 
 * @author Keith
 *
 */
public interface BidTracker {
	
	/**
	 * Records a user's bid
	 * @param user 
	 * @param bid 
	 */
	public void recordBid(Bid bid);
		
	/**
	 * Get all the bids for a user
	 * 
	 * @param item item
	 * @return all bids for user or empty list if user has no bids
	 */
	public List<Bid> getAllBidsForUser(User user);
	
	/**
	 * Get all the bids for an item
	 * 
	 * @param item item
	 * @return all bids for the item, or empty list if no bids
	 */
	public List<Bid> getAllBidsForItem(Item item);
	
	/**
	 * Get the current winning bid for an item
	 * 
	 * @param item
	 * @return winning bid for the item, or null if no bids
	 */
	public Bid getWinningBid(Item item);
	
	/**
	 * Gets all items that a user has bid for
	 * 
	 * @param user user
	 * @return all items which user has bid or empty list if no items
	 */
	public List<Item> getAllItemsForUser(User user);
}
