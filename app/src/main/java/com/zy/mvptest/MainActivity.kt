package com.zy.mvptest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_pager.*


class MainActivity : AppCompatActivity() {

    lateinit var adapter: TabAdapter
    var list = ArrayList<Fragment>()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)

        list.add(ListFragment())
        list.add(ListFragment())
        adapter = TabAdapter(supportFragmentManager, list)
        tab_viewPager.adapter = adapter
        var v1 = View(this)
        var v2 = View(this)
        val params1 =
            LinearLayout.LayoutParams(dip2px(this, 12f), RelativeLayout.LayoutParams.MATCH_PARENT)
        val params2 =
            LinearLayout.LayoutParams(dip2px(this, 8f), RelativeLayout.LayoutParams.MATCH_PARENT)
        v1.layoutParams = params1
        v2.layoutParams = params2
        tab_layout.addView(v1)
        tab_layout.addView(v2)
        params1.rightMargin = dip2px(this, 3f)
        params2.leftMargin = dip2px(this, 3f)
        v1.setBackgroundColor(Color.WHITE)
        v2.setBackgroundColor(Color.BLACK)
        tab_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        v1.setBackgroundColor(Color.WHITE)
                        v2.setBackgroundColor(Color.BLACK)
                        v1.layoutParams = params1
                        v2.layoutParams = params2
                        params1.rightMargin = dip2px(this@MainActivity, 3f)
                        params2.leftMargin = dip2px(this@MainActivity, 3f)
                    }
                    1 -> {
                        v2.setBackgroundColor(Color.WHITE)
                        v1.setBackgroundColor(Color.BLACK)
                        v2.layoutParams = params1
                        v1.layoutParams = params2
                        params2.rightMargin = dip2px(this@MainActivity, 3f)
                        params1.leftMargin = dip2px(this@MainActivity, 3f)
                    }
                }

            }

        })

        /*  val layoutManager1 = LinearLayoutManager(this)
          layoutManager1.orientation = HORIZONTAL
          test_recyclerView.layoutManager = layoutManager1*/
        val layoutManager = ViewPagerLayoutManager(this, RecyclerView.HORIZONTAL)
        test_recyclerView.layoutManager = layoutManager
        /*val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(test_recyclerView)*/
        val adapter1 = GridAdapter()
        test_recyclerView.adapter = adapter1
        adapter1.setNewData(listOf("1", "2"))

        layoutManager.setOnViewPagerListener(object : OnViewPagerListener {
            override fun onInitComplete() {

            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {
                Log.e("ddd1",position.toString())
                Log.e("ddd_isBottom",isBottom.toString())
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                Log.e("ddd_isNext",isNext.toString())
                Log.e("ddd2",position.toString())
            }

        })


    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

}
