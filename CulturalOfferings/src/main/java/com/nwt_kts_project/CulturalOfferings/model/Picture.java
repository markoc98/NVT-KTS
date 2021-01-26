package com.nwt_kts_project.CulturalOfferings.model;

import com.nwt_kts_project.CulturalOfferings.dto.ReviewDTO;

import javax.persistence.*;

@Entity
@Table(name = "image_table")
public class Picture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private CulturalOffering culturalOffering;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Newsletter newsletter;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Review review;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

    public Picture(Long id,CulturalOffering culturalOffering, Newsletter newsletter, Review review) {
        this.id = id;
        this.culturalOffering = culturalOffering;
        this.newsletter = newsletter;
        this.review = review;
    }

    public Picture() {
    }

    public Picture(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;

    }
    public Picture(String name, String type, byte[] picByte, Review review) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.review = review;
    }
    public Picture(String name, String type, byte[] picByte, CulturalOffering culturalOffering) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.culturalOffering = culturalOffering;
    }
    public Picture(String name, String type, byte[] picByte, Newsletter newsletter) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.newsletter = newsletter;
    }



    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CulturalOffering getCulturalOffering() {
        return culturalOffering;
    }

    public Newsletter getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Newsletter newsletter) {
        this.newsletter = newsletter;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setCulturalOffering(CulturalOffering culturalOffering) {
        this.culturalOffering = culturalOffering;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
