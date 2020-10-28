package com.morelang.service;

import com.morelang.dto.Channel;

public interface ChannelService {
	public Channel getInfo(String channelId) throws Exception;
}
