package name.electricalqzhang.cloud.note.entity;

import java.io.Serializable;

public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;
	private Integer postId;

	public Comment() {
		super();
	}

	public Comment(Integer id, String title, Integer postId) {
		super();
		this.id = id;
		this.title = title;
		this.postId = postId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", title=" + title + ", postId=" + postId + "]";
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
