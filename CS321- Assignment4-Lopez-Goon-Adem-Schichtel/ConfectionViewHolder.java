package bakery.cake.bakeryorder.ViewHolder;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import bakery.cake.bakeryorder.ItemClickListener;
import bakery.cake.bakeryorder.R;


/**
 * Created by Kevin Lopez on 4/21/18.
 *
 * A view holder for the Confection
 *
 * Android using Firebase Tutorial 3: https://www.youtube.com/watch?v=k1RUOexThGs
 */
public class ConfectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView confection_name1;
    public ImageView confection_image;
    private ItemClickListener itemClickListener;



    public ConfectionViewHolder(View itemView) {
        super(itemView);

        confection_name1 = (TextView) itemView.findViewById(R.id.confection_name1);
        confection_image =  (ImageView) itemView.findViewById(R.id.confection_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View view) {itemClickListener.onClick(view,getAdapterPosition(),false);}
}
