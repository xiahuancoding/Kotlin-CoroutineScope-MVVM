package com.example.reviewmycp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.reviewmycp.R
import com.example.reviewmycp.fragment.HomeFragment
import com.example.reviewmycp.fragment.MessageFragment
import com.example.reviewmycp.fragment.MineFragment
import com.example.reviewmycp.fragment.ShopCarFragment
import com.example.reviewmycp.viewmodel.NoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.Badge.OnDragStateChangedListener
import q.rorbin.badgeview.QBadgeView

class MainActivity : BaseActivity<NoViewModel>() {


    private var mCurrentFragment: Fragment? = null
    private var mHomeFragment: HomeFragment? = null
    private var mMessageFragment: MessageFragment? = null
    private var mShopCarFragment: ShopCarFragment? = null
    private var mMineFragment: MineFragment? = null


    override fun layoutId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        setBottomNav()
        addBadgeAt(1, 1)
        if (savedInstanceState != null) {
            mCurrentFragment = supportFragmentManager.getFragment(savedInstanceState, "current")
            mHomeFragment = supportFragmentManager.getFragment(savedInstanceState, "home") as HomeFragment?
            mMessageFragment = supportFragmentManager.getFragment(savedInstanceState, "message") as MessageFragment?
            mShopCarFragment = supportFragmentManager.getFragment(savedInstanceState, "shopcar") as ShopCarFragment?
            mMineFragment = supportFragmentManager.getFragment(savedInstanceState, "mine") as MineFragment?

            if (mMessageFragment != null) {
                supportFragmentManager.beginTransaction().hide(mMessageFragment!!)
            }
            if (mShopCarFragment != null) {
                supportFragmentManager.beginTransaction().hide(mShopCarFragment!!)
            }
            if (mMineFragment != null) {
                supportFragmentManager.beginTransaction().hide(mMineFragment!!)
            }
            supportFragmentManager.beginTransaction().show(mCurrentFragment!!).hide(mHomeFragment!!)
                .commitAllowingStateLoss()

        } else {
            mHomeFragment = HomeFragment()
            switchFragment(Fragment(), mHomeFragment!!)
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (mCurrentFragment != null) {
            supportFragmentManager.putFragment(outState, "current", mCurrentFragment!!)
        }

        if (mHomeFragment != null) {
            supportFragmentManager.putFragment(outState, "home", mHomeFragment!!)
        }

        if (mMessageFragment != null) {
            supportFragmentManager.putFragment(outState, "message", mMessageFragment!!)
        }

        if (mShopCarFragment != null) {
            supportFragmentManager.putFragment(outState, "shopcar", mShopCarFragment!!)
        }

        if (mMineFragment != null) {
            supportFragmentManager.putFragment(outState, "mine", mMineFragment!!)
        }


    }

    private fun setBottomNav() {
        bottom_nav.enableAnimation(false)
        bottom_nav.enableShiftingMode(false)
        bottom_nav.enableItemShiftingMode(false)
        bottom_nav.setIconSize(20f, 20f)
        bottom_nav.setTextSize(10f)
        bottom_nav.setIconsMarginTop(45)
    }

    override fun initData() {
        bottom_nav.setOnNavigationItemSelectedListener {
            refreshItem()
            it.isChecked = true
            when (it.itemId) {
                R.id.bottom_nav_home -> {
                    it.setIcon(R.mipmap.icon_index_home_select)
                    switchFragment(mCurrentFragment!!, mHomeFragment!!)

                }

                R.id.bottom_nav_message -> {
                    it.setIcon(R.mipmap.icon_index_new_msg)
                    if (mMessageFragment == null) {
                        mMessageFragment = MessageFragment()
                    }
                    switchFragment(mCurrentFragment!!, mMessageFragment!!)

                }

                R.id.bottom_nav_shop -> {
                    it.setIcon(R.mipmap.icon_index_shop_cart_select)
                    if (mShopCarFragment == null) {
                        mShopCarFragment = ShopCarFragment()
                    }
                    switchFragment(mCurrentFragment!!, mShopCarFragment!!)

                }

                R.id.bottom_nav_mine -> {
                    it.setIcon(R.mipmap.icon_new_wode_select)
                    if (mMineFragment == null) {
                        mMineFragment = MineFragment()
                    }
                    switchFragment(mCurrentFragment!!, mMineFragment!!)

                }
            }

            false
        }


    }


    companion object {
        fun jumpActivity(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }

    }

    private fun switchFragment(from: Fragment, to: Fragment) {
        if (mCurrentFragment != to) {
            mCurrentFragment = to
            val transaction = supportFragmentManager.beginTransaction()
            if (!to.isAdded) {
                transaction.hide(from).add(R.id.fragment_container, to).commitAllowingStateLoss()

            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss()
            }
        }
    }

    private fun addBadgeAt(position: Int, number: Int): Badge? { // add badge
        return QBadgeView(this)
            .setBadgeTextSize(10f, true)
            .setBadgeNumber(number)
            .setGravityOffset(20f, 4f, true)
            .bindTarget(bottom_nav.getBottomNavigationItemView(position))
            .setOnDragStateChangedListener { dragState, badge, targetView ->
                if (OnDragStateChangedListener.STATE_SUCCEED == dragState) Toast.makeText(
                    this@MainActivity,
                    "啊哈哈哈啊",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    private fun refreshItem() {
        val home = bottom_nav.menu.findItem(R.id.bottom_nav_home)
        val message = bottom_nav.menu.findItem(R.id.bottom_nav_message)
        val shop = bottom_nav.menu.findItem(R.id.bottom_nav_shop)
        val mine = bottom_nav.menu.findItem(R.id.bottom_nav_mine)


        home.setIcon(R.mipmap.icon_index_home)
        home.isChecked = false
        message.setIcon(R.mipmap.icon_index_msg)
        message.isChecked = false
        shop.setIcon(R.mipmap.icon_index_shop_cart)
        shop.isChecked = false
        mine.setIcon(R.mipmap.icon_index_wode)
        mine.isChecked = false

    }

}