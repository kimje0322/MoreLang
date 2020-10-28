package com.morelang.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.morelang.config.Webhook;
import com.morelang.dto.Channel;
import com.morelang.dto.Playlist;
import com.morelang.dto.PlaylistItem;
import com.morelang.util.Https;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Override
	public Channel getInfo(String id) throws Exception {
		Channel channel = new Channel();
		URL url = new URL(
				"https://www.googleapis.com/youtube/v3/channels?part=snippet,contentDetails&key=AIzaSyDQ9s4NbYiufJwA6Q2BIT0lhguBoG49pWI&id="
						+ id);
		Webhook.url = url.toString();
		JSONObject obj = new JSONObject(Https.get(url)).getJSONArray("items").getJSONObject(0);

		channel.setId(obj.getString("id"));
		channel.setUploads(obj.getJSONObject("contentDetails").getJSONObject("relatedPlaylists").getString("uploads"));
		obj = obj.getJSONObject("snippet");
		channel.setTitle(obj.getString("title"));
		channel.setDescription(obj.getString("description"));
		channel.setPublishedAt(obj.getString("publishedAt"));
		channel.setImgUrl(obj.getJSONObject("thumbnails").getJSONObject("high").getString("url"));
		if (obj.keySet().contains("defaultLanguage"))
			channel.setDefaultLanguage(obj.getString("defaultLanguage"));
		if (obj.keySet().contains("country"))
			channel.setCountry(obj.getString("country"));
		channel.setChannelUrl("https://www.youtube.com/channel/" + id);

		return channel;
	}

	@Override
	public Playlist getItems(String id, String token) throws Exception {
		Playlist playlist = new Playlist();
		URL url = new URL(
				"https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&key=AIzaSyDQ9s4NbYiufJwA6Q2BIT0lhguBoG49pWI&playlistId="
						+ id + "&pageToken=" + (token == null ? "" : token));
		Webhook.url = url.toString();
		JSONObject obj = new JSONObject(Https.get(url));

		if (obj.keySet().contains("nextPageToken"))
			playlist.setToken(obj.getString("nextPageToken"));

		List<PlaylistItem> items = new ArrayList<>();
		JSONArray arr = obj.getJSONArray("items");

		for (int i = 0; i < arr.length(); i++) {
			JSONObject j = arr.getJSONObject(i).getJSONObject("snippet");
			PlaylistItem item = new PlaylistItem();
			item.setId(j.getJSONObject("resourceId").getString("videoId"));
			item.setTitle(j.getString("title"));
			item.setDescription(j.getString("description"));
			item.setPublishedAt(j.getString("publishedAt"));
			item.setImgUrl("https://i.ytimg.com/vi/" + item.getId() + "/maxresdefault.jpg");
			item.setVideoUrl("https://www.youtube.com/watch?v=" + item.getId());
			items.add(item);
		}
		playlist.setItems(items);

		return playlist;
	}

}
