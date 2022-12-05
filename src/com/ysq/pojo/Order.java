package com.ysq.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    private String userName;
    private String vehicleId;
    private double rents;
    private boolean payed;

    public Order(String vehicleId, double rents) {
        this.vehicleId = vehicleId;
        this.rents = rents;
    }

    public static String toUserJSONList(ResultSet rs) throws SQLException {
        StringBuilder str = new StringBuilder();
        boolean begin = true;
        str.append('[');
        while (rs.next()) {
            if(begin) begin = false;
            else str.append(',');

            String vehicleId = rs.getString("vehicleId");
            double rents = rs.getDouble("rents");
            str.append(new Order(vehicleId,rents).toUserJSON());
        }
        str.append(']');

        return new String(str);
    }

    public String toUserJSON() {
        return "{\"vehicleId\": \""+vehicleId+"\",\"rents\": "+rents+"}";
    }
}
