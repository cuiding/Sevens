package seven.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import seven.entity.Reply;
import seven.entity.Topic;
import seven.service.ReplyService;
import seven.service.TopicService;
import seven.util.ResponseUtil;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ReplyAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	@Resource
	private ReplyService replyService;
	
	@Resource
	private TopicService topicService;
	
	private String page;
	private String rows;
	private Long total;
	private String pageCode;
	
	private String ids;
	
	private String mainPage;

	private String crumb1;
	
	private Reply reply;
	
	private int topicId;
	
	private int replyId;
	
	
	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getCrumb1() {
		return crumb1;
	}

	public void setCrumb1(String crumb1) {
		this.crumb1 = crumb1;
	}

	public String save()throws Exception{
		reply.setPublishTime(new Date());
		System.out.println(reply.getTopic().getId());
		Topic topic=topicService.findTopicById(reply.getTopic().getId());
		topic.setModifyTime(new Date());
		topicService.saveTopic(topic);
		
		replyService.saveReply(reply);
		JSONObject result=new JSONObject();
		result.put("success", true);
		/*数据回显在页面上*/
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		Reply reply=replyService.findReplyById(replyId);
		replyService.deleteReply(reply);
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
