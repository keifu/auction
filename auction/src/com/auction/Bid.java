package com.auction;
import java.util.Objects;

/**
 * Class that represents a bid from a user
 * 
 * @author Keith
 */
public final class Bid {
	
	/**
	 * User's bid price
	 */
	private final Double bidPrice;
	
	/**
	 * Item that user bid
	 */
    private final Item item;
    
    /**
     * User who is bidding
     */
    private final User user;
    
    public Bid(Double bidPrice, Item item,  User user){
    	this.bidPrice = Objects.requireNonNull(bidPrice);
    	this.item =  Objects.requireNonNull(item);
    	this.user =  Objects.requireNonNull(user);
    }
    
    public Double getBidPrice() {
		return bidPrice;
	}
    
    public Item getItem() {
		return item;
	}
    
    public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bidPrice.hashCode();
		result = prime * result + item.hashCode();
		result = prime * result +  user.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		
		return bidPrice.equals(other.bidPrice)
				&& item.equals(other.item)
				&& 	user.equals(other.user);
		
	}
    
    
    
    
}
