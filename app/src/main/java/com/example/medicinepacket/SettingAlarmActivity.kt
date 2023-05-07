package com.example.medicinepacket

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.medicinepacket.databinding.ActivitySettingAlarmBinding

class SettingAlarmActivity : AppCompatActivity() {
    val binding by lazy {ActivitySettingAlarmBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var hour = binding.timePicker.hour.toString()
        var minute = binding.timePicker.minute.toString()
        var time = "2000-00-00 $hour:$minute:00" // 2000-00-00 형태로 설정

        // Random으로 설정, 그러나 겹칠 수 있음 다른 방법을 생각해야함
        var random = (1..100000)
        var alarmCode = random.random()
    }

    private fun setAlarm(alarmCode: Int, content : String, time : String) {
    }
}

class AlarmReceiver() :BroadcastReceiver() {
    // 정해진 시간에 AlarmManger로부터 호출을 받는 Class
    private lateinit var manager :NotificationManager // 알림의 System을 발생시키는 Manager
    private lateinit var builder :NotificationCompat.Builder // 알림의 다양한 정보를 생성
    lateinit var mainActivity : MainActivity
    companion object {
        const val CHANNEL_ID = "channel"
        const val CHANNEL_NAME = "channel1"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Suppress("DEPRECATION")

    override fun onReceive(context: Context?, intent: Intent?) {
        manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(
            NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )

        builder = NotificationCompat.Builder(context, CHANNEL_ID)

        val intent2 = Intent(mainActivity, AlarmService::class.java)
        val requestCode = intent?.extras!!.getInt("alarm_rqCode") // 해당 변수는 NULL일 수 없다.
        val title = intent.extras!!.getString("content") // 해당 변수는 NULL일 수 없다.

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(context,requestCode,intent2,PendingIntent.FLAG_IMMUTABLE); //Activity를 시작하는 인텐트 생성
        }else {
            PendingIntent.getActivity(context,requestCode,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        }
        val notification = builder.setContentTitle(title)
            .setContentText("SCHEDULE MANAGER")
            .setSmallIcon(R.drawable.baseline_alarm_24)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        manager.notify(1, notification)
    }
}

class AlarmService {
    // 백그라운드에서 알람을 실행시킴.
}
class AlarmFunctions {
    // 정해진 시간에 AlarmManger로부터 호출을 받음.
}

class RebootAlarmReceiver {
    // 기기 재부팅 시 취소되었던 알람들을 재설정함.
}
