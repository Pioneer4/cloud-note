package name.electricalqzhang.cloud.note.entity;

import java.io.Serializable;

public class Stars implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String userId;
	private Integer stars;

	public Stars() {

	}

	public Stars(String id, String userId, Integer stars) {
		super();
		this.id = id;
		this.userId = userId;
		this.stars = stars;
	}

	/**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Returns value of userId
	* @return
	*/
	public String getUserId() {
		return userId;
	}

	/**
	* Sets new value of userId
	* @param
	*/
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	* Returns value of stars
	* @return
	*/
	public Integer getStars() {
		return stars;
	}

	/**
	* Sets new value of stars
	* @param
	*/
	public void setStars(Integer stars) {
		this.stars = stars;
	}



	/**
	* Create string representation of Stars for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Stars [id=" + id + ", userId=" + userId + ", stars=" + stars + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((stars == null) ? 0 : stars.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Stars other = (Stars) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stars == null) {
			if (other.stars != null)
				return false;
		} else if (!stars.equals(other.stars))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}
