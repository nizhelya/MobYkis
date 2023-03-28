package com.yuzhny.mykis.presentation.appartment.family

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.ItemFamilyListBinding
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.presentation.appartment.util.hideIfEmpty
import com.yuzhny.mykis.presentation.appartment.util.trueOrFalse
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class FamilyListAdapter @Inject constructor() :
    ListAdapter<FamilyEntity, FamilyListAdapter.FamilyViewHolder>(
        DiffCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FamilyListAdapter.FamilyViewHolder(
            ItemFamilyListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        val family = getItem(position)
        holder.binding.apply {
            fullfio.text = "${family.surname} ${family.fistname} ${family.lastname}"
            born.text = family.born
            relationship.text = family.rodstvo
            sex.text = family.sex
            phone.text = family.phone
            inn.text = family.inn
            doc.text = family.document
            seria.text = family.seria
            number.text = family.nomer
            dateIssued.text = family.datav
            issued.text = family.organ
            subsidia.isChecked = trueOrFalse(family.subsidia)
            vkl.isChecked = trueOrFalse(family.vkl)
            expandItem(family , holder)
        }
        holder.binding.cardView.setOnClickListener {
            family.isExpandable = !family.isExpandable
            notifyItemChanged(position, Unit)
        }
    }
    fun expandItem(family: FamilyEntity, holder: FamilyViewHolder) {
        if (family.isExpandable) {
            holder.binding.apply {
                sex.visibility = View.VISIBLE
                sexText.visibility = View.VISIBLE
                born.visibility = View.VISIBLE
                bornText.visibility = View.VISIBLE
                relationship.visibility = View.VISIBLE
                relationshipText.visibility = View.VISIBLE
                phone.visibility = View.VISIBLE
                phoneText.visibility = View.VISIBLE
                subsidia.visibility = View.VISIBLE
                subsidiaText.visibility = View.VISIBLE
                vkl.visibility = View.VISIBLE
                vklText.visibility = View.VISIBLE
                cardText.visibility = View.VISIBLE
                innText.visibility = View.VISIBLE
                inn.visibility = View.VISIBLE
                docText.visibility = View.VISIBLE
                doc.visibility = View.VISIBLE
                seriaText.visibility = View.VISIBLE
                seria.visibility = View.VISIBLE
                number.visibility = View.VISIBLE
                numberText.visibility = View.VISIBLE
                dateText.visibility = View.VISIBLE
                dateIssued.visibility = View.VISIBLE
                issuedText.visibility = View.VISIBLE
                issued.visibility = View.VISIBLE
                cardId.visibility = View.VISIBLE
                viewOpen.setImageResource(R.drawable.ic_expand_less)
            }
        } else {
            holder.binding.apply {
                sex.visibility = View.GONE
                sexText.visibility = View.GONE
                born.visibility = View.GONE
                bornText.visibility = View.GONE
                relationship.visibility = View.GONE
                relationshipText.visibility = View.GONE
                phone.visibility = View.GONE
                phoneText.visibility = View.GONE
                subsidia.visibility = View.GONE
                subsidiaText.visibility = View.GONE
                vklText.visibility = View.GONE
                vkl.visibility = View.GONE
                cardText.visibility = View.GONE
                innText.visibility = View.GONE
                inn.visibility = View.GONE
                docText.visibility = View.GONE
                doc.visibility = View.GONE
                seriaText.visibility = View.GONE
                seria.visibility = View.GONE
                number.visibility = View.GONE
                numberText.visibility = View.GONE
                dateText.visibility = View.GONE
                dateIssued.visibility = View.GONE
                issuedText.visibility = View.GONE
                issued.visibility = View.GONE
                cardId.visibility = View.GONE
                viewOpen.setImageResource(R.drawable.ic_expand_more)
            }
        }

}


    fun clearList(){
        currentList.clear()
    }
    class FamilyViewHolder(var binding: ItemFamilyListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FamilyEntity>() {
        override fun areItemsTheSame(oldItem: FamilyEntity, newItem: FamilyEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FamilyEntity, newItem: FamilyEntity): Boolean {
            return oldItem == newItem
        }

    }
}
