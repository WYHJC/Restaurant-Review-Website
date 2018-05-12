package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.Comment;

public interface CommentDao {
	int insertComment(@Param("id")String id,@Param("business_id")String business_id,@Param("user_id")String user_id,@Param("stars")float stars, @Param("text")String text);
	public List<Comment> selectCommentByUser(@Param("user_id")String user_id);
	public List<Comment> selectCommentByBusiness(@Param("business_id")String business_id);
}
