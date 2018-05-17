package com.sbd.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author rushikeshM
 * Sequence Collection on MongoDB to maintaining sequence
 */

@Document(collection = "sequence")
public class SequenceId {

	@Id
	private String id;

	private int seq;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

}