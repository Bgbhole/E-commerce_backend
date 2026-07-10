package com.ecommerce.ecommerce.dto;

public class PasswordRequestDTO {
	


	    private Long userId;

	    private String oldPassword;

	    private String newPassword;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}

		


	    
	

}
