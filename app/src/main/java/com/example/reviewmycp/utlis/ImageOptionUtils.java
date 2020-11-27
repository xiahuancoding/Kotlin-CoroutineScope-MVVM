package com.example.reviewmycp.utlis;

import android.content.Context;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.reviewmycp.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageOptionUtils {
    /**
     * 获取普通的Options
     *
     * @return
     */
    public static RequestOptions getNormalOptions() {
        return new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.core_icon_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

    }


    /**
     * 首页轮播图的option
     * @return
     */
    public static RequestOptions bannderOptions(){
        return new RequestOptions()
                .optionalCenterCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();
    }


    /**
     * 获取圆角图片
     *
     * @return
     */
    public static RequestOptions getRoundOptions(int radius) {
        return new RequestOptions()
                .transforms(new CenterCrop(), new RoundedCorners(radius))
                .placeholder(R.mipmap.core_icon_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

    }




    /**
     * 获取圆角图片,大图占位符
     *
     * @return
     */
    public static RequestOptions getRoundOptionsBig(int radius) {
        return new RequestOptions()
                .transforms(new CenterCrop(), new RoundedCorners(radius))
                .placeholder(R.mipmap.icon_big_mengban)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

    }


    /**
     * 获取圆角图片,小图占位符
     *
     * @return
     */
    public static RequestOptions getRoundOptionsSmall(int radius) {
        return new RequestOptions()
                .transforms(new CenterCrop(), new RoundedCorners(radius))
                .placeholder(R.mipmap.icon_small_mengban)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

    }



    /**
     * 获取圆角图片
     *
     * @return
     */
    public static RequestOptions getRoundOptionsTop(Context context, int radius) {
        return new RequestOptions()
                .transforms
                        (new RoundedCornersTransformation(AppDensityUtils.dip2px(context, radius),
                                0, RoundedCornersTransformation.CornerType.TOP))
                .placeholder(R.mipmap.core_icon_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

    }



    /**
     * 获取圆形头像的Options
     *
     * @return
     */
    public static RequestOptions getCircleAvatarOptions() {
        return new RequestOptions()
                .transform(new CircleCrop())
                .placeholder(R.mipmap.core_icon_default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

    }

    public static RequestOptions getCircleAvatarOptions(@DrawableRes int drawRes) {
        return new RequestOptions()
                .transform(new CircleCrop())
                .placeholder(drawRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

    }
}
