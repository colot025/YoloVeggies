package com.egreksystems.yoloveggies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Fruit> mData;
    public FruitAdapter(Context context, ArrayList<Fruit> data){
        mContext = context;
        mData = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView fruitImage;
        public TextView fruitName, score;
        public ViewHolder(View itemView){
            super(itemView);

            fruitName = (TextView)itemView.findViewById(R.id.fruit_name);
            score = (TextView)itemView.findViewById(R.id.score);
            fruitImage = (ImageView)itemView.findViewById(R.id.fruit_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

//            Intent i = new Intent(view.getContext(), ListActivity.class);
//            //Change the activity.
//            i.putExtra(EXTRA_ADDRESS, mData.get(getAdapterPosition()).getDeviceAddress()); //this will be received at ledControl (class) Activity
//            mContext.startActivity(i);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row,parent,false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit f = mData.get(position);
        holder.fruitName.setText(f.getFruit());
        holder.score.setText(f.getScore());
        int resId = mContext.getResources().getIdentifier(f.getImage(),"drawable",mContext.getPackageName());
        holder.fruitImage.setImageResource(resId);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
