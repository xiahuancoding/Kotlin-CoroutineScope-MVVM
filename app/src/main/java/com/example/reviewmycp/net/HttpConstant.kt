package com.example.reviewmycp.net

object HttpConstant {



    const val SPLASH_URL = "/v1/member/member-info"

    /**
     * 账号密码登录
     */
    const val USER_LOGIN_PWD = "v1/member/login-old"

    /**
     * 验证码登录
     */
    const val USER_LOGIN_CODE = "v1/member/login"

    /**
     * 发送短信：
     */
    const val SEND_CODE = "v1/member/sms"


    /**
     * 上传头像
     */
    const val UPLOAD_AVATAR = "v1/setting/edit-header"

    /**
     * 个人信息--用户业务信息（收入，余额，阳光值）
     */
    const val MEMBER_MEMBER_STAT = "v1/member/member-stat"


    /**
     * 个人中心--用户基本信息(姓名，头像，id)
     */
    const val MEMBER_MEMBER_INFO = "v1/member/member-info"

    /**
     * 申请店主接口
     */
    const val APPLY_SHOP_MANAGER = "v1/store-apply/index"


    /** 2020-7-28
     * v1/store-apply/is-show-apply : 申请店主或卓越店主的界面是否展示
     */
    const val IS_SHOW_USER_APPLY = "v1/store-apply/is-show-apply"


    /** 2020-6-15
     * v1/store-apply/set-notify : 设置已通知用户成为店主
     */
    const val HAD_NOTIFY_USER_BE_MANAGER = "v1/store-apply/set-notify"


    /** 2020-7-28
     * v1/store-apply/set-down-notify  降级通知告知服务端，已经弹过dialog了
     */
    const val LEVEL_DOWN_NOTIFY = "v1/store-apply/set-down-notify"


}