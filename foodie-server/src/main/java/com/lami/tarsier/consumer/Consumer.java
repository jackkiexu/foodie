package com.lami.tarsier.consumer;

import lombok.Data;

/**
 * Created by xjk on 11/9/16.
 */
@Data
public class Consumer{
	private Integer uid;  // userId
	private String mid;
	private Long session; //  sessionId
	private Long lasting;  // login time
	private String deviceUniqueId;
}