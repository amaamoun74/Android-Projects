package com.example.healthapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.R;
import com.example.healthapp.UI.fragment.MedicalDataFragment;
import com.example.healthapp.model.Data;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Data> ArrayList = new ArrayList<>();

    Context context;
    List<MedicalDataFragment> list;

  /**  public DataAdapter (Context context, List<MedicalDataFragment> list){
        this.context = context;
        this.list = list;
    }*/

    public DataAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_info_list, parent, false));
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       // holder.titleTextView.setText(list.get(position).title);
       // holder.descriptionTextView.setText(list.get(position).description);
        holder.titleTextView.setText("title1");
        holder.descriptionTextView.setText("Description here");
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }

    public void setList(ArrayList<Data> ArrayList) {
        this.ArrayList = ArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
