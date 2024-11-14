package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class User {
	@Id
	public String username;
	public String password;
	public long mobno;
	public String emailid;

	public String imagepath;
// when user select image from browser it goes to server server give it to spring spring store it into mulipartfile object and then we can use its methods
	public transient MultipartFile images;// MultipartFile object contains image details

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public MultipartFile getImages() {
		return images;
	}

	public void setImages(MultipartFile images) {
		this.images = images;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobno() {
		return mobno;
	}

	public void setMobno(long mobno) {
		this.mobno = mobno;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", mobno=" + mobno + ", emailid=" + emailid
				+ "]";
	}

}
