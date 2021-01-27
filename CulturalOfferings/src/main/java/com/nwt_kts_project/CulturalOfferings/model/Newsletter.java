package com.nwt_kts_project.CulturalOfferings.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "newsletter")
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private Date date;

    @OneToMany(mappedBy = "newsletter",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Picture> pictures = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private CulturalOffering CulturalOffering;
    //validacija

    public Newsletter() {
        super();
    }

    public Newsletter(Long id, String title, String content, Date date,
                      Set<Picture> pictures, CulturalOffering culturalOffering) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.pictures = pictures;
        this.CulturalOffering = culturalOffering;
    }

    public Newsletter(String title, String content, Date date, CulturalOffering culturalOffering) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.CulturalOffering = culturalOffering;
    }
    
    public Newsletter(String title,String content, Date date) {
    	super();
    	this.title = title;
    	this.content = content;
    	this.date = date;
    }
    public Newsletter(Long id,String title,String content,Date date) {
    	this.id = id;
    	this.title = title;
    	this.content = content;
    	this.date = date;
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

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public void setCulturalOffering(CulturalOffering culturalOffering) {
        CulturalOffering = culturalOffering;
    }

    public CulturalOffering getCulturalOffering() {
        return CulturalOffering;
    }

	@Override
	public String toString() {
		return "Newsletter [id=" + id + ", title=" + title + ", content=" + content + ", date=" + date + ", pictures="
				+ pictures + ", CulturalOffering=" + CulturalOffering + "]";
	}
    
    
}