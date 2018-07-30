package com.wondrousapps.spanishtoenglish.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.google.firebase.firestore.Query;
import com.wondrousapps.spanishtoenglish.R;
import com.wondrousapps.spanishtoenglish.adapter.DetailAdapter;
import com.wondrousapps.spanishtoenglish.model.Firebasemodel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DetailListActivity extends AppCompatActivity {
    RecyclerView detailListRV;
    TextView textView;
    String clicked_topic;

    FirebaseFirestore db;
    DocumentReference SpanToEng;
    List<Firebasemodel> firebasemodelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);

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
        Log.d("clicked_topic",clicked_topic);
        db=FirebaseFirestore.getInstance();
        SpanToEng=db.collection("SpanishToEnglish").document(clicked_topic);

        detailListRV=findViewById(R.id.detail_list_rv);
        detailListRV.setHasFixedSize(true);
        detailListRV.setLayoutManager(new LinearLayoutManager(this));

        firebasemodelList=new ArrayList<>();

        textView=findViewById(R.id.detail_topic_tv);
        textView.setText(clicked_topic);

        SpanToEng.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();
                    //    firebasemodelList.add(new Firebasemodel("dsd","dsds"));
//                        String[] span= (String[]) doc.get("spaphrases");
//                        //Arrays.copyOf(objectArray, objectArray.length, String[].class);
//                        String[] eng= (String[]) doc.get("engphrases");

                    ArrayList<Object> spaphrases= (ArrayList<Object>) doc.get("spaphrases");
                    ArrayList<Object> engphrases= (ArrayList<Object>) doc.get("engphrases");
                    for (int i=0;i<engphrases.size();i++){
                        firebasemodelList.add(new Firebasemodel(engphrases.get(i).toString(),spaphrases.get(i).toString()));
                    }
                    DetailAdapter adapter=new DetailAdapter(DetailListActivity.this,firebasemodelList);
                    detailListRV.setAdapter(adapter);
                } else {

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("failed",e+"");
                Toast.makeText(getApplicationContext(),"Failed to load",Toast.LENGTH_SHORT).show();
            }
        });
    }
}