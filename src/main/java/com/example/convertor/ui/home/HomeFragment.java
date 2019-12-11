package com.example.convertor.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.convertor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private FirebaseDatabase FDB;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    DatabaseReference UserRef;
    String UID;
    RatingBar Rb;

    private Button RateButton;
    private static String Tag = "Home Fragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        FDB = FirebaseDatabase.getInstance();
        DatabaseReference myRef = FDB.getReference("Users");


        UID = user.getUid();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FindData(dataSnapshot);
                TextView RateTv = root.findViewById(R.id.RateTextView);
                int i = 0;
                int c = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.hasChild("Rating")){
                        String s = ds.child("Rating").getValue().toString();
                        i += Integer.parseInt(s);
                        c++;
                    }

                }
                float f = i/c;
                RateTv.setText("We have an average rating of " + f+ " stars");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        RateButton = root.findViewById(R.id.RatingButton);
        Rb = root.findViewById(R.id.ratingBar);

        RateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRef.child("Rating").setValue(Rb.getRating());
            }
        });


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    private void FindData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            if(ds.child("UID").getValue().equals(UID)){
                UserRef = ds.getRef();
                Log.d(Tag, "User Reference now equals data snapshot instance.");
                break;
            }
            else{
                Log.d(Tag, "User reference was not saved as ds reference");
            }
        }

    }
}