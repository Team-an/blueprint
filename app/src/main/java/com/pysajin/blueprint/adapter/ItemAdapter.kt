package com.pysajin.blueprint.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.pysajin.blueprint.viewmodel.MainViewModel
import androidx.recyclerview.widget.RecyclerView
import com.pysajin.blueprint.databinding.ItemListBinding
import com.pysajin.blueprint.interfaces.ItemTouchHelperListener

class ItemAdapter(private val layoutId: Int, private val mMainViewModel: MainViewModel) :
    RecyclerView.Adapter<ItemAdapter.Holder>(),
    ItemTouchHelperListener {
    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        Log.e("INFO", "itemmove")
        return false
    }

    override fun onItemSwipe(position: Int) {
        Log.e("INFO", "itemswipe")
    }

    override fun onLeftClick(position: Int, viewHolder: RecyclerView.ViewHolder) {
        Log.e("INFO", "leftclick")
    }

    override fun onRightClick(position: Int, viewHolder: RecyclerView.ViewHolder) {
        Log.e("INFO", "rightclick")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemListBinding>(layoutInflater, viewType, parent, false)
        return Holder(binding)
    }

    override fun getItemCount() =
        if (mMainViewModel.itemList.value != null) {
            mMainViewModel.itemList.value!!.size
        } else 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(mMainViewModel, position)
    }

    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition()

    private fun getLayoutIdForPosition(): Int = layoutId

    class Holder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.itemLists) {
        fun bind(mMainViewModel: MainViewModel, position: Int) {
            binding.mainVM = mMainViewModel
            binding.position = position
            binding.executePendingBindings() // 즉시 바인딩
        }
    }
}