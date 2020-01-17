package com.juaracoding.ujianandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.ujianandroid.R;
import com.juaracoding.ujianandroid.model.productresult.Product;
import com.squareup.picasso.Picasso;

public class AdapterListItem extends RecyclerView.Adapter<AdapterListItem.ViewHolder> {




    java.util.List<Product> data;
    Context context;

    public AdapterListItem(Context context, java.util.List<Product> data){



        this.data = data;
        this.context = context;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);


        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.txtNamaProduct.setText(data.get(position).getProductName());
        holder.txtPrice.setText(data.get(position).getPrice());
        holder.txtDesc.setText(data.get(position).getDescription());

        String image =  data.get(position).getImage();
        Picasso.get().load(image).into(holder.imgList);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView txtNamaProduct,txtPrice, txtDesc;
        public ImageView imgList;
        public LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNamaProduct = (TextView) itemView.findViewById(R.id.txtNamaProduct);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtDesc = (TextView) itemView.findViewById(R.id.txtDesc);

            imgList = (ImageView)itemView.findViewById(R.id.imgList);
            parentLayout = (LinearLayout)itemView.findViewById(R.id.parentLayout);

        }

    }


}
