package com.example.matopdv;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.ViewHolder> {

    private List<CalculationEntry> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    // data is passed into the constructor
    MyRecyclerView(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public List<CalculationEntry> getmData() {
        return mData;
    }

    public void setmData(List<CalculationEntry> mData) {
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
        CalculationEntry item = mData.get(position);
        holder.tvBaseNumber.setText(String.format("%.2f", item.getFormattedBaseNumber()).replace('.', ','));
        holder.tvVatAmount.setText(String.valueOf(item.getVatAmount()));
        holder.tvdateCreated.setText(item.getFormattedDate());
        holder.tvdateCreated.setTextColor(Color.GRAY);
        holder.tvUkupno1.setText(String.format("%.2f", item.getTotalAmount()).replace('.', ','));
        holder.tvUkupno2.setText(String.format("%.3f", item.getTotalAmount()).replace('.', ','));

        holder.btnBrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculationEntry theRemovedItem = mData.get(position);
                // remove your item from data base
                mData.remove(position);  // remove the item from list
                notifyItemRemoved(position); // notify the adapter about the removed item
                notifyDataSetChanged();
            }
        });
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvBaseNumber;
        TextView tvPlus2;
        TextView tvVatAmount;
        TextView tvJednako2;
        TextView tvUkupno1;
        TextView tvZagrada1;
        TextView tvUkupno2;
        TextView tvZagrada2;
        TextView tvdateCreated;
        Button btnBrisi;


        ViewHolder(View itemView) {
            super(itemView);
            tvBaseNumber = itemView.findViewById(R.id.tvBaseNumber);
            tvPlus2 = itemView.findViewById(R.id.tvPlus2);
            tvVatAmount = itemView.findViewById(R.id.tvVatAmount);
            tvJednako2 = itemView.findViewById(R.id.tvJednako2);
            tvUkupno1 = itemView.findViewById(R.id.tvUkupno1);
            tvUkupno2 = itemView.findViewById(R.id.tvUkupno2);
            tvZagrada1 = itemView.findViewById(R.id.tvZagrada1);
            tvZagrada2 = itemView.findViewById(R.id.tvZagrada2);
            tvdateCreated = itemView.findViewById(R.id.tvDateCreated);
            btnBrisi = itemView.findViewById(R.id.btnBrisi);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    CalculationEntry getItem(int id) {
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