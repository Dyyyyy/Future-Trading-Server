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
	
	//点击进入某一条新闻查看具体消息
	@RequestMapping(value="/news/details")
	public HashMap<String, String> getNewsDetails(@RequestParam(value="id") String id){
		
		HashMap<String, String> result = new HashMap<>();
		News news = new News();
		
		news = news_repository.findById(Long.parseLong(id));
		String title = news.getTitle();
		String content = news.getContent();
		String url = news.getUrl();
		result.put("title", title);
		result.put("content", content);
		result.put("source", url);
		return result;
	}

	//关键字搜索新闻
	@RequestMapping(value = "/news/search")
//	public Page<News> searchNews(
//			@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
//			@RequestParam(value = "keyword") String keyword) {
//		return news_repository.findByTitle(keyword, pageable);
//	}
	public HashMap<String, Object> searchNews(@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "page") String page) {
		ArrayList<News> news = new ArrayList<News>();
		news = news_repository.findByTitleLike(keyword);
		Iterator<News> it = news.iterator();
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();
		int pageIndex = (Integer.parseInt(page)-1)*18;
		int count = -1;
		int pagenum = news.size()/18 + ((news.size() % 18) == 0 ? 0 : 1) ;
		System.out.println("pageIndex : " + pageIndex + "pagenum : " + pagenum);
		while (it.hasNext()) {
			count++;
			News n = it.next();
			if(count < pageIndex)continue;
			if(count - pageIndex >= 18)break;
			HashMap<String, String> map = new HashMap<String, String>();
			String title = n.getTitle();
			String content = n.getContent();
			String url = n.getUrl();
			long id=n.getId();
			

			System.out.println("new id : " + n.getId());
			map.put("id", String.valueOf(id));
			map.put("title", title);
			map.put("content", content);
			map.put("source", url);
			result.add(map);
		}

		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("result", result);
		res.put("pagenum", pagenum);
		System.out.println("keyword" + keyword);
		return res;
	}

	//首页获取新闻列表
	@RequestMapping(value = "/news/index")
//	public Page<News> getNews(
//			@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
//		return news_repository.findAll(pageable);
//	}

	public ArrayList<HashMap<String, String>> getNews() {
		ArrayList<News> news = new ArrayList<News>();
		news = news_repository.findAll();
		Iterator<News> it = news.iterator();
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		while (it.hasNext()) {
			News n = it.next();
			HashMap<String, String> map = new HashMap<String, String>();
			String title = n.getTitle();
			String content = n.getContent();
			String url = n.getUrl();
			long id=n.getId();
			map.put("id", String.valueOf(id));
			map.put("title", title);
			map.put("content", content);
			map.put("source", url);
			result.add(map);
		}
		return result;
	}
	
	

}
