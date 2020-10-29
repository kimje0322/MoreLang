package com.morelang.service;

import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.morelang.dto.Video;
import com.morelang.util.Https;

@Service
public class VideoServiceImpl implements VideoService {

	@Override
	public Video getInfo(String id) throws Exception {
		Video video = new Video();
		URL url = new URL(
				"https://www.googleapis.com/youtube/v3/videos?part=snippet&key=AIzaSyDQ9s4NbYiufJwA6Q2BIT0lhguBoG49pWI&id="
						+ id);
		JSONObject obj = new JSONObject(Https.get(url)).getJSONArray("items").getJSONObject(0);

		video.setId(obj.getString("id"));
		obj = obj.getJSONObject("snippet");
		video.setTitle(obj.getString("title"));
		video.setDescription(obj.getString("description"));
		video.setPublishedAt(obj.getString("publishedAt"));
		video.setChannelId(obj.getString("channelId"));
		video.setChannelTitle(obj.getString("channelTitle"));
		if (obj.keySet().contains("defaultLanguage"))
			video.setDefaultLanguage(obj.getString("defaultLanguage"));
		if (obj.keySet().contains("defaultAudioLanguage"))
			video.setDefaultAudioLanguage(obj.getString("defaultAudioLanguage"));

		return video;
	}

}
