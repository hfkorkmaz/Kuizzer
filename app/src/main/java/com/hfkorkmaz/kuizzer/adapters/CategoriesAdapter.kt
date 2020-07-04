package com.hfkorkmaz.kuizzer.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfkorkmaz.kuizzer.R
import com.hfkorkmaz.kuizzer.models.Category

class CategoriesAdapter(
    private val categoriesArray: ArrayList<Category>,
    var categoryClickListener: OnCategoryListener?
) : RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_row, parent, false)
        return CategoryHolder(view, categoryClickListener)

    }

    override fun getItemCount(): Int {
        return categoriesArray.size

    }


    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.recyclerCategoryText?.text = categoriesArray[position].name

    }


    class CategoryHolder(view: View, onCategoryListener: OnCategoryListener?) :
        RecyclerView.ViewHolder(view) {


        var recyclerCategoryText: TextView? = null

        init {
            recyclerCategoryText = view.findViewById(R.id.recylerCategoryText);
            recyclerCategoryText?.setOnClickListener {
                onCategoryListener?.onCategoryClick(
                    adapterPosition
                )
            }
        }


    }

    interface OnCategoryListener {
        fun onCategoryClick(adapterPosition: Int)
    }

}