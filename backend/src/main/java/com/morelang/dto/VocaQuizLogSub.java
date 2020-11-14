package com.morelang.dto;

import java.util.Date;

public interface VocaQuizLogSub {
	public int getLogId();
	public int getAnswerCnt();
	public int getWrongCnt();
	public int getAllCnt();
	public double getAccRate();
	public String getCountry();
	public Date getQuizTime();
}
