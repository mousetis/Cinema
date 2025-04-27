package model;

import java.util.Objects;

public class Room {

	String roomID;
	String roomName;
	int capacity;
	String roomType;
	String status;
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(String roomID, String roomName, int capacity, String roomType, String status) {
		super();
		this.roomID = roomID;
		this.roomName = roomName;
		this.capacity = capacity;
		this.roomType = roomType;
		this.status = status;
	}

	public Room(String roomID) {
		super();
		this.roomID = roomID;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(roomID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return Objects.equals(roomID, other.roomID);
	}

	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", roomName=" + roomName + ", capacity=" + capacity + ", roomType=" + roomType
				+ ", status=" + status + "]";
	}
	
	
}
