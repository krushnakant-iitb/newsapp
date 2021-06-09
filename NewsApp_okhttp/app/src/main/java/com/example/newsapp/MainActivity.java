package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;



public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Item> items;
    Adapter adapter;
    OkHttpClient client;

    private final String URL = "https://cs251-outlab-6.herokuapp.com/initial_values/";
    private final String URL2 = "http://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=b9a058a0ebb3479aba68fcae9757454e";
    private final String URL3 = "http://newsapi.org/v2/everything?q=bitcoin&from=2020-11-14&sortBy=publishedAt&apiKey=e3be6e6d7c914719a048b95ef37807f4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.itemslist);
        items = new ArrayList<>();

        client = new OkHttpClient();

        //extract();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new Adapter(getApplicationContext(), items);
        recyclerView.setAdapter(adapter);

        set_initial();

        do_this();

        set_initial();

        adapter.notifyDataSetChanged();

        Log.d("DEBUG_DEBUG_DEBUG", "\nDid this\n\n\n\n");
        String k = String.valueOf(items.size());
        Log.d("DEBUG_DEBUG_DEBUG", k);
        for (int j = 0; j < items.size(); j++) {
            Log.d("DEBUG_DEBUG_DEBUG", items.get(j).title.toString());
        }

        Log.d("DEBUG_DEBUG_DEBUG", "\nS\nS\nS\nS\nS\n");

    }

    private void do_this() {

        ArrayList<Item> mylist=new ArrayList<>();

        okhttp3.Request request = new okhttp3.Request.Builder().url(URL3).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myresponse = response.body().string();
                    Log.d("DEBUG_DEBUG_DEBUG", "response");

                    try {
                        json_thing(myresponse);

                        {
                            JSONObject obj = new JSONObject(myresponse);
                            JSONArray list_ = obj.getJSONArray("articles");
                            for (int i = 0; i < list_.length(); i++) {
                                Item item = new Item();
                                JSONObject Q = list_.getJSONObject(i);
                                item.title = Q.getString("title");
                                item.description = Q.getString("description");

                                mylist.add(item);


                                //adapter.Adapter_change(items);
                                //recyclerView.setAdapter(adapter);

                                //recyclerView.getAdapter().notifyDataSetChanged();
                                //adapter.notifyDataSetChanged();
                                //adapter.notifyItemInserted(i+3);
                                Log.d("DEBUG_DEBUG_DEBUG", String.valueOf(mylist.size()));
                            }
                        }

                    } catch (JSONException e) {
                        Log.d("DEBUG_DEBUG_DEBUG", e.toString());
                    }
                }
            }
        });

        Item itemk = new Item();
        itemk.title = "Hii";
        itemk.description = "Yesssssssssssssss";
        ArrayList<Item> PQR= new ArrayList<>(items);
        items.add(itemk);
        Log.d("DEBUG_DEBUG_DEBUG", "itemk added");
        //Log.d("DEBUG_DEBUG_DEBUG", mylist.get(2).title);
        items.addAll(mylist);
        Log.d("DEBUG_DEBUG_DEBUG", String.valueOf(items.size()));

    }

    private void json_thing(String myresponse) throws JSONException {
        Log.d("DEBUG_DEBUG_DEBUG", "Beta\nk");
        JSONObject obj = new JSONObject(myresponse);
        JSONArray list_ = obj.getJSONArray("articles");
        for (int i = 0; i < list_.length(); i++) {
            Item item = new Item();
            JSONObject Q = list_.getJSONObject(i);
            item.title = Q.getString("title");
            item.description = Q.getString("description");

            items.add(item);

            Item itemk = new Item();
            itemk.title = "Hii";
            itemk.description = "Yess";

            items.add(itemk);

            //adapter.Adapter_change(items);
            //recyclerView.setAdapter(adapter);

            //recyclerView.getAdapter().notifyDataSetChanged();
            //adapter.notifyDataSetChanged();
            //adapter.notifyItemInserted(i+3);
            Log.d("DEBUG_DEBUG_DEBUG", String.valueOf(items.size()));
        }

        Log.d("DEBUG_DEBUG_DEBUG", String.valueOf(items.size()));
        Item itemk = new Item();
        itemk.title = "Hii";
        itemk.description = "Yess";

        items.add(itemk);
        Log.d("DEBUG_DEBUG_DEBUG", String.valueOf(items.size()));
    }

    private void set_initial() {
        Item item = new Item();
        item.title = "Hii";
        item.description = "Yess";

        items.add(item);
        items.add(item);

        Item item2 = new Item();
        item2.title = "Hii";
        item2.description = "Yesss";

        items.add(item2);
    }
}