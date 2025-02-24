package com.prox.babyvaccinationtracker;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prox.babyvaccinationtracker.adapter.TimeLineAdapter;
import com.prox.babyvaccinationtracker.model.Baby;
import com.prox.babyvaccinationtracker.model.Regimen;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DashboardFragment extends Fragment {

    Context context;
    RecyclerView recyclerViewTimeline;
    TimeLineAdapter timeLineAdapter;
    LinearLayout injectionHistory, familyContainer, commnunity, QRCodeScanner;
    List<Regimen> regimenList = new ArrayList<>();

    LinearLayout babyListContainer;
    ArrayList<Baby> babies = new ArrayList<>();

    String babyId = "";
    String firstBabyId = "";
    TextView textViewGreetings;
    ImageView imageViewAvatar;
    View loadingLayout;


    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = container != null ? container.getContext() : null;
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerViewTimeline = view.findViewById(R.id.recyclerViewTimeline);
        injectionHistory = view.findViewById(R.id.injectionHistory);
        babyListContainer = view.findViewById(R.id.babyListContainer);
        familyContainer = view.findViewById(R.id.familyContainer);
        commnunity = view.findViewById(R.id.community);
        textViewGreetings = view.findViewById(R.id.textViewGreetings);
        imageViewAvatar = view.findViewById(R.id.imageViewAvatar);
        QRCodeScanner = view.findViewById(R.id.QRCodeScanner);
        loadingLayout = view.findViewById(R.id.loadingLayout);

        loadingLayout.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = context.getSharedPreferences("user", MODE_PRIVATE);
        String babiesList = sharedPreferences.getString("babiesList", "");
        String cus_name = sharedPreferences.getString("cus_name", "Trần Công Trí");
        String cus_avatar = sharedPreferences.getString("cus_avatar", "");
        if (!cus_avatar.equals("")){
            String imgaeUrl = cus_avatar.contains("https") ? cus_avatar : cus_avatar.replace("http", "https");
            Picasso.get().load(imgaeUrl).into(imageViewAvatar);
        }
        textViewGreetings.setText(cus_name.split(" ")[cus_name.split(" ").length - 1]);
        String babyID = "";
        try {
            Gson gson = new Gson();
            babies = gson.fromJson(babiesList, new TypeToken<List<Baby>>() {}.getType());
            firstBabyId = babies.get(0).getBaby_id();
            setTimeLine(firstBabyId);
            Log.i("Aaaa", "onCreateView: " + babies.toString());
            for (Baby baby : babies) {
                addButtonForBaby(baby);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        commnunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, community_activity.class));
            }
        });

        injectionHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, InjectionHistoryActivity.class);
                startActivity(i);
            }
        });

        familyContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FamilyActivity.class);
                startActivity(i);
            }
        });

        QRCodeScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, QrScannerActivity.class);
                startActivity(i);
            }
        });

        imageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, UserProfileActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    private void addButtonForBaby(final Baby baby) {
        Button button = new Button(context);
        button.setText(baby.getBaby_name());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        params.setMargins(0, 0, 15, 0);
        button.setLayoutParams(params);
        button.setElevation(0);
        button.setPadding(20, 5, 20, 5);
        button.setHeight(30);
        button.setMinimumHeight(130);
        button.setMinHeight(0);
        button.setStateListAnimator(null);

        if (babyListContainer.getChildCount() == 0) {
            button.setBackground(context.getResources().getDrawable(R.drawable.rounded_primary_button_bg));
            button.setTextColor(context.getResources().getColor(R.color.white));
            babyId = baby.getBaby_id();
            setTimeLine(babyId);
        } else {
            button.setBackground(context.getResources().getDrawable(R.drawable.rounded_white_button_bg));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                babyId = baby.getBaby_id();
                setTimeLine(babyId);
                resetButtonBackgrounds();
                button.setBackground(context.getResources().getDrawable(R.drawable.rounded_primary_button_bg));
                button.setTextColor(context.getResources().getColor(R.color.white));
            }
        });

        babyListContainer.addView(button);
    }

    private void resetButtonBackgrounds() {
        for (int i = 0; i < babyListContainer.getChildCount(); i++) {
            View child = babyListContainer.getChildAt(i);
            if (child instanceof Button) {
                ((Button) child).setBackground(context.getResources().getDrawable(R.drawable.rounded_white_button_bg));
                ((Button) child).setTextColor(context.getResources().getColor(R.color.textColor));
            }
        }
    }

    public void setTimeLine(String baby_id) {
        DatabaseReference vaccinationRegimenReference = FirebaseDatabase.getInstance().getReference("vaccination_regimen").child(baby_id);
        vaccinationRegimenReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                regimenList.clear();
                Date closestDate = null;
                for (DataSnapshot regimenSnapshot : snapshot.getChildren()) {
                    Regimen regimen = regimenSnapshot.getValue(Regimen.class);
                    regimenList.add(regimen);
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                timeLineAdapter = new TimeLineAdapter(context, regimenList);
                linearLayoutManager.scrollToPositionWithOffset(timeLineAdapter.highlightedPosition, 0);
                recyclerViewTimeline.setAdapter(timeLineAdapter);
                recyclerViewTimeline.setLayoutManager(linearLayoutManager);
                Log.i("Home", "onDataChange: " + regimenList.size());
                vaccinationRegimenReference.removeEventListener(this);
                loadingLayout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
     public void onResume() {
        super.onResume();
        Log.i("dashboard", "onResume: ");
        //UPDATE BABY LIST
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", MODE_PRIVATE);
        String babiesList = sharedPreferences.getString("babiesList", "");

        String babyID = "";
        try {
            Gson gson = new Gson();
            babies = gson.fromJson(babiesList, new TypeToken<List<Baby>>() {}.getType());
            firstBabyId = babies.get(0).getBaby_id();
            setTimeLine(firstBabyId);
            Log.i("Aaaa", "onCreateView: " + babies.toString());
            // clear all button
            babyListContainer.removeAllViews();
            for (Baby baby : babies) {
                addButtonForBaby(baby);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}