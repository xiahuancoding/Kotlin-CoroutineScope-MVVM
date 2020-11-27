package com.example.reviewmycp.utlis;


import java.io.Serializable;

/**
 * Created by Android Studio.
 * User: LeeJiTing
 * Date: 2019/9/2
 * Time: 9:57
 */
public class CommonOb implements Serializable {
    public static class CommonFields {
        private static String _TAG = "CommonFields";
        public static String ID = _TAG + "_ID";
        public static String TITLE = _TAG + "_TITLE";
        public static String SUBTITLE = _TAG + "_SUBTITLE";
        public static String TEXT = _TAG + "_TEXT";
        public static String URL = _TAG + "_URL";
        public static String LOCAL_URL = _TAG + "_LOCAL_URL";
        public static String TAG = _TAG + "_TAG";
        public static String PRICE = _TAG + "_PRICE";
        public static String TIME = _TAG + "_TIME";
        public static String TYPE = _TAG + "_TYPE";
        public static String TIME_START = _TAG + "_TIME_START";
        public static String TIME_END = _TAG + "_TIME_END";
        //用户ID专用字段
        public static String USERID = _TAG + "_USERID";
        //集合字段
        public static String LIST = _TAG + "_LIST";
        public static String NUMBER = _TAG + "_NUMBER";
        //用于跳转
        public static String WAY = _TAG + "_WAY";

        // 开始时间，结束时间
        public static String START_TIME = _TAG + "start_time";
        public static String END_TIME = _TAG + "end_time";
    }

    /**
     * 商品常用参数
     */
    public static class GoodFields extends CommonFields {

        private static String _TAG = "GoodFields";
        public static final String TOTAL_NUMBER = _TAG + "total_number";
        public static String ORIGINAL_PRICE = _TAG + "_ORIGINAL_PRICE";
        public static String IS_COUPON_CONVERT = _TAG + "_IS_COUPON_CONVERT";
        //vip
        public static String SHOP_PRICE = _TAG + "_SHOP_PRICE";
        //svip
        public static String MEMBER_PRICE = _TAG + "_MEMBER_PRICE";
        //白银
        public static String STORE_PRICE = _TAG + "_STORE_PRICE";
        //黄金
        public static  String PARTNER_PRICE=_TAG+"_PARTNER_PRICE";
        //钻石
        public static  String TEAM_PRICE=_TAG+"_TEAM_PRICE";

        public static String COLLECTED = _TAG + "_COLLECTED";
        public static String CONTENT = _TAG + "_CONTENT";
        public static String PROPERTY = _TAG + "_PROPERTY";
        public static String PARAMS = _TAG + "_PARAMS";
        public static String THUMB = _TAG + "_THUMB";
        public static String STOCK = _TAG + "_STOCK";
        public static String BRAND = _TAG + "_BRAND";
        public static String SIMILAR = _TAG + "_SIMILAR";
        public static String LIST_LABLE = _TAG + "_LIST_LABLE";
        public static String TOTAL_STOCK = _TAG + "_TOTAL_STOCK";
        public static String IS_SET_SUBSCRIBE = _TAG + "_IS_SET_SUBSCRIBE";
        public static String GOODS_SALE_TYPE = _TAG + "_GOODS_SALE_TYPE";
        public static String END_TIME = _TAG + "_END_TIME";
        public static String RANK = _TAG + "_RANK";
        public static String RATIO = _TAG + "_RATIO";
        public static String FORMAT_START_TIME = _TAG + "_FORMAT_START_TIME";
        public static String SECKILL_TYPE = _TAG + "SECKILL_TYPE";

        // 已抢多少件
        public static String HAD_ROB_NUM = _TAG + "HAD_ROB_NUM";
        // 已选规格种类总数
        public static String HAD_SELECT_ALL_TYPES = _TAG + "HAD_SELECT_ALL_TYPES";
        // 已选规格的图片地址
        public static String HAD_SELECT_STANDARD_IMG = _TAG + "HAD_SELECT_STANDARD_IMG";
        // 分享预计可赚
        public static String SHARE_GOODS_MAKE_MONEY = _TAG + "SHARE_GOODS_MAKE_MONEY";
        // 秒杀商品
        public static String IS_KILL_SECOND = _TAG + "IS_KILL_SECOND";
        // 规格选择后的钱
        public static String SELECT_STANDARD_MONEY = _TAG + "select_standard_money";
        // 规格选择后的名称
        public static String SELECT_STANDARD_NAME_LOCAL = _TAG + "select_standard_name_local";
        // 规格选择后的赚多少钱
        public static String SELECT_STANDARD_DIFFFEE = _TAG + "SELECT_STANDARD_DIFFFEE";

