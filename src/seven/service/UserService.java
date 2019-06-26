package seven.service;

import java.util.List;

import seven.entity.PageBean;
import seven.entity.User;

public interface UserService {

	public void saveUser(User user);
	
	public boolean existUserWithNickName(String nickName);
	/*public boolean existUserWithTrueName(String trueName);
	public boolean existUserWithStuId(String stuId);*/
	/**
	 * /**
	 * 这里调用dao层的里面的get方法，获得结果集。
	 * @param user 返回的用户登录对方，包含了数据库里面的用户名和密码
	 * @return
	 */
	public User login(User user);
	
	public List<User> findUserList(User s_user,PageBean pageBean);
	
	public Long getUserCount(User s_user);
	
	public void delete(User user);
	
	public User getUserById(int id);
	
	public User getUserByNickName(String nickName);
	
	public List<User> findSignUser(PageBean pageBean);
	
	public Long findSignUserNum();
	
	public List<User> findNoMatchUserBySex(String sex);
	
	public List<User> findWomenMatchUserByNeed(String need);
	
	public List<User> findMenMatchUserByNeed(String need);
}
