package cn.uestc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.uestc.mapper.UserMapper;
import cn.uestc.pojo.User;
import cn.uestc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public void insert(User user){
		userMapper.insert(user);
	}
	@Override
	public void delete(User user){
		userMapper.delete(user);
	}
	@Override
	public void update(User user){
		userMapper.update(user);
	}
	@Override
	public int selectCount(User user){
		return userMapper.selectCount(user);
	}
	@Override
	public User select(User user){
		return userMapper.select(user);
	}
	@Override
	public User loginSelectCount(User user) {
		return userMapper.loginSelectCount(user);
	}
	@Override
	public User loginSelectCount1(User user1) {
		return userMapper.loginSelectCount1(user1);
	}
}
