
package com.example.dexterphones.model.phones;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
@Generated("jsonschema2pojo")
public class Phone {

    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("phone_name")
    @Expose
    private String phoneName;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("detail")
    @Expose
    private String detail;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Phone() {
    }

    /**
     * 
     * @param image
     * @param detail
     * @param brand
     * @param phoneName
     * @param slug
     */
    public Phone(String brand, String phoneName, String slug, String image, String detail) {
        super();
        this.brand = brand;
        this.phoneName = phoneName;
        this.slug = slug;
        this.image = image;
        this.detail = detail;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
