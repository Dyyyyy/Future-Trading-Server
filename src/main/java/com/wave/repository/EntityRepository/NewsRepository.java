package com.wave.repository.EntityRepository;

import com.wave.model.News;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Json on 2016/12/11.
 */
@Transactional
public interface NewsRepository extends PagingAndSortingRepository<News,Long> {
	@Query("select n from News n where n.title like :title")
//	Page<News> findByTitle(@Param("title") String title, Pageable pageable);
	ArrayList<News> findByTitleLike(@Param("title") String title);
}
