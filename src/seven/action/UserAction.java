package seven.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import seven.entity.PageBean;
import seven.entity.Section;
import seven.entity.User;
import seven.service.SectionService;
import seven.service.UserService;
import seven.service.TopicService;
import seven.util.DateUtil;
import seven.util.NavUtil;
import seven.util.PageUtil;
import seven.util.ResponseUtil;
import seven.util.StringUtil;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class UserAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserService userService;
	
	@Resource
	private SectionService sectionService;
	private TopicService topicService;
	
	private HttpServletRequest request;
	
	private String nickName;
	private User user;
	private String error;
	
	private String imageCode;
	
	private String navCode;
	private String mainPage;
	
	private String ids;

	private User s_user;//这个字段什么意思？？？
	
	private String page;
	private String rows;
	private String pageCode;
	
	private String crumb1;

	private File face;
	private String faceFileName;
	
	private int userId;
	
	private List<User> userList=new ArrayList<User>();
	private List<Section> sectionList=new ArrayList<Section>();
	
	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}



	public String getImageCode() {
		return imageCode;
	}



	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}



	public String getNavCode() {
		return navCode;
	}



	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}



	public String getMainPage() {
		return mainPage;
	}



	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}



	public String getIds() {
		return ids;
	}



	public void setIds(String ids) {
		this.ids = ids;
	}



	public User getS_user() {
		return s_user;
	}



	public void setS_user(User s_user) {
		this.s_user = s_user;
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
	
	
	public File getFace() {
		return face;
	}



	public void setFace(File face) {
		this.face = face;
	}


	public String getFaceFileName() {
		return faceFileName;
	}



	public void setFaceFileName(String faceFileName) {
		this.faceFileName = faceFileName;
	}


	public String getPageCode() {
		return pageCode;
	}



	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}



	public String getCrumb1() {
		return crumb1;
	}



	public void setCrumb1(String crumb1) {
		this.crumb1 = crumb1;
	}


	public List<User> getUserList() {
		return userList;
	}



	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}


	public List<Section> getSectionList() {
		return sectionList;
	}



	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}


//注册
	public String register()throws Exception{
		if (face!=null) {
			/*头像*/
			String imageName=DateUtil.getCurrentDateStr();
			String realPath=ServletActionContext.getServletContext().getRealPath("/images/user");
			String imageFile=imageName+"."+faceFileName.split("\\.")[1];
			File saveFile=new File(realPath,imageFile);
			FileUtils.copyFile(face, saveFile);
			user.setFace("images/user/"+imageFile);
		}else{
			user.setFace("");
		}
		user.setRegTime(new Date());
		userService.saveUser(user);
		User currentUser=userService.getUserByNickName(user.getNickName());
		request.getSession().setAttribute("currentUser", currentUser);
		return "register_success";
	}
	
