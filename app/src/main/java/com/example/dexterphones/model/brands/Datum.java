
package com.example.dexterphones.model.brands;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Datum {

    @SerializedName("brand_id")
    @Expose
    private Integer brandId;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("brand_slug")
    @Expose
    private String brandSlug;
    @SerializedName("device_count")
    @Expose
    private Integer deviceCount;
    @SerializedName("detail")
    @Expose
    private String detail;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param brandSlug
     * @param brandName
     * @param deviceCount
     * @param brandId
     * @param detail
     */
    public Datum(Integer brandId, String brandName, String brandSlug, Integer deviceCount, String detail) {
        super();
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandSlug = brandSlug;
        this.deviceCount = deviceCount;
        this.detail = detail;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandSlug() {
        return brandSlug;
    }

    public void setBrandSlug(String brandSlug) {
        this.brandSlug = brandSlug;
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
