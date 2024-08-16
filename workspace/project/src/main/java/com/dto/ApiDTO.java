package com.dto;

import lombok.Data;

@Data
public class ApiDTO {
	private Long id;
	private String galTitle;
	private String galPhotographyMonth;
	private String galPhotographyLocation;
	private String galPhotographer;
	private String galWebImageUrl;
	
	 // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGalTitle() {
        return galTitle;
    }

    public void setGalTitle(String galTitle) {
        this.galTitle = galTitle;
    }

    public String getGalPhotographyMonth() {
        return galPhotographyMonth;
    }

    public void setGalPhotographyMonth(String galPhotographyMonth) {
        this.galPhotographyMonth = galPhotographyMonth;
    }

    public String getGalPhotographyLocation() {
        return galPhotographyLocation;
    }

    public void setGalPhotographyLocation(String galPhotographyLocation) {
        this.galPhotographyLocation = galPhotographyLocation;
    }

    public String getGalPhotographer() {
        return galPhotographer;
    }

    public void setGalPhotographer(String galPhotographer) {
        this.galPhotographer = galPhotographer;
    }

    public String getGalWebImageUrl() {
        return galWebImageUrl;
    }

    public void setGalWebImageUrl(String galWebImageUrl) {
        this.galWebImageUrl = galWebImageUrl;
    }
	
}
