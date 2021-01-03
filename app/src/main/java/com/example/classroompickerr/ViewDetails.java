package com.example.classroompickerr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ViewDetails extends AppCompatActivity {
    FirebaseDatabase fDB;
    TextView name,description,id;
    DatabaseReference myRef;
    ArrayAdapter<String> adapter;
    String TAG="FIREBASE";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getRoomDetail();
        id = findViewById(R.id.tvId);
        name = findViewById(R.id.tv_name);
        description = findViewById(R.id.tv_description);
        }
        private void getRoomDetail(){
        Intent intent = getIntent();
        final String key = intent.getStringExtra("KEY");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Room");
        myRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              try {
                  HashMap<String,Object> hashMap = (HashMap<String, Object>)dataSnapshot.getValue();
                  id.setText(key);
                  name.setText(hashMap.get("Name").toString());
                  description.setText(hashMap.get("Description").toString());
              } catch (Exception e) {
                  Log.d("LOI_JSON",e.toString());
              }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Error", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    }