        // waring num
        public static String WARING_NUM = _TAG + "WARING_NUM";
        // 规格集合数据skudata
        public static String SKU_DATA = _TAG + "sku_data";
        // 是否拆单
        public static String IS_BREAK_ORDER = _TAG + "break_order";

    }

    /**
     * 多类型常用参数
     */
    public static class MultipleFields extends CommonFields {

        private static String _TAG = "MultipleFields";
        public static String ITEM_TYPE = _TAG + "_ITEM_TYPE";
        public static String IMAGE_URL = _TAG + "_IMAGE_URL";
        public static String IMAGE_ARRAYS = _TAG + "_IMAGE_ARRAYS";
        public static String BANNERS = _TAG + "_BANNERS";
        public static String SPAN_SIZE = _TAG + "_SPAN_SIZE";
        public static String NAME = _TAG + "_NAME";
        public static String STATUS = _TAG + "_STATUS";
        public static String LEFT = _TAG + "_LEFT";
        public static String RIGHT = _TAG + "_RIGHT";
        public static String ORDER_ID = _TAG + "_ORDER_ID";
        public static String GOODS_ID = _TAG + "_GOODS_ID";
        public static String SALE_ID = _TAG + "_SALE_ID";
        public static String IMAGE_BITMAP = _TAG + "IMAGE_BITMAP";
        public static String USED_TAG = _TAG + "_USED_TAG";
        public static String STATUS_NAME = _TAG + "_STATUS_NAME";
        // 消息中心，时间字段
        public static String MESSAGE_TIME = _TAG + "MESSAGE_TIME";

        // 我的收益，新增的字段
        public static String MINE_PROFIT_TIME = _TAG + "MINE_PROFIT_TIME";
        public static String MINE_PROFIT_TOTAL = _TAG + "MINE_PROFIT_TOTAL";
        // 是否显示当item的时间
        public static String IS_SHOW_ITEM_TIME = _TAG + "IS_SHOW_ITEM_TIME";

        // 用户头像
        public static String USER_AVATAR = _TAG +"user_avatar";
        // 提现失败原因
        public static String CASH_OUT_FAIL_REASON = _TAG + "CASH_OUT_FAIL_REASON";
        // 提现合计金额
        public static String CASH_OUT_TOTAL_MONEY = _TAG + "CASH_OUT_TOTAL_MONEY";
        // 我的收入item，共多少件
        public static String MINE_COLLECT_NUMS = _TAG + "MINE_COLLECT_NUMS";

    }

    /**
     * 订单
     */
    public static class OrderItemFields extends CommonFields {
        private static String _TAG = "OrderItemFields";
        public static String NUMBER = _TAG + "_NUMBER";
        public static String BUYER_NAME = _TAG + "_BUYER_NAME";
        public static String RECEIVER_NAME = _TAG + "_RECEIVER_NAME";
        public static String RECEIVER_MOBILE = _TAG + "_RECEIVER_MOBILE";
        public static String BUYER_NAME_TYPE = _TAG + "_BUYER_NAME_TYPE";
    }

    public static class ShopCartItemFields extends CommonFields {
        private static String _TAG = "ShopCartItemFields";
        public static String DESC = _TAG + "_DESC";
        public static String COUNT = _TAG + "_COUNT";
        public static String IS_SELECTED = _TAG + "_IS_SELECTED";
        public static String POSITION = _TAG + "_POSITION";
        public static String PROPERTIES_ID = _TAG + "_PROPERTIES_ID";
        public static String SKU_ID = _TAG + "_SKU_ID";
        public static String TOTAL_STOCK = _TAG + "TOTAL_STOCK";
        public static String ZU_NUM = _TAG + "_ZU_NUM";
        public static String SINGLE_MIN = _TAG + "_SINGLE_MIN";
        public static String SINGLE_MAX = _TAG + "_SINGLE_MAX";
        public static String GOODS_ID = _TAG + "_GOODS_ID";
        public static String SECKILL_TIME_BEGIN = _TAG + "SECKILL_TIME_BEGIN";
        public static String SECKILL_TIME_END = _TAG + "SECKILL_TIME_END";

    }

