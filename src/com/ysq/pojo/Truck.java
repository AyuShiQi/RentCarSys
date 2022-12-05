package com.ysq.pojo;

/**
 * 卡车类
 */
public class Truck extends Vehicle {
    /**
     * 卡车载重，单位为吨
     */
    String weight;
    /**
     * 创建Car对象
     */
    public Truck() {
        super("未知", "未知", 0);
        weight = "0";
    }
    /**
     * 创建Car对象
     * @param vehicleId 车牌号
     * @param brand 品牌
     * @param perRent 日租金
     * @param weight 卡车载重
     */
    public Truck(String vehicleId, String brand, int perRent,String weight) {
        super(vehicleId, brand, perRent);
        this.weight = weight;
    }

    @Override
    public float calRent(int days) {
        float rent;
        if(days<30) rent = days * getPerRent();
        else if (days < 200) rent = days * getPerRent() * 0.8F;
        else if(days < 365) rent = days * getPerRent() * 0.7F;
        else rent = days * getPerRent() * 0.6F;

        return rent;
    }

    @Override
    public String getModel() {
        return getWeightWithUnit();
    }

    @Override
    public String getModel(String model) {
        return model.substring(0,model.length()-1);
    }

    @Override
    public void updateModel() {

    }

    @Override
    public String getName() {
        return "货车";
    }

    public String getTypeName() {
        return "载重";
    }

    /**
     * 获取载重
     * @return 载重
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 设置新载重
     * @param weight 新载重
     */
    public boolean setWeight(String weight) {
        this.weight = weight;
        return true;
    }

    /**
     * 获取带单位的载重
     * @return 带单位载重
     */
    public String getWeightWithUnit() {
        return weight;
    }
}

