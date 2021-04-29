package model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDto {

	
	private Long num;
	
	private String id;
	
	private String password;
	
	private String email;
	
	 private String address;
	
	private String phoneNumber;

	private String petName;
	
    private String img;


    private int creditRating;

    private String creditGrade;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
   
	
	


    

//	@Override
//	public String toString() {
//		return "UserDto [id=" + id + ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber
//				+ ", img=" + img + ", address=" + address + ", creditRating=" + creditRating + ", creditGrade="
//				+ creditGrade + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", petName=" + petName + "]";
//	}
	
//	public Long getNum() {
//		return num;
//	}
//
//	public void setNum(Long num) {
//		this.num = num;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public String getImg() {
//		return img;
//	}
//
//	public void setImg(String img) {
//		this.img = img;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public int getCreditRating() {
//		return creditRating;
//	}
//
//	public void setCreditRating(int creditRating) {
//		this.creditRating = creditRating;
//	}
//
//	public String getCreditGrade() {
//		return creditGrade;
//	}
//
//	public void setCreditGrade(String creditGrade) {
//		this.creditGrade = creditGrade;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getPssword() {
//		return password;
//	}
//
//	public void setPssword(String pssword) {
//		this.password = pssword;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPetName() {
//		return petName;
//	}
//
//	public void setPetName(String petName) {
//		this.petName = petName;
//	}
//	
}
