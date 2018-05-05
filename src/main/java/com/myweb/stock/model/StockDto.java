package com.myweb.stock.model;

/**
 * 库存页面展示
 */
public class StockDto {

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 颜色名
     */
    private String colorName;

    /**
     * 型号名
     */
    private String modelName;

    /**
     * 内存名
     */
    private String memoryName;

    /**
     * 库存数量
     */
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMemoryName() {
        return memoryName;
    }

    public void setMemoryName(String memoryName) {
        this.memoryName = memoryName;
    }
}
