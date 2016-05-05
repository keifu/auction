package com.auction;
import java.util.Objects;

/**
 * Class that represents an item in an auction
 * @author Keith
 */
public final class Item {

	/**
	 * Name of the item - assume unique
	 */
	private final String itemName;

	public Item(String itemName) {
		this.itemName = Objects.requireNonNull(itemName);
	}

	public String getItemName() {
		return itemName;
	}

	@Override
	public int hashCode() {
		return  itemName.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return itemName.equals(other.itemName);
	}
	
	@Override
	public String toString() {
		return this.itemName;
	}

}