//修改用户信息
	public String modify()throws Exception{
		if (face!=null) {
			String imageName=DateUtil.getCurrentDateStr();
			String realPath=ServletActionContext.getServletContext().getRealPath("/images/user");
			String imageFile=imageName+"."+faceFileName.split("\\.")[1];
			File saveFile=new File(realPath,imageFile);
			FileUtils.copyFile(face, saveFile);
			user.setFace("images/user/"+imageFile);
		}else{
			
		}
		userService.saveUser(user);
		request.getSession().invalidate();
		/*return "modifySuccess";*/
		return "logout";
	}
	/*查重*/
	public String existUserWithUserName()throws Exception{
		boolean exist=userService.existUserWithNickName(nickName);
		JSONObject result=new JSONObject();
		if (exist) {
			result.put("exist", true);
		} else {
			result.put("exist", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/*public String existUserWithTrueName()throws Exception{
		boolean exist=userService.existUserWithNickName(nickName);
		JSONObject result=new JSONObject();
		if (exist) {
			result.put("exist", true);
		} else {
			result.put("exist", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}*/
//登录	
	public String login() throws Exception{
		HttpSession session=request.getSession();
		HttpServletResponse response=ServletActionContext.getResponse();
		User currentUser=userService.login(user);
		if(!imageCode.equals(session.getAttribute("sRand"))){
			error="验证码错误！";
			session.setAttribute("error", error);
		}else 
		if(currentUser==null){
			error="用户名或密码错误！";
			session.setAttribute("error", error);
		}else{
			session.setAttribute("currentUser", currentUser);
			session.removeAttribute("error");
		}
		if (user.getType()==3) {
			return "adminLogin";//管理员登录，跳转至main.jsp页面
		} else {
			return "login";
		}
	}
	
//管理员登录
	public String loginAdmin() throws Exception{
		HttpSession session=request.getSession();
		User currentUser=userService.login(user);
		if (currentUser!=null&&currentUser.getType()==2) {
			session.setAttribute("currentUser", currentUser);
		}else {
			error="用户名或密码错误！";
			return "errorAdmin";
		}
		return "loginAdmin";
	}
//退出，注销用户，事session失效	
	public String logout()throws Exception{
		request.getSession().invalidate();
		return "logout";
	}
	
	/*？？？？要加一个管理员退出？？*/
//管理员退出		
	public String logout2()throws Exception{
		request.getSession().invalidate();
		return "logout2";
	}
	/*修改个人信息后*/
	public String preSave()throws Exception{
		HttpSession session=request.getSession();
		user=(User) session.getAttribute("currentUser");
		navCode=NavUtil.genNavCode("个人中心");
		return "modify";
	}
	/*个人中心*/
	public String userCenter()throws Exception{
		navCode=NavUtil.genNavCode("个人中心");
		//mainPage="userCenter/ucDefault.jsp";
		return "userCenter";
	}
	public String getUserInfo()throws Exception{
		navCode=NavUtil.genNavCode("个人中心");
		mainPage="userCenter/userInfo.jsp";
		return "userCenter";
	}
	
	public String save()throws Exception{
		HttpSession session=request.getSession();
		userService.saveUser(user);
		//session.setAttribute("currentUser", user);
		/*navCode=NavUtil.genNavCode("个人中心");
		mainPage="userCenter/userInfo.jsp";*/
		return SUCCESS;
	}
	
	public String list()throws Exception{//后台
		HttpSession session=request.getSession();
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		if (s_user==null) {
			Object o=session.getAttribute("s_user");
			if(o!=null){
				s_user=(User)o;
			}else{
				s_user=new User();				
			}
		}else{
			session.setAttribute("s_user", s_user);
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		userList=userService.findUserList(s_user, pageBean);
		sectionList=sectionService.findSectionList(null, null);
		long total=userService.getUserCount(s_user);
		pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/User_list.action", total, Integer.parseInt(page), 6,null);
		mainPage="user.jsp";
		crumb1="用户管理";
		return SUCCESS;
	}
	public String listMatch()throws Exception{//后台
		HttpSession session=request.getSession();
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		if (s_user==null) {
			Object o=session.getAttribute("s_user");
			if(o!=null){
				s_user=(User)o;
			}else{
				s_user=new User();				
			}
		}else{
			session.setAttribute("s_user", s_user);
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		userList=userService.findUserList(s_user, pageBean);
		sectionList=sectionService.findSectionList(null, null);
		long total=userService.getUserCount(s_user);
		pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/User_listMatch.action", total, Integer.parseInt(page), 6,null);
		mainPage="match.jsp";
		crumb1="对象匹配";
		return SUCCESS;
	}
	/*批量删除用户*/
	public String deleteUsers()throws Exception{
		JSONObject result=new JSONObject();
		String[] idsStr=ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			User u=userService.getUserById(Integer.parseInt(idsStr[i]));
			if (u.getSectionList().size()>0) {
				result.put("info", u.getNickName()+"是题主，不能删除！");
				ResponseUtil.write(ServletActionContext.getResponse(), result);
				return SUCCESS;
			}
		}
		for (int i = 0; i < idsStr.length; i++) {
			User u=userService.getUserById(Integer.parseInt(idsStr[i]));
			userService.delete(u);
		}
		result.put("info", "删除成功！");
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}
	/*删除用户*/
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		User e=userService.getUserById(userId);
		if(e.getSectionList().size()>0){
			result.put("info", "此用户是题主，不能删除！");
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return SUCCESS;
		}else {
			userService.delete(e);
			result.put("info", "删除成功！");
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return SUCCESS;
		}
	}
	
	public String saveUser()throws Exception{
		userService.saveUser(user);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	/*修改密码*/
	public String modifyPassword()throws Exception{
		User u=userService.getUserById(user.getId());
		u.setPassword(user.getPassword());
		userService.saveUser(u);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	/*报名*/
	public String saveSignUp() throws Exception{
		User userSignUp=userService.getUserById(user.getId());
		userSignUp.setMajor(user.getMajor());//专业
		userSignUp.setChoice(user.getChoice());//选择
		userSignUp.setNeed(user.getNeed());//要求
		userSignUp.setPersonDesc(user.getPersonDesc());//个人描述
		userService.saveUser(userSignUp);
		return "signup_success";
	}
	/*listMatch*/
}
