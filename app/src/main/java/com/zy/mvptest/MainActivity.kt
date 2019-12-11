package com.zy.mvptest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
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
    var views = ArrayList<View>()
    var params1: LinearLayout.LayoutParams? = null
    var params2: LinearLayout.LayoutParams? = null


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)
        val datas = ArrayList<String>()
        for (i in 0 until 35) {
            datas.add("title$i")
        }
        val data = Utils.splitList(datas, 10)
        data?.forEachIndexed { index, arrayList ->
            list.add(ListFragment.newInstance(arrayList))
        }
        adapter = TabAdapter(supportFragmentManager, list)
        tab_viewPager.adapter = adapter
        list.forEach { _ ->
            val v = View(this)
            v.setBackgroundColor(Color.WHITE)
            views.add(v)
            tab_layout.addView(v)
        }
        params1 =
            LinearLayout.LayoutParams(dip2px(this, 15f), RelativeLayout.LayoutParams.MATCH_PARENT)
        params2 =
            LinearLayout.LayoutParams(dip2px(this, 4f), RelativeLayout.LayoutParams.MATCH_PARENT)
        params1?.leftMargin = dip2px(this, 3f)
        params1?.rightMargin = dip2px(this, 3f)
        params2?.leftMargin = dip2px(this, 3f)
        params2?.rightMargin = dip2px(this, 3f)
        setSelect(0)
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
                setSelect(position)
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

            }

            override fun onPageRelease(isNext: Boolean, position: Int) {

            }

        })


    }

    fun setSelect(int: Int) {
        list.forEachIndexed { index, fragment ->
            if (index == int) {
                views[index].layoutParams = params1
            } else {
                views[index].layoutParams = params2
            }
        }
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

}
