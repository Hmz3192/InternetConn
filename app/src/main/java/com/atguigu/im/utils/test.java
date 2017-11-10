package com.atguigu.im.utils;

/**
 * Created by ZJNU-Hmz on 2017/11/8.
 */

public class test {

    public static void main(String[] args) {
        String location = "中国浙江省金华市婺城区二环北路";
        String sheng = "省";
        String shi = "市";
        String qu = "区";

        int sheng_size = location.indexOf(sheng);
        int shi_size = location.indexOf(shi);
        int qu_size = location.indexOf(qu);
        System.out.println(sheng_size+"+"+shi_size+"+"+qu_size);
    }
}
