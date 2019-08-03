package org.yue.main.Controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yue.main.pojo.User;
import org.yue.main.repository.UserRepository;

 
@Controller
public class mysqlTest {
	@Autowired
    private UserRepository userRepository;
	@ResponseBody
	@RequestMapping("add")
//	添加操作
	public String addTable(@RequestParam String name) {
		User n = new User();
        n.setName(name);
        n.setEmail("1079051908@qq.com");
        n.setAge("22");
        userRepository.save(n);
		return "数据插入成功";
	}
	@ResponseBody
	@RequestMapping("getAll")
//	获取表全部
	public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }
	@ResponseBody
	@RequestMapping("seleteByName")
//	筛选表通过名字字段
	public User getUserByUserName(@RequestParam String name) {
		return userRepository.findByName(name);
	}
	@ResponseBody
	@RequestMapping("getById")
//	通过id筛选返回给前端的是数组包对象
	public List<User> findOne(){
		return userRepository.findById(3);
	}
	@ResponseBody
	@RequestMapping("getByIdObj")
//	通过id筛选返回给前端的是对象
	public Object findByIdObj(){
		return userRepository.findAllById(3);
	}
	@ResponseBody
	@RequestMapping(value = "UserUpdate", method = RequestMethod.GET)
	public String update(@RequestParam int id,@RequestParam String name) {
		User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge("100");
        userRepository.save(user);
        return "更新成功";
		 
	}
	@Transactional
	@ResponseBody
	@RequestMapping(value = "UserUpdateSome", method = RequestMethod.GET)
//	更新某些字段，未指明更新的字段不会变null
	public String updateOne(@RequestParam("name") String name, @RequestParam("id") Integer id) {
		userRepository.updateOne(name,id);
	    return "更新成功";
	}
	@Transactional
	@ResponseBody
	@RequestMapping(value = "deleteUser", method = RequestMethod.GET)
//	删除操作(多条件)
    public String deleteUser(@RequestParam("age") String age,@RequestParam("name") String name) {
    	userRepository.deleteUserByWhere(age,name);
    	return "删除成功";
    }
	 
 
	 
}
