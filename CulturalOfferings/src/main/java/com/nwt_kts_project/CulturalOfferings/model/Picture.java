package com.nwt_kts_project.CulturalOfferings.model;

import javax.persistence.*;

@Entity
public class Picture {

    @Id
    private String picturePath;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private CulturalOffering culturalOffering;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Newsletter newsletter;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Review review;

    public Picture(String picturePath,CulturalOffering culturalOffering, Newsletter newsletter, Review review) {
        this.picturePath = picturePath;
        this.culturalOffering = culturalOffering;
        this.newsletter = newsletter;
        this.review = review;
    }
    public Picture() {
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

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPicturePath() {
        return picturePath;
    }

}
