package com.hfkorkmaz.kuizzer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfkorkmaz.kuizzer.R
import com.hfkorkmaz.kuizzer.models.Option


class QuizOptionsAdapter(
    private val optionsArray: ArrayList<Option>,
    var optionClickListener: OnQuizOptionsListener?
) : RecyclerView.Adapter<QuizOptionsAdapter.QuizOptionHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuizOptionHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.option_row, parent, false)
        return QuizOptionHolder(view, optionClickListener)

    }

    override fun getItemCount(): Int {
        return optionsArray.size

    }


    override fun onBindViewHolder(holder: QuizOptionHolder, position: Int) {
        holder.optionText?.text = optionsArray[position].text


    }


    class QuizOptionHolder(view: View, onOptionClickListener: OnQuizOptionsListener?) :
        RecyclerView.ViewHolder(view) {


        var optionText: TextView? = null

        init {
            optionText = view.findViewById(R.id.option_text);
            optionText?.setOnClickListener {
                onOptionClickListener?.onQuizOptionClick(
                    adapterPosition
                )
            }
        }


    }

    interface OnQuizOptionsListener {
        fun onQuizOptionClick(adapterPosition: Int)
    }

}