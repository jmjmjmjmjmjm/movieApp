package com.cos.retrofit2movieapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private long id;
    private String url;
    private String title;
    private long year;
    private double rating;
    private long runtime;
    private String summary;
    @SerializedName("medium_cover_image")
    private String mediumCoverImage;
}
