package com.Ecommerce.JWT.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

//@Data
//@Entity(name = "User")
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	@Id
	private String username;
	private String password;

	//need default constructor for JSON Parsing
		public JwtRequest()
		{
			
		}

		public JwtRequest(String username, String password) {
			this.setUsername(username);
			this.setPassword(password);
		}

		public String getUsername() {
			return this.username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return this.password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	
}
