/**
 * 
 */
package com.flower.bd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.bd.po.UserInfoPo;
import com.flower.bd.po.mapper.UserInfoMapper;

/**
 * @author bc
 *
 */
@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoMapper uiMapper;
	
	public UserInfoPo getUserInfoPo(String userid,String pwd) {
		return uiMapper.getUserInfo(userid, pwd);
	}
	
	public boolean changePwd (String userId,String oldPassword,String newPassword) {
		int count =  uiMapper.updatePwd(userId, oldPassword, newPassword);
		if (count>0) {
			return true;
		} else {
			return false;
		}
	}


}
