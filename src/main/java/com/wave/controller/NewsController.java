package com.wave.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wave.model.News;
import com.wave.repository.EntityRepository.NewsRepository;

@RestController
public class NewsController {
	@Autowired
	private NewsRepository news_repository;
//	Pageable pageable = new PageRequest(1, 20);

	@RequestMapping(value = "/news/search", method = RequestMethod.POST)
//	public Page<News> searchNews(
//			@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
//			@RequestParam(value = "keyword") String keyword) {
//		return news_repository.findByTitle(keyword, pageable);
//	}
	public ArrayList<HashMap<String, String>> searchNews(@RequestParam(value = "keyword") String keyword) {
		ArrayList<News> news = new ArrayList<News>();
		news = news_repository.findByTitleLike(keyword);
		Iterator<News> it = news.iterator();
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		while (it.hasNext()) {
			News n = it.next();
			HashMap<String, String> map = new HashMap<String, String>();
			String title = n.getTitle();
			String content = n.getContent();
			String img = n.getUrl();
			String source = n.getSource();
			map.put("title", title);
			map.put("content", content);
			map.put("image", img);
			map.put("source", source);
			result.add(map);
		}
		return result;
	}

}
