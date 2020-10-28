package com.morelang.service;

import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.morelang.dto.Channel;
import com.morelang.util.Https;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Override
	public Channel getInfo(String id) throws Exception {

		Channel channel = new Channel();
		URL url = new URL(
				"https://www.googleapis.com/youtube/v3/channels?part=snippet,contentDetails&key=AIzaSyDQ9s4NbYiufJwA6Q2BIT0lhguBoG49pWI&id="
						+ id);
		JSONObject obj = new JSONObject(Https.get(url)).getJSONArray("items").getJSONObject(0);

		channel.setId(obj.getString("id"));
		channel.setUploads(obj.getJSONObject("contentDetails").getJSONObject("relatedPlaylists").getString("uploads"));
		obj = obj.getJSONObject("snippet");
		channel.setTitle(obj.getString("title"));
		channel.setDescription(obj.getString("description"));
		channel.setPublishedAt(obj.getString("publishedAt"));
		channel.setImgUrl(obj.getJSONObject("thumbnails").getJSONObject("high").getString("url"));
		channel.setDefaultLanguage(obj.getString("defaultLanguage"));
		channel.setCountry(obj.getString("country"));
		channel.setChannelUrl("https://www.youtube.com/channel/" + id);

		return channel;
	}

}
