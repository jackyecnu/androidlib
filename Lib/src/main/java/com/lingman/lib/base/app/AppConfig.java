package com.lingman.lib.base.app;

import java.security.KeyStore;

/**
 * Created by Norton on 2018/9/11.
 */


public class AppConfig {

    public interface BusinessTag {
        int BANNER_DELAY = 5000;
        boolean ENABLE_UPDATE = true;  //是否开启更新功能
    }

    //微信等的key
    public static final String APP_ID = "wx72eb5dc0051ded5c";//

    //阿里统一登录
    public static final String ALI_APP_ID = "c4OU8LU+7QIagSMS2PbvzBUuQytt17pt1XvY3lVhi6wyglhvY7GDZ6I1WGPL0faZwY07NElH1NswnksuKL1MMeJrn06psAfez+aLO0YsrKeLGj+4D0QiDqLT/fd0vOdm+D6PePV+fqOIdoUo5bSTmZngaX9pAyAw/GDBy/V5XpHTNOpqLbaJHGxiIki3EkJHI1S0rcKnHAvtgjA470Ytzl01t+pW0TBLY6CtJdwNo0bbH8R1rpgisBMCjjwvhu7swfNxlnp7VDxcIR1jAXNqBt61aNB8dPSvrp1XKaUobEoz6WY8WejR/mBMQIXWWXzX";

    //高德地图
    public static final String GAODE_KEY = "ff73b42e0ad4b632b3563c63ae51d2e5";

    public enum EventBusKey {
        LOGOUT, //退出登录
        WX_LOGIN, //微信登录成功获取code
        LOGIN, //登录成功
        CITY, //选择城市
        BRAND, //选择品牌
        CAR_CATEGORY, //车辆搜索
        VEHICLE_RECEIVE, //车辆接收
        VEHICLE_PUT_AWAY, //车辆上架
        VEHICLE_SOLE_OUT, //车辆下架
        SELECT_TAB, //选择tab
        CAR_VIN, //vin拍照剪裁
        EDIT_VEHICLE_PRICE, // 编辑价格描述
        MESSAGE_TIP, // 消息数量
        FRIEND_COUNT, // 添加好友数量
        SWITCH_TAB, // 添加好友数量
        RE_LOGIN  // 重新登录
    }

    // EventBus
//    public interface EventBusKey {
//        String LOGOUT = "LOGOUT"; //退出登录
//        String WX_LOGIN = "WX_LOGIN"; //微信登录成功获取code
//        String LOGIN = "LOGIN"; //登录成功
//        String CITY = "CITY"; //选择城市
//        String BRAND = "BRAND"; //选择品牌
//        String CAR_CATEGORY = "CAR_CATEGORY"; //车辆搜索
//        String VEHICLE_RECEIVE = "VEHICLE_RECEIVE"; //车辆接收
//        String VEHICLE_PUT_AWAY = "VEHICLE_PUT_AWAY"; //车辆上架
//        String VEHICLE_SOLE_OUT = "VEHICLE_SOLE_OUT"; //车辆下架
//        String SELECT_TAB = "SELECT_TAB"; //选择tab
//        String CAR_VIN = "CAR_VIN"; //vin拍照剪裁
//        String EDIT_VEHICLE_PRICE = "EDIT_VEHICLE_PRICE"; // 编辑价格描述
//        String MESSAGE_TIP = "MESSAGE_TIP"; // 消息数量
//        String FRIEND_COUNT = "FRIEND_COUNT"; // 添加好友数量
//        String SWITCH_TAB = "SWITCH_TAB"; // 添加好友数量
//        String RE_LOGIN = "RE_LOGIN"; // 重新登录
//    }


    public interface Config {
        int BANNER_TIME = 5000;
        //        String SERVICE_PHONE = "13661557530";
        String WX_SHARE_LINK = "https://www.taohupai.com/";

        String WX_SHARE_COMPANY_LINE = "https://www.taohupai.com/home/UploadGongpai/";

        String COMPANY_BANNER_URL = "https://s.lingman.tech/app/taohupai/temp/gongpaibanner.png";
        String COMPANY_FLOW_URL = "https://s.lingman.tech/app/taohupai/temp/liuchenggongpai.png";
        String COMPANY_EXAMPLE = "https://s.lingman.tech/app/taohupai/static/gongpai.png";
        String TENDER_AGREEMENT = "https://s.lingman.tech/taohupai/web/xieyi.html"; //淘沪牌协议
        String COMPANY_AGREEMENT = "https://s.lingman.tech/taohupai/web/gongpaixieyi.html"; //代拍协议
        String COMPANY_DOUBLE_AGREEMENT = "https://s.lingman.tech/taohupai/web/doublebidxieyi.html"; //双标书协议
        String PROVACY_AGREEMENT = "https://s.lingman.tech/app/shiliu/web/seller/privacy.html"; //隐私协议
        String SERVER_AGREEMENT = "https://s.lingman.tech/app/shiliu/web/seller/useragreement.html"; //服务协议
        String VERSION_TIME = "version_time";
    }

    public interface SPKey {
        String IS_FIRST = "is_first"; //是否第一次进入app
        String USER_ID = "user_id"; //user_id
        String USER_NAME = "user_name"; //user_name
        String USER_MOBILE = "user_mobile"; //user_mobile
        String USER_AVATAR = "user_avatar"; //用户头像
        String TOKEN = "token";
        String IS_SELLER = "is_seller"; // 是否是车商
        String TEMP_MOBILE = "temp_mobile"; //临时号码
        String CURRENT_ME_TAB = "current_me_tab";
    }


}
