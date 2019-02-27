package name.electricalqzhang.cloud.note.dao;

import name.electricalqzhang.cloud.note.entity.Stars;

public interface StarsDao {
	Stars findStarsByUserId(String userId);

	int insertStars(Stars stars);

	int updateStars(Stars stars);
}
