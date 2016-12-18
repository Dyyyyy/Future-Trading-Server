package com.wave.model;

import javax.persistence.*;
import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Json on 2016/11/18.
 */
@Entity
public class News {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;

    @Lob
    private String source;

    @Temporal(TemporalType.TIMESTAMP)
    private Date release_time;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = NewsTag.class)
    private List<NewsTag> tags=new ArrayList<NewsTag>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<NewsTag> getTags() {
        return tags;
    }

    public void setTags(List<NewsTag> tags) {
        this.tags = tags;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getRelease_time() {
        return release_time;
    }

    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }
}
