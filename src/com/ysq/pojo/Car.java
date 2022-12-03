package com.ysq.pojo;

public class Car extends Vehicle {
    /**
     * 型号
     */
    private String model;

    /**
     * 创建Car对象
     */
    public Car() {
        super("未知", "未知", 0);
        model = "未知";
    }
    /**
     * 创建Car对象
     * @param vehicleId 车牌号
     * @param brand 品牌
     * @param perRent 日租金
     * @param model 汽车型号
     */
    public Car(String vehicleId, String brand, String model, int perRent) {
        super(vehicleId, brand, perRent);
        this.model = model;
    }

    /**
     * 计算租金
     * @param days 租车天数
     * @return 租金
     */
    @Override
    public float calRent(int days) {
        float rent;
        if(days<=7) rent = getPerRent() * days;
        else if(days<=30) rent = (getPerRent() * days) * 0.9F;
        else if(days<=150) rent = (getPerRent() * days) * 0.8F;
        else rent = (getPerRent() * days) * 0.7F;
        return rent;
    }

    @Override
    public String getTypeName() {
        return "型号";
    }

    /**
     * 获取型号
     * @return 型号
     */
    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void updateModel() {

    }

    @Override
    public String getName() {
        return "轿车";
    }
}