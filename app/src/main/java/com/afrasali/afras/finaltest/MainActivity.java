package com.afrasali.afras.finaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

import static com.afrasali.afras.finaltest.R.id.rank;

public class MainActivity extends AppCompatActivity {

    EditText e1, e2;
    String username, password;
    JSONObject jsobj;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }






    public void lClick(View view) {
        //Toast.makeText(this, "hiii", Toast.LENGTH_SHORT).show();
        e1 = (EditText) findViewById(R.id.getMobile);
        e2 = (EditText) findViewById(R.id.getPassword);
        t=(TextView)findViewById(R.id.text);
        username = e1.getText().toString();
        password = e2.getText().toString();
          if(username.isEmpty()||password.isEmpty()){

            t.setText("Invalid Crediantials");
        }
        String LoginUrl = " http://10.0.2.2/newDb/final.php?mobile=" + username + "&&password=" + password;
        JsonArrayRequest j=new JsonArrayRequest(LoginUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0;i<response.length();i++){

                    try {
                        jsobj=(JSONObject) response.get(i);
                        if (jsobj.getString("status").equals("200")){
                            Intent in=new Intent(MainActivity.this,NewsFeed.class);
                            startActivity(in);
                            Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                           // Toast.makeText(MainActivity.this, jsobj.getString("Msg"), Toast.LENGTH_SHORT).show();
                        }
                        else if(jsobj.getString("status").equals("500")){
                            t.setText("Invalid Crediantials");
                              Toast.makeText(MainActivity.this, jsobj.getString("Msg"), Toast.LENGTH_SHORT).show();
                        }
                        else if(jsobj.getString("status").equals("404")){
                            t.setText("Invalid Crediantials");

                            //  Toast.makeText(MainActivity.this, jsobj.getString("Msg"), Toast.LENGTH_SHORT).show();
                        }




                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue req= Volley.newRequestQueue(this);
        req.add(j);

    }





}
