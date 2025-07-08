//package com.LoginRegister.example.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.LoginRegister.example.entity.Users;
//import com.LoginRegister.example.repository.UsersRepo;
//import com.LoginRegister.example.requests.LoginRequest;
//
//@Service
//public class UserService {
//
//	@Autowired
//	UsersRepo usersRepo;
//
//	public Users addUser(Users user) {
//
//		return usersRepo.save(user);
//
//	}
//
//	public Boolean loginUser(LoginRequest loginRequest) {
//
//		Optional<Users> user = usersRepo.findById(loginRequest.getUserId());
//		Users user1 = user.get();
//
//		if(user1 == null) {
//			return false;
//		}
//
//
//
//		if(!user1.getPassword().equals(loginRequest.getPassword())) {
//			return false;
//		}
//
//		return true;
//
//
//
//	}
//
//}
package com.LoginRegister.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LoginRegister.example.entity.Users;
import com.LoginRegister.example.repository.UsersRepo;
import com.LoginRegister.example.requests.LoginRequest;

@Service
public class UserService {

	@Autowired
	private UsersRepo usersRepo;

	public Users addUser(Users user) {
		return usersRepo.save(user);
	}

	public Boolean loginUser(LoginRequest loginRequest) {
		// Use email instead of ID
		Optional<Users> userOptional = usersRepo.findByEmail(loginRequest.getUserId());

		if (userOptional.isEmpty()) {
			return false; // user not found
		}

		Users user = userOptional.get();

		// Check password
		return user.getPassword().equals(loginRequest.getPassword());
	}
}
