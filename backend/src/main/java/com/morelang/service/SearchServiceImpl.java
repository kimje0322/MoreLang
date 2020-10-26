package com.morelang.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import com.morelang.dto.Caption;
import com.morelang.dto.Search;

@Service
public class SearchServiceImpl implements SearchService {
	static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	static final String WEB_DRIVER_PATH = "/driver/chromedriver";

	@Override
	public List<Search> search(String q, String start) throws Exception {
		List<Search> result = new ArrayList<>();

		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		WebDriver driver = new ChromeDriver();

		try {
			String url = "https://www.google.com/search?tbm=vid&tbs=cc:1&start=" + start + "&q=" + q;
			driver.get(url);
			String html = driver.getPageSource();
			Document d = Jsoup.parse(html);
			Elements videos = d.getElementsByClass("rc");

			for (Element e : videos) {
				Search s = new Search();

				String videoUrl = e.child(0).child(0).attr("href");
				if (!videoUrl.startsWith("https://www.youtube"))
					continue;

				if (videoUrl.contains("&")) {
					videoUrl = videoUrl.substring(0, videoUrl.indexOf('&'));
				}
				String id = videoUrl.substring(videoUrl.indexOf("v=") + 2);
				String imgUrl = "https://i.ytimg.com/vi/" + id + "/mqdefault.jpg";
				String title = e.getElementsByTag("h3").tagName("span").text();
				String description = e.child(1).child(1).text();

				d = Jsoup.connect("http://video.google.com/timedtext?type=list&v=" + id).userAgent("Mozilla/5.0").get();
				Elements tracks = d.getElementsByTag("track");
				List<Caption> captions = new ArrayList<>();
				for (Element track : tracks) {
					Caption c = new Caption();
					c.setId(track.attr("id"));
					c.setLang_code(track.attr("lang_code"));
					c.setLang_original(track.attr("lang_original"));
					c.setLang_translated(track.attr("lang_translated"));
					captions.add(c);
				}

				s.setId(id);
				s.setVideoUrl(videoUrl);
				s.setImgUrl(imgUrl);
				s.setTitle(title);
				s.setDescription(description);
				s.setCaptions(captions);
				result.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}

		return result;
	}

}
