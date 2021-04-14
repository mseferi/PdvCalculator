package com.example.matopdv.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.matopdv.entity.CalculationEntry;
import com.example.matopdv.R;

import java.util.List;

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.ViewHolder> {

    private List<CalculationEntry> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    // data is passed into the constructor
    public MyRecyclerView(Context context) {
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
        holder.tvBaseNumber.setText(item.getFormattedBaseNumber());
        holder.tvVatAmount2.setText(String.valueOf(item.getVatAmount()));
        holder.tvdateCreated.setText(item.getFormattedDate());
        holder.tvdateCreated.setTextColor(Color.GRAY);
        holder.tvTotalAmount.setText(item.getTotalAmount(2));
        holder.tvTotalAmount2.setText(item.getTotalAmount(3));

        holder.btnErase.setOnClickListener(new View.OnClickListener() {
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
        TextView tvVatAmount2;
        TextView tvEquals2;
        TextView tvTotalAmount;
        TextView tvBrackets1;
        TextView tvTotalAmount2;
        TextView tvBrackets2;
        TextView tvdateCreated;
        Button btnErase;


        ViewHolder(View itemView) {
            super(itemView);
            tvBaseNumber = itemView.findViewById(R.id.tvBaseNumber);
            tvPlus2 = itemView.findViewById(R.id.tvPlus2);
            tvVatAmount2 = itemView.findViewById(R.id.tvVatAmount2);
            tvEquals2 = itemView.findViewById(R.id.tvEquals2);
            tvTotalAmount = itemView.findViewById(R.id.tvTotalAmount);
            tvTotalAmount2 = itemView.findViewById(R.id.tvTotalAmount2);
            tvBrackets1 = itemView.findViewById(R.id.tvBrackets1);
            tvBrackets2 = itemView.findViewById(R.id.tvBrackets2);
            tvdateCreated = itemView.findViewById(R.id.tvDateCreated);
            btnErase = itemView.findViewById(R.id.btnErase);
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