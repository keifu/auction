package com.auction;
import java.util.Objects;

/**
 * Class that represents a user of auction bid system
 * 
 * @author Keith
 */
public class User {

	/**
	 * User Name - this should be unique
	 */
	private final String userName;

	public User(String userName) {
		this.userName = Objects.requireNonNull(userName);
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public int hashCode() {
		return userName.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return userName.equals(other.userName);
	}
	
	@Override
	public String toString() {
		return this.userName;
	}
}
