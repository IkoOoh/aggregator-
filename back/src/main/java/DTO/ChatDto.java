package DTO;

public class ChatDto {
	private String advName;
	private String ownerName;
	private String customerUsername;
	private int advId;
	private int id;
	public ChatDto(){
		
	}
	public String getAdvName() {
		return advName;
	}
	public void setAdvName(String advName) {
		this.advName = advName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getCustomerUsername() {
		return customerUsername;
	}
	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	public int getAdvId() {
		return advId;
	}
	public void setAdvId(int advId) {
		this.advId = advId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
