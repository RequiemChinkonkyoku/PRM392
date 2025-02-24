package com.admin.babyvaccinationtracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.admin.babyvaccinationtracker.Adapter.ManagerUserViewPageAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_manage_user_upgrade#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_manage_user_upgrade extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TabLayout mTablayout;
    private ViewPager mViewPager;

    ManagerUserViewPageAdapter adapter;

    public Fragment_manage_user_upgrade() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_manage_user_upgrade.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_manage_user_upgrade newInstance(String param1, String param2) {
        Fragment_manage_user_upgrade fragment = new Fragment_manage_user_upgrade();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_manage_user_upgrade, container, false);
        mTablayout = view.findViewById(R.id.ManagerTabLayout);
        mViewPager = view.findViewById(R.id.ManageviewPager);

        adapter = new ManagerUserViewPageAdapter(requireActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(adapter);

        mTablayout.setupWithViewPager(mViewPager);

        return view ;
    }
}