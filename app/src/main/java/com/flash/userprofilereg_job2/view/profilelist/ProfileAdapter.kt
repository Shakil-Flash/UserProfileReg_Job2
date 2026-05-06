package com.flash.userprofilereg_job2.view.profilelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flash.userprofilereg_job2.data.UserProfile
import com.flash.userprofilereg_job2.databinding.ItemListBinding

class ProfileAdapter(
    private val profileList: List<UserProfile>,
    private val onEdit: (UserProfile) -> Unit,
    private val onDelete: (UserProfile) -> Unit,
    private val onClick: (UserProfile) -> Unit
) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val profile = profileList[position]
        holder.binding.tvUserName.text = profile.name
        holder.binding.tvEmail.text = profile.email

        holder.binding.ivEdit.setOnClickListener {
            onEdit(profile)
        }

        holder.binding.ivDelete.setOnClickListener {
            onDelete(profile)
        }

        holder.binding.root.setOnClickListener {
            onClick(profile)
        }
    }

    override fun getItemCount(): Int = profileList.size

}