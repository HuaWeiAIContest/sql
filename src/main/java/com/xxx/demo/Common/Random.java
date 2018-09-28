package com.xxx.demo.Common;

public  class Random {
    public static String getRandomNumString(int m){
        int num = (int)Math.pow(10,m-1) + (int)(Math.random() * Math.pow(10,m-1));
        return num+"";
    }
}
