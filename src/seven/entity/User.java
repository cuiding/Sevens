package seven.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_user")
public class User {

	private int id;        		
	private String nickName;	
	private String trueName;	
	private String password;	
	private String sex;			
	private String face;		
	private Date regTime;		
	private String email;		
	private String mobile;		
	private int type;			
	private List<Section> sectionList=new ArrayList<Section>();
	private List<Topic> topicList=new ArrayList<Topic>();
	private List<Reply> replyList=new ArrayList<Reply>();
	private String major; 
	private String stuId;
	private String personDesc;
	private int matchId;
	private int carded;
	private String choice;
	private String need;
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(length=20)
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Column(length=20)
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	@Column(length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=20)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(length=20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length=10)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	/*@OneToMany(mappedBy="master")*/
	/*@OneToMany(targetEntity=Section.class,cascade=CascadeType.ALL)*/
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="masterId", updatable=false)
	public List<Section> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}
	
	@OneToMany(mappedBy="user")
	@Cascade(value={CascadeType.DELETE})
	public List<Topic> getTopicList() {
		return topicList;
	}
	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}
	
	@OneToMany(mappedBy="user")
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	@Column(length=64)
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Column(length=64)
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	@Column(length=1024)
	public String getPersonDesc() {
		return personDesc;
	}
	public void setPersonDesc(String personDesc) {
		this.personDesc = personDesc;
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	public String getNeed() {
		// TODO Auto-generated method stub
		return need;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String getChoice() {
		// TODO Auto-generated method stub
		return choice;
	}
	public void setCarded(int carded) {
		this.carded = carded;
	}
	public int getCarded() {
		// TODO Auto-generated method stub
		return carded;
	}
}
