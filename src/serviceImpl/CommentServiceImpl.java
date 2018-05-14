package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CommentDao;
import model.Comment;
import service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	@Override
	public int insert(String id, String business_id, String user_id, float stars, String text) {
		return commentDao.insertComment(id, business_id, user_id, stars, text);
	}

	@Override
	public List<Comment> displayComment(String business_id) {
		return commentDao.selectCommentByBusiness(business_id);
	}

	@Override
	public List<Comment> displayCommentInfo(String user_id) {
		return commentDao.selectCommentByUser(user_id);
	}
}
