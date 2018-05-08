package com.myweb.picture.model;

import com.myweb.core.BaseArgs;

public class FrontPictureArgs extends BaseArgs {

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 模型名
     */
    private String modelName;

    /**
     * 是否有图片 1：有 0：无
     */
    private Integer hasImg;

    /**
     * img url
     */
    private String img;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getHasImg() {
        return hasImg;
    }

    public void setHasImg(Integer hasImg) {
        this.hasImg = hasImg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
