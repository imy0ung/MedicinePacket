package com.example.medicinepacket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.medicinepacket.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 초기 화면 띄우기
        changeFragment(MainFragment())

        // bottomNavigationView를 누를 때마다 Fragment 호출
        binding.bottomNavigationView.run {
            setOnItemSelectedListener { item->
                when(item.itemId) {
                    R.id.home -> {
                        changeFragment(MainFragment())
                    } // home
                    R.id.mypage -> {
                        changeFragment(MypageFragment())
                    } // mypage
                    R.id.info -> {
                        changeFragment(InfoFragment())
                    } // information
                }
                true
            }
        }
    }

    // Fragment 넘기기, supprotFragmentManger을 통해 transaction 시도, viewPager2에 구현
    private fun changeFragment(fragment : Fragment) : Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.viewPager, fragment).commit()
        return true
    }
}

