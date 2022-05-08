package com.example.healthapp.UI.fragment.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.healthapp.R;
import com.example.healthapp.adapter.DataAdapter;

public class MedicalDataFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical_data, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new DataAdapter());
        return view;

        /**String title_items[] = getResources().getStringArray(R.array.title);
        String descriptionforAll= getResources().getString(R.string.description);
        for(int i=0; i<title.length();i++){
            list.add(title);

        }*/
        //adapter = new DataAdapter(this,list);
    }
}