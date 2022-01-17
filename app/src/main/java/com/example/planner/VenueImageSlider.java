package com.example.planner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import com.example.planner.imageHelper;

public class VenueImageSlider extends AppCompatActivity {
    FirebaseDatabase fireData;
    DatabaseReference dataRefre;
    //String url1, url2,url3;
    String url1; //= "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png";
    String url2;//= "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp";
    String url3; //= "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png";
    String ID;
    //url must be replaced with the images data in the form if string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireData = FirebaseDatabase.getInstance();
        dataRefre = FirebaseDatabase.getInstance().getReference("venue");
         ID = "1";




        dataRefre.child(ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.getResult().exists()){
                    DataSnapshot dataSnapshot = task.getResult();
                    url1 = String.valueOf(dataSnapshot.child("image").getValue());
                    url2 = String.valueOf(dataSnapshot.child("image1").getValue());
                    url3 = String.valueOf(dataSnapshot.child("image2").getValue());



                }
            }
        });

        setContentView(R.layout.activity_venue_image_slider);
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);

        //firebase codes

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));


        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();
    }
}