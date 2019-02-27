package name.electricalqzhang.cloud.note.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;

	// 发帖人
	private Person person;

	// 当前帖子收到的回复
	private List<Comment> comments = null;



	/**
	* Default empty Post constructor
	*/
	public Post() {
		super();
		comments = new ArrayList<Comment>();
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
	public Integer getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(Integer id) {
		this.id = id;
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
	* Returns value of person
	* @return
	*/
	public Person getPerson() {
		return person;
	}

	/**
	* Sets new value of person
	* @param
	*/
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	* Returns value of comments
	* @return
	*/
	public List<Comment> getComments() {
		return comments;
	}

	/**
	* Sets new value of comments
	* @param
	*/
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	* Create string representation of Post for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", person=" + person + ", comments=" + comments + "]";
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
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
