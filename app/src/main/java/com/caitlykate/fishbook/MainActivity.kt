package com.caitlykate.fishbook

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.caitlykate.fishbook.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var rootElement: ActivityMainBinding
    var adapter : MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityMainBinding.inflate(layoutInflater)
        setContentView(rootElement.root)
        nav_view.setNavigationItemSelectedListener (this)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        init()

        var list = ArrayList<ListItem>()

        list.addAll(fillArray(resources.getStringArray(R.array.fish_title),     //заполняем контент по умолчанию
                              resources.getStringArray(R.array.fish_content),
                              getImageIdArray(R.array.fish_image)))

        rcView.hasFixedSize()
        rcView.layoutManager = GridLayoutManager(this,2) //LinearLayoutManager(this)        //hz
        rcView.adapter = adapter

    }

    private fun init(){
        setSupportActionBar(rootElement.mainContent.toolbar)                  //уведомляем систему что у нас вой тулбар
        val toggle = ActionBarDrawerToggle(this, rootElement.drawerLayout,
            rootElement.mainContent.toolbar, R.string.open, R.string.close)   //создаем кнопку
        rootElement.drawerLayout.addDrawerListener(toggle)                    //указываем, что наше меню (drawerLayout) будет
        //открываться по нажатию на эту кнопку
        toggle.syncState()
        rootElement.navView.setNavigationItemSelectedListener (this)          //наш navView будут передавать событие(нажатие)
                                                                              // сюда (в этот класс)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.fish -> {
                adapter?.updateAdapter(fillArray(resources.getStringArray(R.array.fish_title),
                                                 resources.getStringArray(R.array.fish_content),
                                                 getImageIdArray(R.array.fish_image)))

            }
            R.id.nazh -> Toast.makeText(this,"Здесь будут наживки",Toast.LENGTH_LONG).show()
            R.id.snas -> Toast.makeText(this,"Здесь будут снасти",Toast.LENGTH_LONG).show()
            R.id.history -> Toast.makeText(this,"Здесь будут истории",Toast.LENGTH_LONG).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun fillArray(titleArray: Array<String>,contentArray: Array<String>,imageArray: IntArray): ArrayList<ListItem>{
        var listItemArray = ArrayList<ListItem>()
        for(n in titleArray.indices){
            var listItem = ListItem(imageArray[n],titleArray[n],contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageIdArray(imageArrayId: Int): IntArray {   //расшифровываем наши ресурсы-картинки в int
        var tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        var count = tArray.length()
        var ids = IntArray(count)
        for (i in ids.indices) {
            ids[i] = tArray.getResourceId(i, 0)
        }
        tArray.recycle()                        //даем возможность переиспользовать массив
        return ids
    }
}