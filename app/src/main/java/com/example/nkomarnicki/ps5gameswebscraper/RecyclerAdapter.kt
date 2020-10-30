package com.example.nkomarnicki.ps5gameswebscraper


import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyRecyclerAdapter(private val myDataset: GamesScraper.DataContainer) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_card_layout, parent, false) as View
        val lp = view.layoutParams
        lp.height = parent.measuredHeight/3
        view.layoutParams = lp
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.titleTextView).text = myDataset.titles[position]
        holder.view.findViewById<TextView>(R.id.descriptionTextView).text = myDataset.synopsis[position]
        Picasso.get().load(myDataset.images[position]).into(holder.view.findViewById<ImageView>(R.id.imageView))
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.titles.count()
}