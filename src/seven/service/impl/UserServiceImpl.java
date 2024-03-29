package seven.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import seven.dao.BaseDAO;
import seven.entity.PageBean;
import seven.entity.User;
import seven.service.UserService;
import seven.util.StringUtil;

import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Resource
	private BaseDAO<User> baseDAO;
	
	@Override
	public void saveUser(User user) {
		baseDAO.merge(user);
	}

	//由用户名判断是否存在该用户
	@Override
	public boolean existUserWithNickName(String nickName) {
		String hql="select count(*) from User where nickName=?";
		long count=baseDAO.count(hql, new Object[]{nickName});
		if (count>0) {
			return true;
		} else {
			return false;
		}
	}
	/*@Override
	public boolean existUserWithTrueName(String trueName) {
		String hql="select count(*) from User where trueName=?";
		long count=baseDAO.count(hql, new Object[]{trueName});
		if (count>0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean existUserWithNum(String stuId) {
		String hql="select count(*) from User where stuId=?";
		long count=baseDAO.count(hql, new Object[]{stuId});
		if (count>0) {
			return true;
		} else {
			return false;
		}
	}
*/
 /*调用dao层的里面的get方法，获得结果集。*/
	@Override
	public User login(User user) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User u where u.nickName=? and u.password=?");
		//这是拼接的数据库连接的sql
		param.add(user.getNickName());
		param.add(user.getPassword());
		return baseDAO.get(hql.toString(), param);
	}
	@Override
	public List<User> findUserList(User s_user, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User");
		if (s_user!=null) {
			if (StringUtil.isNotEmpty(s_user.getNickName())) {
				hql.append(" and nickName like ?");
				param.add("%"+s_user.getNickName()+"%");
			}
			if (s_user.getType()>0) {
				hql.append(" and type = ?");
				param.add(s_user.getType());
			}
		}
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public Long getUserCount(User s_user) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from User");
		if (s_user!=null) {
			if (StringUtil.isNotEmpty(s_user.getNickName())) {
				hql.append(" and nickName like ?");
				param.add("%"+s_user.getNickName()+"%");
			}
			if (s_user.getType()>0) {
				hql.append(" and type = ?");
				param.add(s_user.getType());
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void delete(User user) {
		baseDAO.delete(user);
	}

	@Override
	public User getUserById(int id) {
		return baseDAO.get(User.class, id);
	}

	@Override
	public User getUserByNickName(String nickName) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User");
		if (StringUtil.isNotEmpty(nickName)) {
			hql.append(" and nickName = ?");
			param.add(nickName);
		}
		return baseDAO.get(hql.toString().replaceFirst("and", "where"), param);
	}
	@Override
	public List<User> findSignUser(PageBean pageBean) {
		String hql="from User where major is not null";
		return baseDAO.find(hql, new ArrayList(), pageBean);
	}
	@Override
	public Long findSignUserNum(){
		String hql="select count(*) from User where major is not null";
		return baseDAO.count(hql.toString());
	}
	@Override
	public List<User> findNoMatchUserBySex(String sex){
		List<Object> param=new LinkedList<Object>();
		param.add(sex);
		String hql="from User where matchId = 0 and sex=?";
		return baseDAO.find(hql, param);
	}
	
	public List<User> findWomenMatchUserByNeed(String need){
		String newTmp=need;  
		newTmp="%"+newTmp+"%";  		
		List<Object> param=new LinkedList<Object>();
		param.add(newTmp);
		String hql="from User where matchId = 0 and sex='女' and personDesc like ? ";
		return baseDAO.find(hql, param);
	}
	
	public List<User> findMenMatchUserByNeed(String need){	
		String newTmp=need;  
		newTmp="%"+newTmp+"%";  		
		List<Object> param=new LinkedList<Object>();
		param.add(newTmp);
		String hql="from User where matchId = 0 and sex='男' and personDesc like ? ";
		return baseDAO.find(hql, param);
	}
}
