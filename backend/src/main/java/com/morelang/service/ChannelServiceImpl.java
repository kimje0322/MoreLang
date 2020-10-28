package com.morelang.service;

import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.morelang.dto.Channel;
import com.morelang.util.Https;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Override
	public Channel getInfo(String channelId) throws Exception {

		Channel channel = new Channel();
		URL url = new URL(
				"https://www.googleapis.com/youtube/v3/channels?part=snippet,contentDetails&key=AIzaSyDQ9s4NbYiufJwA6Q2BIT0lhguBoG49pWI&id="
						+ channelId);
		JSONObject obj = new JSONObject(Https.get(url));
		return channel;
	}

}
