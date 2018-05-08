package com.myweb.picture.model;

/**
 */
public class FrontPictureDto {

    /**
     * 型号ID
     */
    private Long modelId;

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 型号
     */
    private String modelName;

    /**
     * 图片
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
}
