package seven.service;

import java.util.List;

import seven.entity.PageBean;
import seven.entity.Reply;

public interface ReplyService {

	public Reply findLastReplyByTopicId(int topicId);
	
	public Long getReplyCountByTopicId(int topicId);
	
	public List<Reply> findReplyListByTopicId(int topicId, PageBean pageBean);
	
	public void saveReply(Reply reply);
	
	public void deleteReply(Reply reply);
	
	public Reply findReplyById(int replyId);
}
