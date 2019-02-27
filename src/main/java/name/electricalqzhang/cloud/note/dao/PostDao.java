package name.electricalqzhang.cloud.note.dao;

import name.electricalqzhang.cloud.note.entity.Post;

public interface PostDao {
	Post findPostById(Integer id);
}