    /**
     * 团购
     */
    public static class TeamFileds extends CommonFields {
        private static String _TAG = "TeamFileds";
        public static String TEAM_NUMBER = _TAG + "_TEAM_NUMBER";
        public static String TEAM_PRICE = _TAG + "_TEAM_PRICE";
        public static String ORIGINAL_PRICE = _TAG + "_ORIGINAL_PRICE";
        public static String LAST_TIME = _TAG + "_LAST_TIME";
        public static String STATUS_NAME = _TAG + "_STATUS_NAME";
    }

    /**
     *
     */
    public static class IndexFields extends CommonFields {
        private static String _TAG = "IndexFields";
        public static String SUBPRICE = _TAG + "_SUBPRICE";
        public static String LASTNUMBER = _TAG + "_LASTNUMBER";
        public static String STORE_STOCK = _TAG + "STOCK";
        public static String STATUS = _TAG + "_STATUS";
        public static String PRICE = _TAG + "_PRICE";
        public static String PERSON_NUM = _TAG + "_PERSON_NUM";
        public static String SALE_NUMBER = _TAG + "_SALE_NUMBER";
        public static String PERCENT = _TAG + "_PERCENT";
        // 首页限时抢购的商品，大图
        public static String INDEX_IMAGE_BIG = _TAG  + "INDEX_IMAGE_BIG";
    }

    public static class TeamStatus extends CommonFields {
        private static String _TAG = "TeamStatus";
        public static String WAIT = _TAG + "_WAIT";
        public static String FAIL = _TAG + "_FAIL";
        public static String SUCCESS = _TAG + "_SUCCESS";

    }

    public static class LiveFields extends CommonFields {
        private static String _TAG = "LiveFields";
        public static String LIST_GOODS = _TAG + "_LIST_GOODS";
        public static String LIST_RECOMDER_GOODS = _TAG + "_LIST_RECOMDER_GOODS";
        public static String LIVE_HEAD_URL = _TAG + "_HEAD_URL";
        public static String LIVE_ROOM_TITLE = _TAG + "_LIVE_ROOM_NAME";
        //点赞数
        public static String LIVE_ROOM_LIKE = _TAG + "_LIVE_LIKE_NUMBER";
        //观看总人数
        public static String LIVE_ROOM_PT = _TAG + "_LIVE_LIKE_PT";
        //场次介绍
        public static String LIVE_ROOM_INTRODUCE = _TAG + "_LIVE_ROOM_INTRODUCE";
        //用户基属性
        //用户名字
        public static String LIVE_USER_NAME = _TAG + "_LIVE_NAME";
        public static String LIVE_USER_COLOR = _TAG + "_LIVE_COLOR";
        //推流地址
        public static String LIVE_PLAY_URL = _TAG + "LIVE_PLAY_URL";
        //消息ID
        public static  String LIVE_MESSAGE_ID=_TAG+"LIVE_MESSAGE_ID";
    }

    /**
     * 名义出品等级
     */
    public static class MYCB_LEVEL {
        public static int vip = 1;
        public static int svip = 20;
        public static int silver_vip = 30;
        public static int gold_vip = 52;
        public static int diamonds_vip = 51;

    }
    /**
     * 扩展字段
     */
    public static class ExtendFields {
        private static String _TAG = "extendFields";
        public static String EXTEND_1=_TAG+"_1";
        public static String EXTEND_2=_TAG+"_2";
        public static String EXTEND_3=_TAG+"_3";
        public static String EXTEND_4=_TAG+"_4";
        public static String EXTEND_5=_TAG+"_5";
        public static String EXTEND_6=_TAG+"_6";
        public static String EXTEND_7=_TAG+"_7";
        public static String EXTEND_8=_TAG+"_8";
        public static String EXTEND_9=_TAG+"_9";
        public static String EXTEND_10=_TAG+"_10";

        // 活动立减
        public static String EXTEND_ACTIVITY_FEE = _TAG + "extend_activity_fee";
    }
}
