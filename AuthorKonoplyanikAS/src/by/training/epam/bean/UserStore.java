package by.training.epam.bean;

import java.io.Serializable;

public class UserStore implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String role;
	private int balance;
	private int id;
	
	public UserStore() {}

	public UserStore(String name, String role, int balance, int id) {
		this.name = name;
		this.role = role;
		this.balance = balance;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + balance;
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
		UserStore other = (UserStore) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (balance != other.balance)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserStore [name=" + name + ", role=" + role + ", balance=" + balance + ", id=" + id + "]";
	}

}
