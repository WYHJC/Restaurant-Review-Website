package service;

import java.util.List;

import model.Comment;

public interface CommentService {
	public int insert(String id,String bussiness_id,String user_id,float stars, String text);//�����
	public List<Comment> displayCommentInfo(String user_id);   //��ʾ�����ڸ�������
	public List<Comment> displayComment(String business_id);   //��ʾ����������
}
