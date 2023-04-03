package com.example.medicinepacket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.medicinepacket.databinding.ItemRecyclerBinding

class Adapter: RecyclerView.Adapter<Holder>() {
    var FireBaseData = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =  ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return Holder(binding)
    } // 화면에 띄어주기 위한 onCreate

    override fun getItemCount(): Int {
        return FireBaseData.size
    } // size가 몇개인지

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val indexingData = FireBaseData.get(position)
        holder.bind(indexingData)
    } // Data 에서 값을 받아주고 holder에 넘겨준다
}

class Holder(val binding:ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root) {
    private val imageLink = binding.medicineImage
    private val medicineName = binding.medicineName
    private val alarmClock = binding.alarmClock
    private val eatingDay = binding.eatingDay
    private val BLD = binding.BLD

    fun bind(data : Data) {
        // Image
        Glide
            .with(itemView) // 어떤 뷰에? (context)
            .load(data.medicineImage) // 어디에 올릴거냐
            .placeholder(R.mipmap.ic_launcher) // 만약 링크가 안되면 기본이미지 설정
            .into(imageLink) // 이미지링크

        medicineName.text = data.medicineName
        alarmClock.text = data.alarmClock
        eatingDay.text = data.eatingDay
        BLD.text = data.BLD
    }
}