package com.example.reviewmycp.utlis.rxbus;

public class RxBusAction {
    /**
     * 平滑滚动到首页
     */
    public static final String CLICK_TOP = "click_top";
    /**
     * 更新头像
     */
    public static final String UPLOAD_AVATAR = "upload_avatar";
    /**
     * 更新个人信息
     */
    public static final String UPDATE_USER_INFO = "update_user_info";
    /**
     * 更新手机
     */
    public static final String UPDATE_PHONE = "update_phone";
    /**
     * 新增手机
     */
    public static final String UPDATE_ADDRESS = "update_address";
    /**
     * 设置未使用优惠券
     */
    public static final String SET_COUPON_UNDO = "coupon_undo";
    /**
     * 设置已使用优惠券
     */
    public static final String SET_COUPON_DONE = "coupon_done";
    /**
     * 设置已过期使用优惠券
     */
    public static final String SET_COUPON_PASS = "coupon_pass";
    /**
     * 跳转首页哪个界面
     */
    public static final String MAIN_INDEX = "main_index";
    /**
     * 更新底部导航消息数量
     */
    public static final String INDEX_NAVIGATION_MSG = "index_navigation_msg";


    /**
     * 商品详情，参数更改
     */
    public static final String GOOD_DETAIL_STANDARD_CHANGE = "good_detail_standard_change";
    /**
     * 砍价列表数据
     */
    public static final String MINUS_LIST = "minus_list";
    /**
     * 提现金额
     */
    public static final String WITHDRAW_MONEY = "withdraw_money";
    /**
     * 评论列表
     */
    public static final String COMMENT_LIST_MINE = "comment_list_mine";
    /**
     * 评论成功
     */
    public static final String COMMENT_PUBLISH_SUCCESS = "COMMENT_PUBLISH_SUCCESS";
    /**
     * 登录失效
     */
    public static final String LOGOUT_TOKEN = "logout_token";
    /**
     * 点击退出登录
     */
    public static final String LOGOUT = "logout";
    /**
     * 砍价列表刷新
     */
    public static final String MINUS_REFRESH = "minus_refresh";
    /**
     * 商品支付成功
     */
    public static final String PAY_SUCCESS = "pay_success";
    /**
     * 选择地址成功
     */
    public static final String CHOOSE_ADDRESS = "choose_address";
    /**
     * 加入购物车
     */
    public static final String ADD_CART = "ADD_CART";
    /**
     * vip购买成功
     */
    public static final String VIP_SUCCESS = "vip_success";
    /**
     * 充值成功
     */
    public static final String BALANCE_SUCCESS = "balance_success";
    /**
     * 添加银行卡
     */
    public static final String BANK_ADD = "bank_add";
    /**
     * 选择银行卡
     */
    public static final String BANK_CHOOSE = "bank_choose";
    /**
     * 提现成功
     */
    public static final String WITHDRAW_SUCCESS = "withdraw_success";
    /**
     * 足迹是否全部选中
     */
    public static final String FOOTER_ALL = "footer_all";
    /**
     * 删除地址
     */
    public static final String ADDRESS_DELETE = "member-address/del";
    /**
     * 购物车全选改变
     */
    public static final String SHOP_CART_ALL_CHANGE = "shop_cart_all_change";

    /**
     * 购物车总金额改变，特别是初始化的时候
     */
    public static final String SHOP_CART_INIT_SELECT_MONEY = "shop_cart_init_select_money";


    /**
     * 确认收货
     */
    public static final String ORDER_GET_SUCCESS = "order_get_success";
    /**
     * 店铺管理
     */
    public static final String UPLOAD_SHOP = "upload_shop";
    /**
     * 删除订单
     */
    public static final String ORDER_DELETE = "order_delete";
    /**
     * 优惠券选择成功
     */
    public static final String COUPON_CHOOSE = "coupon_choose";
    /**
     * 登录成功
     */
    public static final String SIGN_IN = "sign_in";
    /**
     * 代理人数获取成功
     */
    public static final String MEMBERS_NUM = "members_num";
    /**
     * 切换分类
     */
    public static final String SORT = "sort";
    /**
     * 切换分类子角标
     */
    public static final String SORT_INDEX = "sort_index";
    /**
     * 购物车数量
     */
    public static final String CART_NUM = "cart_num";
    /**
     * 团队销售--店主
     */
    public static final String TEAM_SALES_SHOPPER_NUMBER = "team_sales_shopper_number";
    /**
     * 团队销售--今日出单
     */
    public static final String TEAM_SALES_ORDER_NUMBER = "team_sales_order_number";
    /**
     * 订单详情页操作事件
     */
    public static final String ORDER_DETAIL_COMMIT = "order_detail_commit";
    /**
     * 订单列表支付成功返回
     */
    public static final String ORDER_LIST_PAY_SUCCESS = "order_list_pay_success";

