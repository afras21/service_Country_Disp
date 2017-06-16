package com.afrasali.afras.finaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsFeed extends AppCompatActivity {


    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    List<MyData> data_list;
    CustomAdapter myAdapter;
    RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);


        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        data_list=new ArrayList<MyData>();
        load_data_from_server(0);
        myAdapter=new CustomAdapter(this,data_list);

        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(gridLayoutManager.findLastCompletelyVisibleItemPosition()==data_list.size()-1){
                    //load_data_from_server(data_list.size()-1);
                }}
        });


    }

    private void load_data_from_server(int rank) {

        JsonArrayRequest myReq=new JsonArrayRequest("http://10.0.2.2/newDb/finalRetrieve.php?rank="+rank,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(NewsFeed.this, "hii", Toast.LENGTH_SHORT).show();
                        for (int i=0;i<response.length();i++){
                           // Toast.makeText(NewsFeed.this, "hiii", Toast.LENGTH_SHORT).show();

                            try{
                                JSONObject obj=response.getJSONObject(i);
                                MyData mydata=new MyData(obj.getInt("rank"),obj.getString("country"),obj.getString("population"),obj.getString("flag"));
                                data_list.add(mydata);

                            }catch (JSONException e){
                                e.printStackTrace();
                                Toast.makeText(NewsFeed.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                            myAdapter.notifyDataSetChanged();

                        }}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(NewsFeed.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        rq= Volley.newRequestQueue(this);
        rq.add(myReq);
    }

}
