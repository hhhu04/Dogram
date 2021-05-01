package model.dto;

import java.time.LocalDateTime;

import lombok.Generated;

public class MessageLogsDto {

	@Generated
	private Long num;

    private LocalDateTime createdAt;

    private Long writerNum;

    private Long readerNum;

    private String message;

    private String messageStatus;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getWriterNum() {
		return writerNum;
	}

	public void setWriterNum(Long writerNum) {
		this.writerNum = writerNum;
	}

	public Long getReaderNum() {
		return readerNum;
	}

	public void setReaderNum(Long readerNum) {
		this.readerNum = readerNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	
}
