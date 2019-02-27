package name.electricalqzhang.cloud.note.entity;

import java.io.Serializable;

public class Note implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
    private String notebookId;
    private String userId;
    private String statusId;
    private String typeId;
    private String title;
    private String body;
    private Long   createTime;
    private Long   lastModifyTime;
    
    
    public Note() {
    	
    }

	/**
	* Default Note constructor
	*/
	public Note(String id, String notebookId, String userId, String statusId, String typeId, String title, String body, Long createTime, Long lastModifyTime) {
		super();
		this.id = id;
		this.notebookId = notebookId;
		this.userId = userId;
		this.statusId = statusId;
		this.typeId = typeId;
		this.title = title;
		this.body = body;
		this.createTime = createTime;
		this.lastModifyTime = lastModifyTime;
	}

	/**
	* Returns value of serialVersionUID
	* @return
	*/
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	* Returns value of notebookId
	* @return
	*/
	public String getNotebookId() {
		return notebookId;
	}

	/**
	* Sets new value of notebookId
	* @param
	*/
	public void setNotebookId(String notebookId) {
		this.notebookId = notebookId;
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
	* Returns value of statusId
	* @return
	*/
	public String getStatusId() {
		return statusId;
	}

	/**
	* Sets new value of statusId
	* @param
	*/
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	* Returns value of typeId
	* @return
	*/
	public String getTypeId() {
		return typeId;
	}

	/**
	* Sets new value of typeId
	* @param
	*/
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	* Returns value of title
	* @return
	*/
	public String getTitle() {
		return title;
	}

	/**
	* Sets new value of title
	* @param
	*/
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	* Returns value of body
	* @return
	*/
	public String getBody() {
		return body;
	}

	/**
	* Sets new value of body
	* @param
	*/
	public void setBody(String body) {
		this.body = body;
	}

	/**
	* Returns value of createTime
	* @return
	*/
	public Long getCreateTime() {
		return createTime;
	}

	/**
	* Sets new value of createTime
	* @param
	*/
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	* Returns value of lastModifyTime
	* @return
	*/
	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	/**
	* Sets new value of lastModifyTime
	* @param
	*/
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	/**
	* Create string representation of Note for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Note [id=" + id + ", notebookId=" + notebookId + ", userId=" + userId + ", statusId=" + statusId + ", typeId=" + typeId + ", title=" + title + ", body=" + body + ", createTime=" + createTime + ", lastModifyTime=" + lastModifyTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Note other = (Note) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
