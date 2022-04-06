package com.example.mytest.common.design.template;

/**
 * @Author zhangchao
 * @Date 2022/3/11
 */
public class Coffe extends AbstractBeverage{

    /**
     * 这样通过钩子就可以选择是都要加佐料了
     */
    public boolean addCondiments = true;


    /**
     * 添加糖、牛奶
     */
    @Override
    protected void addCondiments() {
        System.out.println("添加糖跟牛奶");
    }

    /**
     * 咖啡冲泡方法
     */
    @Override
    protected void brew() {
        System.out.println("放入咖啡豆，使用秘制方法冲泡");
    }

    /**
     * 重写钩子
     * @return
     */
    @Override
    boolean customerWantsCondiments() {
        return addCondiments;
    }

    public boolean isAddCondiments() {
        return addCondiments;
    }

    public void setAddCondiments(boolean addCondiments) {
        this.addCondiments = addCondiments;
    }
}
