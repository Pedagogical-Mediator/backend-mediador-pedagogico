package com.example.freeaccess.controller.dto;

public class BullyingInformationDTO {
    private Integer id;
    private String description;
    private String image;
    private String formURL;

    public BullyingInformationDTO() {
    }

    public BullyingInformationDTO(String description, String image, String formURL) {
        this.description = description;
        this.image = image;
        this.formURL = formURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFormURL() {
        return formURL;
    }

    public void setFormURL(String formURL) {
        this.formURL = formURL;
    }
}
