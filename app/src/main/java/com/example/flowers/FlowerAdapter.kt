import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowers.DetailsScreen
import com.example.flowers.R


class FlowerAdapter(private val flowers: List<Flower>) : RecyclerView.Adapter<FlowerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val flowerItemView = LayoutInflater.from(parent.context).inflate(R.layout.flower_item, parent, false)
        return ViewHolder(flowerItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flower = flowers[position]
        holder.nameTextView.text = flower.name
        Glide.with(holder.itemView.context).load(flower.imageUrl).into(holder.imageView)

        // Set an OnClickListener for the entire item view
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailsScreen::class.java)
            intent.putExtra(FLOWER_EXTRA_KEY, flower as Parcelable) // Cast to Parcelable
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return flowers.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
    }

    private val FLOWER_EXTRA_KEY = "flower"
}