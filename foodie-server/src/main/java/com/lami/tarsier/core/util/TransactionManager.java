package com.lami.tarsier.core.util;

public interface TransactionManager{
	public void begin() throws Exception ;
	public void commit() throws Exception ;
	public void rollback() throws Exception ;
}