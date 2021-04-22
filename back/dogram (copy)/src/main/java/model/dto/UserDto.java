package model.dto;

import java.time.LocalDateTime;

public class UserDto {

	private String id;
	
	private String pssword;
	
	private String email;
	
	private String phoneNumber;

    private String img;

    private String address;

    private int creditRating;

    private String creditGrade;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
	
	
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(int creditRating) {
		this.creditRating = creditRating;
	}

	public String getCreditGrade() {
		return creditGrade;
	}

	public void setCreditGrade(String creditGrade) {
		this.creditGrade = creditGrade;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPssword() {
		return pssword;
	}

	public void setPssword(String pssword) {
		this.pssword = pssword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
