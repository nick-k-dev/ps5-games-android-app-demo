package com.example.nkomarnicki.ps5gameswebscraper


import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class MyRecyclerAdapter(private val myDataset: GamesScraper.DataContainer) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_card_layout, parent, false) as View
        val lp = view.layoutParams
        lp.height = (parent.measuredHeight / 1.5).roundToInt()
        view.layoutParams = lp
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.titleTextView).text = myDataset.titles[position]
        holder.view.findViewById<TextView>(R.id.descriptionTextView).text = myDataset.synopsis[position]
        Picasso.get().load(myDataset.images[position]).into(holder.view.findViewById<ImageView>(R.id.imageView))
        holder.view.findViewById<ImageView>(R.id.imageView).contentDescription = myDataset.titles[position] + " screenshot"
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.titles.count()

    //Custom decorator in order to be able to change spacing on recycler item
    class MyItemDecorator : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            @NonNull outRect: Rect,
            @NonNull view: View,
            @NonNull parent: RecyclerView,
            @NonNull state: RecyclerView.State
        ): Unit {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = 50
            outRect.bottom = 50
        }
    }
}