    /**
     * 消息数据刷新
     */
    public static final String MESSAGE_NUM_REFRESH = "message_num_refresh";
    /**
     * 一键已读
     */
    public static final String MESSAGE_NUM_REFRESH_ALL = "message_num_refresh_all";
    /**
     * 订单取消
     */
    public static final String ORDER_CANCEL = "order_cancel";
    /**
     * 余额支付成功
     */
    public static final String BALANCE_PAY_SUCCESS = "balance_pay_success";
    /**
     * 售后申请成功
     */
    public static final String RETURN_APPLY_SUCCESS = "return_apply_success";
    /**
     * 注册成功
     */
    public static final String SIGN_UP = "sign_up";
    /**
     * 列表收货成功
     */
    public static final String ORDER_GET_GOOD = "order_get_good";
    /**
     * 寄回商品
     */
    public static final String SEND_BACK = "send_back";
    /**
     * 支付界面关闭
     */
    public static final String ORDER_SUCCESS_FINISH = "order_success_finish";
    /**
     * 首页随机姓名
     */
    public static final String INDEX_NAME_RANDOM = "index_name_random";
    /**
     * 提现方式
     */
    public static final String WAY_CHOOSE = "way_choose";
    /**
     * 确认订单更新地址
     */
    public static final String UPDATE_ADDRESS_ORDER = "update_address_order";
    /**
     * 购物车数量
     */
    public static final String CART_NUM_SORT = "cart_num_sort";
    /**
     * 订单取消
     */
    public static final String ORDER_RETURN_CANCEL = "order_return_cancel";
    /**
     * 用户信息更新
     */
    public static final String USER_INFO = "user_info";
    /**
     * 收藏变化
     */
    public static final String COLLECT_CHANGE = "collect_change";
    /**
     * 参团跳转
     */
    public static final String TUAN_JOIN = "tuan_join";
    /**
     * 拼团列表刷新
     */
    public static final String TUAN_REFRESH = "tuan_refresh";
    /**
     * 广告下载
     */
    public static final String AD_DOWN = "ad_down";
    /**

     * 积分减少
     */
    public static final String INTEGRAL_SUB = "INTEGRAL_SUB";
    /**
     * 添加地址
     */
    public static final String ADD_ADDRESS = "add_address";
    /**
     * 退出App
     */
    public static final String EXIT = "exit";
    /**
     * 上级绑定成功
     */
    public static final String HIGH_NAME_REFRESH = "high_name_refresh";
    /**

     * 设置支付密码成功
     */
    public static String PAY_PWD_SUCCESS = "pay_pwd_success";

    /**
     * 解绑银行卡
     */
    public static final String BANK_UNBIND = "bank_unbind";
    /**
     * 是否开启和关闭声音
     */
    public static  final  String ACTION_VOICE="action_voice";

    /**
     * 不同意条例
     */
    public  static  final  String DIABLE_TIP="disable_tip";

    /**
     * 退出直播间
     */
    public static final String LIVE_ACTION_MSG_QUIT = "action_live_msg_quit";


    /**
     * 申请升级页面发送消息到我的页面
     */
    public static final int USER_APPLY_TO_MINE = 100;

    /**
     * 我的团队----用户列表----点击箭头查询进度
     */
    public static final int QUERY_TEAM_USER_PROGRESS = 101;

    /**2020-5-13 10:14
     * itfreashman
     * 第一次未登录，切换到购物车，我的页面的时候，关闭登录页面，自动切换到首页
     */
    public static final String AUTO_SWITCH_HOME = "auto_switch_home";

    /**
     *  2020-7-30
     *  itfreshman
     *  系统通知，点击用户等级，跳转我的页面
     */
    public static final String SWITCH_TO_MINE = "switch_to_mine";


    /**2020-6-2
     * itfreashman
     * 当请求触发了商品件数的购买，告知确认订单页面隐藏布局
     */
    public static final String HIDE_ADDRESS_LAYOUT = "hide_address_layout";

    /**
     * 点击返回键的时候，重新刷新一次，优惠券id重置
     */
    public static final String BACK_ADDRESS = "back_address_layout";


    public static final String BIND_PHONE_FINISH = "bind_phone_finish";

    /**
     * 快商税h5页面签约成功，回到余额页面，请求接口
     */
    public static final String TAX_SIGN_SUCCESS = "tax_sign_success";

    /** 2020-7-9
     *  申请卓越店主步骤2
     *  itfreashman
     *
     */
    public static final String APPLY_BRILLIANT_SHOP_STEP2 = "APPLY_BRILLIANT_SHOP_STEP2";


    /** 2020-7-9
     *  申请卓越店主步骤3
     *  itfreashman
     *
     */
    public static final String APPLY_BRILLIANT_SHOP_STEP3 = "APPLY_BRILLIANT_SHOP_STEP3";


    /** 2020-7-21
     *  启动页弹窗点击同意后发送事件,弹窗的window要触发的事件
     *  itfreashman
     */
    public static final int SPLASH_CLICK_WINDOW = 102;


    /**
     * 修改地址成功后，更新
     */
    public static final String MODIFY_ADDRESS_SUCCESS = "modify_address_success";

    /**
     * 修改地址，跨境商品异常处理
     */
    public static final String MODIFY_CROSS_GOODS = "modify_cross_goods";

    /** 2020-9-14
     *  购物车刷新标记重置
     */
    public static final String SHOP_CART_REFRESH_FLAG = "shop_cart_refresh_flag";


}
