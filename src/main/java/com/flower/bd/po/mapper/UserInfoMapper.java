/**
 * 
 */
package com.flower.bd.po.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.flower.bd.po.UserInfoPo;

/**
 * @author bc
 *
 */
@Mapper
public interface UserInfoMapper {
	
	UserInfoPo getUserInfo(@Param("userid") String userid,@Param("pwd") String pwd);
	
	int updatePwd(@Param("userId") String userId,@Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword);


}
