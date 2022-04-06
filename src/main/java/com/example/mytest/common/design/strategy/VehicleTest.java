package com.example.mytest.common.design.strategy;

/**
 * @Author zhangchao
 * @Date 2022/3/11
 */
public class VehicleTest {

    public static void main(String[] args) {
        Vehicle vehicle = VehicleFactory.getVehicle(1);
        vehicle.run();
    }
}
