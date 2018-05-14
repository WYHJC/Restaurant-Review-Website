package service;

import java.util.List;

import model.Comment;

public interface CommentService {
	public int insert(String id,String bussiness_id,String user_id,float stars, String text);//插入表
	public List<Comment> displayCommentInfo(String user_id);   //显示评论在个人中心
	public List<Comment> displayComment(String business_id);   //显示评论在详情
}
