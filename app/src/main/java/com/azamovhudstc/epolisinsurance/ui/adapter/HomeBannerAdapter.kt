import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.HomeBanner
import kotlinx.android.synthetic.main.home_banner_item.view.*

class HomeBannerAdapter(private val bannerList: ArrayList<HomeBanner>) :
    RecyclerView.Adapter<HomeBannerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(homeBanner: HomeBanner) {
            itemView.home_banner_img.setImageResource(homeBanner.image.toInt())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_banner_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(bannerList[position])
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }
}