package com.example.mytest.common.design.strategy;

import javassist.NotFoundException;

/**
 * @Author zhangchao
 * @Date 2022/3/11
 */
public class VehicleFactory {

    public static Vehicle getVehicle(Integer type) {
        switch (type) {
            case 1:
                return new Car();
            case 2:
                return new Bus();
            case 3:
                return new Bicycle();
        }

       return new Bus();
    }


}
