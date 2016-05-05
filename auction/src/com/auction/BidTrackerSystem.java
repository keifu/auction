package com.auction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 
 * BidTracker implementation which records\retrieves users' bids in a thread-safe manner
 * 
 * @author Keith
 *
 */
public class BidTrackerSystem implements BidTracker {

	/**
	 * Keeps track of all bids for each user.
	 */
	private Map<User, List<Bid>> userBidMap;

	/**
	 * Keep tract of all bids for each item
	 */
	private Map<Item, List<Bid>> itemBidMap;

	public BidTrackerSystem() {
		userBidMap = new ConcurrentHashMap<>();
		itemBidMap = new ConcurrentHashMap<>();
	}

	@Override
	public void recordBid(Bid bid) {

		Objects.requireNonNull(bid);

		User user = bid.getUser();

		// put in user -> bids map
		List<Bid> userBids = userBidMap.putIfAbsent(user,
				Collections.synchronizedList(new ArrayList<Bid>(1)));

		if (userBids == null) {
			userBids = userBidMap.get(user);
		}

		userBids.add(bid);

		// put in item -> bids map
		List<Bid> itemBids = itemBidMap.putIfAbsent(bid.getItem(),
				Collections.synchronizedList(new ArrayList<Bid>(1)));

		if (itemBids == null) {
			itemBids = itemBidMap.get(bid.getItem());
		}

		itemBids.add(bid);
	}

	@Override	
	public List<Bid> getAllBidsForUser(User user) {

		Objects.requireNonNull(user);

		List<Bid> bids = userBidMap.get(user);

		if (bids == null)
			return Collections.<Bid> emptyList();

		//return a defensive copy
		return new ArrayList<>(bids);
	}

	@Override
	public List<Bid> getAllBidsForItem(Item item) {

		Objects.requireNonNull(item);

		List<Bid> bids = itemBidMap.get(item);

		if (bids == null)
			return Collections.<Bid> emptyList();
		
		//return a defensive copy
		return new ArrayList<>(bids);

	}
	
	@Override
	public Bid getWinningBid(Item item) {

		Objects.requireNonNull(item);

		List<Bid> bids = getAllBidsForItem(item);

		Bid winningBid = null;

		for (Bid bid : bids) {
			if (winningBid == null
					|| winningBid.getBidPrice().compareTo(bid.getBidPrice()) < 0) {
				winningBid = bid;
			}
		}

		return winningBid;
	}

	@Override
	public List<Item> getAllItemsForUser(User user) {

		Objects.requireNonNull(user);

		List<Bid> bids = getAllBidsForUser(user);

		List<Item> items = bids.stream()
							.map(Bid::getItem)
							.distinct()
							.collect(Collectors.toList());

		return items;
	}

}
