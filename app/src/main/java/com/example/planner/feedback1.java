package com.example.planner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.planner.activity_collectdetails;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feedback1 extends AppCompatActivity {
    RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    Button b1;
    FirebaseDatabase fd;
    DatabaseReference ar;
    DatabaseReference ref;
    EditText e1,e2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        rb1 = findViewById(R.id.not_satisfied);
        e1 = findViewById(R.id.takeaway);
        e2 = findViewById(R.id.suggestions);
        rb2 = findViewById(R.id.good);
        rb3 = findViewById(R.id.very_good);
        rb4 = findViewById(R.id.depends);
        rb5 = findViewById(R.id.later);
        rb6 = findViewById(R.id.likely);

        b1 = findViewById(R.id.submit_button);
        final String[] satisfied = new String[1];
        final String[] futureEvents = new String[1];
        fd = FirebaseDatabase.getInstance();
        ar = fd.getReference().child("feedback");

        String suggestions = e1.getText().toString();
        String takeAway = e2.getText().toString();

        /*ref = fd.getReference().child("users.phNumber");
        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                feedback1 post = dataSnapshot.getValue(feedback1.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb1.isChecked()) {
                    satisfied[0] = rb1.getText().toString();
                } else if (rb2.isChecked()) {
                    satisfied[0] = rb2.getText().toString();
                } else {
                    satisfied[0] = rb3.getText().toString();
                }

                if (rb1.isChecked()) {
                    futureEvents[0] = rb4.getText().toString();
                } else if (rb2.isChecked()) {
                    futureEvents[0] = rb5.getText().toString();
                } else {
                    futureEvents[0] = rb6.getText().toString();
                }

                String sat = satisfied[0];
                String future = futureEvents[0];
                //ar.child("Satisfaction").setValue(satisfied[0]);
                //ar.child("future event").setValue(futureEvents[0]);
                //System.out.println("hey!" + satisfied[0].toString() + " " + futureEvents[0]+toString());
                feedbackDatabase fb = new feedbackDatabase(sat,future,suggestions,takeAway);
                ar.child("Feedback").setValue(fb);
            }
        });

        /*
        final String[] satisfied = new String[1];



                //rbg1.setOnClickListener(new View.OnClickListener() {
                //  @Override
                // public void onClick(View v) {
                if (rb1.isChecked()){
                    satisfied[0] = rb1.getText().toString();
                }else if (rb2.isChecked()){
                    satisfied[0] = rb2.getText().toString();
                }else{
                    satisfied[0] = rb3.getText().toString();
                }
                //    }
                //});

        /*rbg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if (rb1.isChecked()){
            futureEvents[0] = rb4.getText().toString();
        }else if (rb2.isChecked()){
            futureEvents[0] = rb5.getText().toString();
        }else{
            futureEvents[0] = rb6.getText().toString();
        }
        //    }
        //});




        */
        //String resultText = satisfied[0] + futureEvents[0];

        /*public void onRadioButtonClicked(View view) {
            // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();

            // Check which radio button was clicked
            switch(view.getId()) {
                case R.id.radio_pirates:
                    if (checked)
                        // Pirates are the best
                        break;
                case R.id.radio_ninjas:
                    if (checked)
                        // Ninjas rule
                        break;
            }
        }
        */


    }

}


