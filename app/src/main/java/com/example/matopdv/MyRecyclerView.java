package com.example.matopdv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.ViewHolder> {

    private List<ListItem> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerView(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public List<ListItem> getmData() {
        return mData;
    }

    public void setmData(List<ListItem> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem item = mData.get(position);
        holder.tvBroj2.setText(item.getPlus2());
        holder.tvPlus2.setText(item.getPlus2());
        holder.tvPdv2.setText(item.getPdv2());
        holder.tvJednako2.setText(item.getJednako2());
        holder.tvUkupno1.setText(item.getUkupno1());
        holder.tvZagrada1.setText(item.getZagrada1());
        holder.tvUkupno2.setText(item.getUkupno2());
        holder.tvZagrada2.setText(item.getZagrada2());
        holder.tvDatum.setText(item.getTvDatum());



    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvBroj2;
        TextView tvPlus2;
        TextView tvPdv2;
        TextView tvJednako2;
        TextView tvUkupno1;
        TextView tvZagrada1;
        TextView tvUkupno2;
        TextView tvZagrada2;
        TextView tvDatum;
        Button btnBrisi;


        ViewHolder(View itemView) {
            super(itemView);
            tvBroj2 = itemView.findViewById(R.id.tvBroj2);
            tvPlus2 = itemView.findViewById(R.id.tvPlus2);
            tvPdv2 = itemView.findViewById(R.id.tvPdv2);
            tvJednako2 = itemView.findViewById(R.id.tvJednako2);
            tvUkupno1 = itemView.findViewById(R.id.tvUkupno1);
            tvUkupno2 = itemView.findViewById(R.id.tvUkupno2);
            tvZagrada1 = itemView.findViewById(R.id.tvZagrada1);
            tvZagrada2 = itemView.findViewById(R.id.tvZagrada2);
            tvDatum = itemView.findViewById(R.id.tvDatum);
            btnBrisi = itemView.findViewById(R.id.btnBrisi);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    ListItem getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}