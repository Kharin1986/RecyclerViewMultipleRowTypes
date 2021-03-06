package ru.project.gbrecyclertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MultipleTypesAdapter adapter;
    List<RowType> items = new ArrayList<>();
    Random rnd = new Random(1337);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        //generate data
        for (int i = 0; i < 15; i++) {
            switch (rnd.nextInt(2)) {
                case 0 :
                    items.add(new DayNumberType("День "+i));
                    break;
                case 1 :
                    items.add(new RouteObjectType("Время прохождения "+ i + ":25","Пушкинский музей",this));
                    break;
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MultipleTypesAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}