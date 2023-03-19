package com.example.medicinepacket

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import com.example.medicinepacket.databinding.ActivityMainBinding
import com.example.medicinepacket.databinding.FragmentMainBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlin.concurrent.thread

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding by lazy { FragmentMainBinding.inflate(LayoutInflater.from(container?.context), container, false) }

        return binding.root
    }
}

// 1. AlarmFunctions : 알람 생성, 취소를 담당
// 2. AlarmReceiver : 정해진 시간에 AlarmManger로부터 호출 받음
// 3. AlarmService : 백그라운드에서 알람을 실행
// 4. RebootAlarmReceiver : 기기 재부팅 시 취소되었던 알람들을 재설정