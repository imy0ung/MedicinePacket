package com.example.medicinepacket

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicinepacket.databinding.ActivityMainBinding
import com.example.medicinepacket.databinding.FragmentMainBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlin.concurrent.thread

class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var mainActivity: MainActivity // context를 쓰기위해서, MainActivity에서만 context가 있다.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding by lazy { FragmentMainBinding.inflate(LayoutInflater.from(container?.context), container, false) }
        mainActivity = context as MainActivity
        val FireBaseData = mutableListOf<Data>()

        // data 할당 : FireBase를 통한 data 받기
        for (i in 1..10) {
            var data = Data(medicineImage = "", medicineName = "타이레놀$i", alarmClock = "12:00", eatingDay = "3/22", BLD = "점심")
            FireBaseData.add(data)
        }
        // data 할당

        // RecyclerView : 사용
        var adapter = Adapter()
        adapter.FireBaseData = FireBaseData
        binding.itemView.adapter = adapter
        binding.itemView.layoutManager = LinearLayoutManager(mainActivity) // linear하게 띄우고싶어서

        // Activity 간 이동
        binding.plusAlarm.setOnClickListener {
            val nextIntent = Intent(mainActivity, SettingAlarmActivity::class.java)
            startActivity(nextIntent)
        } // Intent와 startActivity를 이용해, Activity간 이동을 구현한다.

        return binding.root
    }
}

// 1. AlarmFunctions : 알람 생성, 취소를 담당
// 2. AlarmReceiver : 정해진 시간에 AlarmManger로부터 호출 받음
// 3. AlarmService : 백그라운드에서 알람을 실행
// 4. RebootAlarmReceiver : 기기 재부팅 시 취소되었던 알람들을 재설정