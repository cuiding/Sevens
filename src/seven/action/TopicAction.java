package seven.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import seven.entity.PageBean;
import seven.entity.Reply;
import seven.entity.Section;
import seven.entity.Topic;
import seven.entity.User;
import seven.service.ReplyService;
import seven.service.SectionService;
import seven.service.TopicService;
import seven.service.UserService;
import seven.util.DateUtil;
import seven.util.PageUtil;
import seven.util.ResponseUtil;
import seven.util.StringUtil;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class TopicAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	@Resource
	private TopicService topicService;
	
	@Resource
	private ReplyService replyService;
	
	@Resource
	private SectionService sectionService;
	@Resource
	private UserService userService;
	
	private List<Topic> topicList;
	private List<User> userList;
	
	private String page;
	private String rows;
	private Long total;
	private String pageCode;
	private Topic s_topic;
	
	private int sectionId;
	private String sectionName;
	private Section section;
	
	private User user;
	
	private Topic topic;
	
	private int topicId;
	
	private String ids;
	
	private String mainPage;

	private String crumb1;
	private File image;
	private String imageFileName;
	
	private Map<Topic, Reply> topicLastReply=new HashMap<Topic, Reply>();
	private Map<Topic, Long> topicReplyCount=new HashMap<Topic, Long>();
	/*private Map<User, Topic> topicReplyCount=new HashMap<User, Topic>();*/
	
	private List<Topic> zdTopicList;
	private List<Topic> ptTopicList;
	private List<Topic> cardList;
	
	private Section curSection;
	private List<Section> sectionList;
	
	private List<Reply> replyList;
	
	private int topicGood;
	private int topicTop;
	
	
	public int getTopicGood() {
		return topicGood;
	}

	public void setTopicGood(int topicGood) {
		this.topicGood = topicGood;
	}

	public int getTopicTop() {
		return topicTop;
	}

	public void setTopicTop(int topicTop) {
		this.topicTop = topicTop;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	public List<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public Section getCurSection() {
		return curSection;
	}

	public void setCurSection(Section curSection) {
		this.curSection = curSection;
	}

	public List<Topic> getZdTopicList() {
		return zdTopicList;
	}

	public void setZdTopicList(List<Topic> zdTopicList) {
		this.zdTopicList = zdTopicList;
	}

	public List<Topic> getPtTopicList() {
		return ptTopicList;
	}

	public void setPtTopicList(List<Topic> ptTopicList) {
		this.ptTopicList = ptTopicList;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}
	
	public List<Topic> getCardList() {
		return cardList;
	}

	public void setCardList(List<Topic> cardList) {
		this.cardList = cardList;
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

	public Topic getS_topic() {
		return s_topic;
	}

	public void setS_topic(Topic s_topic) {
		this.s_topic = s_topic;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
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
	
	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	
	public Map<Topic, Reply> getTopicLastReply() {
		return topicLastReply;
	}

	public void setTopicLastReply(Map<Topic, Reply> topicLastReply) {
		this.topicLastReply = topicLastReply;
	}

	public Map<Topic, Long> getTopicReplyCount() {
		return topicReplyCount;
	}

	public void setTopicReplyCount(Map<Topic, Long> topicReplyCount) {
		this.topicReplyCount = topicReplyCount;
	}
	
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	/*public Map<Section, Long> getUserCardedCount() {
		return userCardedCount;
	}

	public void setUserCarded(Map<Section, Long> userCardedCount) {
		this.userCardedCount = userCardedCount;
	}*/
	public String preSave()throws Exception{
		HttpSession session=request.getSession();
		HttpServletResponse response=ServletActionContext.getResponse();
		User currentUser=(User) session.getAttribute("currentUser");
		if (currentUser==null) {
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			/*将请求转发给login.jsp*/
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		sectionList=sectionService.findSectionList(null, null);
		return "preSave";
	}
	/*保存更改*/
	public String save()throws Exception{		
		if (image!=null) {
			/*图片*/
			String imageName=DateUtil.getCurrentDateStr();
			String realPath=ServletActionContext.getServletContext().getRealPath("/images/topic");
			String imageFile=imageName+"."+imageFileName.split("\\.")[1];
			File saveFile=new File(realPath,imageFile);
			FileUtils.copyFile(image, saveFile);
			topic.setImage("images/topic/"+imageFile);
		}else{
			topic.setImage("");
		}		
		topic.setPublishTime(new Date());
		topic.setModifyTime(new Date());
		topicService.saveTopic(topic);
		return "save";
	}
	
	public String modify()throws Exception{
		if (topicId>0) {
			Topic topic=topicService.findTopicById(topicId);
			topic.setTop(topicTop);
			topic.setGood(topicGood);
			topicService.saveTopic(topic);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}
	
	public String list()throws Exception{//前台
		if(sectionId==1){//活动介绍
			return "describ";
		}
		if(sectionId==7)
		{//报名,先登录
			HttpSession session=request.getSession();
			HttpServletResponse response=ServletActionContext.getResponse();
			User currentUser=(User) session.getAttribute("currentUser");
			if (currentUser==null) 
			{
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return SUCCESS;
			}
			else
			{
				currentUser=userService.getUserById(currentUser.getId());
				if(currentUser.getMajor()!=null && !"".equals(currentUser.getMajor()))
				{//已报名
					userList=new ArrayList<User>();
					if(currentUser.getMatchId() != 0)
					{//已配对 查询二人信息
						/*这里需要更改首页显示为配对结果？？？？*/
						userList.add(userService.getUserById(currentUser.getId()));
						userList.add(userService.getUserById(currentUser.getMatchId()));
					}
					else
					{//随机匹配 男女匹配
						/*怎样在users中查找到need最大匹配？？？？？*/
						List<User> users;
						if("随机匹配".equals(currentUser.getChoice()))
						{
							if("男".equals(currentUser.getSex())){
								users=userService.findNoMatchUserBySex("女");
							}
							else
							{
								users=userService.findNoMatchUserBySex("男");
							}
						}
						else {
							if("男".equals(currentUser.getSex())){
								users= userService.findWomenMatchUserByNeed(currentUser.getNeed());
							}
							else
							{
								users=userService.findMenMatchUserByNeed(currentUser.getNeed());
							}
						}
						if(users!=null && users.size()>0){
							/*更新两个user的match_id*/
							User user=users.get(0);
							User userA=userService.getUserById(currentUser.getId());
							User userB=userService.getUserById(user.getId());
							userA.setMatchId(userB.getId());
							userB.setMatchId(userA.getId());
							userService.saveUser(userA);
							userService.saveUser(userB);
							userList.add(userA);
							userList.add(userB);
							/*更新首页显示为匹配结果*/
							/*Section curSection = sectionService.findSectionById(7);
							curSection.setName("匹配结果");*/
						}
						else
						{
							userList.add(userService.getUserById(currentUser.getId()));
						}
					}
					return "match_result";//匹配结果
				}
				return "signup";//去报名
			}				
		}
		if(sectionId==4){//信息查询
			if (StringUtil.isEmpty(page)) {
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),5);
			userList=userService.findSignUser(pageBean);
			long total=userService.findSignUserNum();
			pageCode=PageUtil.genPagination(request.getContextPath()+"/Topic_list.action", total, Integer.parseInt(page), 5,"sectionId=4");
			return "query";
		}
		HttpSession session=request.getSession();
		if (curSection==null) {
			Object o=session.getAttribute("curSection");
			if(o!=null && sectionId==0){
				curSection=(Section)o;
			}else{
				curSection=new Section();
				curSection.setId(sectionId);
				session.setAttribute("curSection", curSection);
			}
		}
		else
		{
			session.setAttribute("curSection", curSection);
		}
		section=sectionService.findSectionById(curSection.getId());
		
		session.setAttribute("section", section);
		
		zdTopicList=topicService.findZdTopicListBySectionId(curSection.getId(), null);
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		ptTopicList=topicService.findPtTopicListBySectionId(curSection.getId(), pageBean);
		HttpServletResponse response=ServletActionContext.getResponse();
		User currentUser=(User) session.getAttribute("currentUser");
		if ((currentUser==null) && (sectionId==11)) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else{
			currentUser=userService.getUserById(currentUser.getId());
			cardList=topicService.findTopicListByUserId(currentUser.getId(), pageBean);	
		}
		long total=topicService.getPtTopicCountBySectionId(curSection.getId());
		pageCode=PageUtil.genPagination(request.getContextPath()+"/Topic_list.action", total, Integer.parseInt(page), 3,null);
		for (Topic topic : zdTopicList) {
			Reply reply=replyService.findLastReplyByTopicId(topic.getId());
			Long replyCount=replyService.getReplyCountByTopicId(topic.getId());
			if (reply!=null) {
				topicLastReply.put(topic, reply);
			}
			topicReplyCount.put(topic, replyCount);
		}
		for (Topic topic : ptTopicList) {
			Reply reply=replyService.findLastReplyByTopicId(topic.getId());
			Long replyCount=replyService.getReplyCountByTopicId(topic.getId());
			if (reply!=null) {
				topicLastReply.put(topic, reply);
			}
			topicReplyCount.put(topic, replyCount);
		}		
		return SUCCESS;
	}
	
	//管理员设置置顶和精华标签
	public String listAdmin()throws Exception{
		HttpSession session=request.getSession();
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		if (s_topic==null) {
			Object o=session.getAttribute("s_topic");
			if(o!=null){
				s_topic=(Topic)o;
			}else{
				s_topic=new Topic();	
				s_topic.setTop(2);
				s_topic.setGood(2);
			}
		}else{
			session.setAttribute("s_topic", s_topic);
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 10);
		topicList=topicService.findTopicList(s_topic, pageBean);
		total=topicService.getTopicCount(s_topic);
		pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/Topic_listAdmin.action", total, Integer.parseInt(page), 10,null);
		sectionList=sectionService.findSectionList(null, null);
		mainPage="topic.jsp";
		return "successAdmin";
	}
	//展示详情
	public String details()throws Exception{
		topic=topicService.findTopicById(topicId);
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 10);
		replyList=replyService.findReplyListByTopicId(topicId, pageBean);
		total=replyService.getReplyCountByTopicId(topicId);
		StringBuffer param=new StringBuffer();
		if (topicId>0) {
			param.append("topicId="+topicId);//获取topic
		}
		pageCode=PageUtil.genPagination(request.getContextPath()+"/Topic_details.action", total, Integer.parseInt(page), 10,param.toString());
		return "details";
	}
	
	//删除评论
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		Topic topic=topicService.findTopicById(topicId);
		topicService.deleteTopic(topic);
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String delete1()throws Exception{
		JSONObject result=new JSONObject();
		String[] idsStr=ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			Topic e=topicService.findTopicById(Integer.parseInt(idsStr[i]));
			topicService.deleteTopic(e);
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
