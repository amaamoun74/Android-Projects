package com.example.healthapp.UI.fragment.data;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.example.healthapp.R;
import com.example.healthapp.adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class profileFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentManager fragmentManager;
    FragmentAdapter fragmentAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        addViewPagerChildFragment(view);
        return view;
    }

    private void addViewPagerChildFragment(View view) {
        viewPager = view.findViewById(R.id.schedulepager);
        tabLayout = view.findViewById(R.id.tablayout);
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new MedicalDataFragment(),"Medical");
        adapter.addFragment(new PersonalDataFragment(),"Personal");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}