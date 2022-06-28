package com.example.healthapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.R;
import com.example.healthapp.model.Diseases;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    Context context;
    List<Diseases> list;

   public DataAdapter (Context context, List<Diseases> list){
        this.context = context;
        this.list = list;
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
        //hn8ier f l class name yb2 3 7sb l model w tb3n hn8ier f l get title w l klam dh

        Diseases diseases = list.get(position);
       holder.diseasesTextView.setText(diseases.getDiseases());
        holder.descriptionTextView.setText(diseases.getDescription());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // n3ml dialogue 3shan deleting l user medical data
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
/*
    public void setList(ArrayList<Data> ArrayList) {
        this.ArrayList = ArrayList;
        notifyDataSetChanged();
    }
*/
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView diseasesTextView, descriptionTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            diseasesTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
