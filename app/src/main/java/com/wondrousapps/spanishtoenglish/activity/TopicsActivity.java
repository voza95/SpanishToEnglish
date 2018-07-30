package com.wondrousapps.spanishtoenglish.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wondrousapps.spanishtoenglish.R;
import com.wondrousapps.spanishtoenglish.adapter.TopicsAdapter;
import com.wondrousapps.spanishtoenglish.listener.RecyclerItemClickListener;
import com.wondrousapps.spanishtoenglish.model.Topics;

import java.util.ArrayList;
import java.util.List;

public class TopicsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Topics> topicsList;
    String lastWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        recyclerView=findViewById(R.id.rv_topics);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topicsList=new ArrayList<>();
//á, é, í, ó, and ú or the rare dieresis,
// ü.¡,Señor,Señora,ñ,Ñ,á, é,
// í, ó, ú, ü, ñ, ¿, ¡
        topicsList.add(new Topics(1,"Expresión"));
        topicsList.add(new Topics(2,"Expresión - Revisión"));
        topicsList.add(new Topics(3,"Modo de Portarse"));
        topicsList.add(new Topics(4,"Modo de Portarse - Revisión"));
        topicsList.add(new Topics(5,"Entrevista"));
        topicsList.add(new Topics(6,"Entrevista - Revisión"));
        topicsList.add(new Topics(7,"Escenarios"));
        topicsList.add(new Topics(8,"Escenarios - Revisión"));
        topicsList.add(new Topics(9,"Frases Verbales"));
        topicsList.add(new Topics(10,"Frases Verbales - Revisión"));
        topicsList.add(new Topics(11,"Coloquial"));
        topicsList.add(new Topics(12,"Coloquial - Revisión"));
        topicsList.add(new Topics(13,"Completar - Revisión"));

        TopicsAdapter adapter=new TopicsAdapter(this,topicsList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
               new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Topics topics=topicsList.get(position);
                    lastWord = topics.getTopic().substring(topics.getTopic().lastIndexOf(" ")+1);
                    Log.d("lastword",lastWord);
                    Log.d("lastword",lastWord.length()+"");
               if (lastWord.endsWith("Revisión")){
                    Intent intent=new Intent(getApplicationContext(),QuizActivity.class);
                    Log.d("lastword",lastWord);
                    Toast.makeText(getApplicationContext(),"Revisión",Toast.LENGTH_SHORT).show();
                    intent.putExtra("clicked_topic",topics.getTopic());
                    startActivity(intent);
               }else {
                    Intent intent=new Intent(getApplicationContext(),DetailListActivity.class);
                    intent.putExtra("clicked_topic",topics.getTopic());
                    startActivity(intent);
                 }
               }
            })
        );
    }
}