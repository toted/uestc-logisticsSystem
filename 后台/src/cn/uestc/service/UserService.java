package cn.uestc.service;


import cn.uestc.pojo.User;

public interface UserService {

	public void insert(User user);
	public void delete(User user);
	public void update(User user);
	public int selectCount(User user);
	public User select(User user);
	public User loginSelectCount(User user);
	public User loginSelectCount1(User user1);
}
