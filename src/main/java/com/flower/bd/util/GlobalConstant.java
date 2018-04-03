package com.flower.bd.util;

public interface GlobalConstant {
	
	String USER_INFO ="userinfo";
	
	public enum OperationType{
		LOGIN(1,"登录"),
		LOGOUT(2,"注销");
		private Integer ID;
		private String Name;
		private OperationType(Integer ID,String Name){
			this.setID(ID);
			this.setName(Name);
		}
		public Integer getID() {
			return ID;
		}
		public void setID(Integer iD) {
			ID = iD;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
	}
}
