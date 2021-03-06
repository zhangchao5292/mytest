package com.example.mytest.common.design.template;

/**
 * 1）良好的封装性。把公有的不变的方法封装在父类，而子类负责实现具体逻辑。
 * 2）良好的扩展性：增加功能由子类实现基本方法扩展，符合单一职责原则和开闭原则。
 * 3）复用代码。
 *
 * @Author zhangchao
 * @Date 2022/3/11
 */
public abstract class AbstractBeverage {
    /**
     * 这就是模板方法。它被声明为final，以免子类改变这个算法的顺序。
     * 算法步骤组合
     */
    final void prepareRecipe() {
        // 模板方法定义了一连串的步骤，每个步骤由一个方法代表
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    /**
     * 我们在这里定义了一个方法，（通常）是空的缺省实现。这个方法只会返回true，不做别的事。
     * 这就是一个钩子，子类可以覆盖这个方法，但不见得一定要这么。
     *
     * @return
     */
    boolean customerWantsCondiments() {
        return true;
    }

    /**
     * 添加佐料:不同饮料也有不同佐料：申明为抽象类，由子类取操心
     */
    protected abstract void addCondiments();

    /**
     * 酿制:不同饮料方式也不同，申明为抽象类，由子类取操心
     */
    protected abstract void brew();

    /**
     * 共通方法:倒入杯中
     */
    public void pourInCup() {
        System.out.println("倒入杯子中...");
    }

    /**
     * 把水煮沸,共通方法
     */
    public void boilWater() {
        System.out.println("把水煮沸...");
    }

}
