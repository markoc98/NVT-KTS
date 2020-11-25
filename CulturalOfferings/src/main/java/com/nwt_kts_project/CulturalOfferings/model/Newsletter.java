package com.nwt_kts_project.CulturalOfferings.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "newsletter")
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date date;

    private String pictures;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private CulturalOffering CulturalOffering;


    public Newsletter() {
        super();
    }

    public Newsletter(Long id, String title, String content, Date date,
                      String pictures, CulturalOffering culturalOffering) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.pictures = pictures;
        this.CulturalOffering = culturalOffering;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }
}