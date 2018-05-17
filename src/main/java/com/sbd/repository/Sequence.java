package com.sbd.repository;

import com.sbd.exception.SequenceException;
/**
 * @author rushikeshM
 *	Method Signature to fetch sequence number based on collection name.
 */
public interface Sequence {
	int getNextSequenceId(String key) throws SequenceException;
}
