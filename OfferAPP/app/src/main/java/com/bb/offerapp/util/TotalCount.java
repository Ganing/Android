package com.bb.offerapp.util;

/**
 * Created by bb on 2016/11/27.
 */
//单例模式
public class TotalCount {

    // 如果要在静态方法中使用成员变量，那么要把成员变量转换为静态变量
    private static TotalCount sTotalCount;

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    // 用来计数
    private int mCount;

    // 禁止普通的获得对象的方式
    private TotalCount() {
        mCount = 0;
    }

    // 通过这个静态方法来获得Count类对象
    public static TotalCount getInstance() {
        if (sTotalCount == null) {
            // 由于构造器私有的，所以可以在类内部使用
            sTotalCount = new TotalCount();
        }
        return sTotalCount;
    }

    // 参数count为外部答题获得的分数
    public void addCount(int count) {
        mCount = mCount + count;
    }

    // 获取总分数的方法
    public int getCount() {
        return mCount;
    }

}

