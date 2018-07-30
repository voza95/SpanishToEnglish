package com.wondrousapps.spanishtoenglish.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wondrousapps.spanishtoenglish.R;

public class QuizActivity extends AppCompatActivity {

    TextView opta_tv,optb_tv,optc_tv,que_tv;

    int points=0;
    String ans;
    int i=1;
    String clicked_topic;
    FirebaseFirestore db;
    DocumentReference SpanToEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState == null){
            Bundle bundle= getIntent().getExtras();
            if (bundle==null){
                clicked_topic=null;
            }else {
                clicked_topic=bundle.getString("clicked_topic");
            }
        }else {
            clicked_topic= (String) savedInstanceState.getSerializable("clicked_topic");
        }
        db=FirebaseFirestore.getInstance();
        SpanToEng=db.collection("SpanishToEnglish").document(clicked_topic);

        que_tv=findViewById(R.id.que_tv);

        opta_tv=findViewById(R.id.opta_tv);
        optb_tv=findViewById(R.id.optb_tv);
        optc_tv=findViewById(R.id.optc_tv);

        MyMethod(i);

        opta_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("opta",opta_tv.getText().toString());
                checkAnswer(opta_tv.getText().toString(),ans,i);
            }
        });
        optb_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(optb_tv.getText().toString(),ans.toString(),i);
            }
        });
        optc_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(optc_tv.getText().toString(),ans.toString(),i);
            }
        });
    }

    public void MyMethod(final int i){
        if (i<5){
            SpanToEng.collection("Questions").document(i+"").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            final DocumentSnapshot doc = task.getResult();

                            que_tv.setText(doc.get("Question").toString());
                            opta_tv.setText(doc.get("OptionA").toString());
                            optb_tv.setText(doc.get("OptionB").toString());
                            optc_tv.setText(doc.get("OptionC").toString());
                            ans=doc.get("Answer").toString();
                            opta_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.d("opta",opta_tv.getText().toString());
                                    checkAnswer(opta_tv.getText().toString(),ans,i);
                                }
                            });
                            optb_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    checkAnswer(optb_tv.getText().toString(),ans,i);
                                }
                            });
                            optc_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    checkAnswer(optc_tv.getText().toString(),ans,i);
                                }
                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        }else {
            Toast.makeText(getApplicationContext(),i+"",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }
    public void checkAnswer(String clicked,String answer,int i){
            if (clicked.equals(answer)){
                Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                points+=10;
                }else {
                Toast.makeText(getApplicationContext(),answer,Toast.LENGTH_SHORT).show();
            }
        i+=1;
        MyMethod(i);
    }
}