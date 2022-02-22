package com.sjk.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sjk.user.entity.User;
import com.sjk.user.repository.UserRepository;
import com.sjk.user.vo.Department;
import com.sjk.user.vo.ResponseTemplateVO;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RestTemplate restTemplate;
	
	public User saveUser(User user) {
		return repository.save(user);
	}

	public User getUserById(Long userId) {
		return repository.findByUserId(userId);
	}

	public ResponseTemplateVO getUserWithDepartment(Long id) {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = repository.findByUserId(id);
		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class);
		vo.setUser(user);
		vo.setDepartment(department);
		return vo;
	}
}
