package com.wave.repository.EntityRepository;

import com.wave.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Json on 2016/12/11.
 */
public interface NewsRepository extends JpaRepository<News,Long> {
}
