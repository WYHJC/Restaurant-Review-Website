package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Comment;
import service.CommentService;


@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	public CommentService getCommentService() {
		return this.commentService;
	}

	public void setCommentsService(CommentService commentService) {
		this.commentService = commentService;
	}

	@RequestMapping("displayCommentInfo")
	public List<Comment> displayCommentInfo(@RequestParam String user_id) {

		return commentService.displayCommentInfo(user_id);
	}

	@RequestMapping("displayComment")
	public List<Comment> displayComment(@RequestParam String business_id) {
		return commentService.displayComment(business_id);
	}

}