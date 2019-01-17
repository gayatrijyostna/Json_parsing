package com.example.sys.json_parsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Listdata> listdata;
    private CustomAdapter adapter;
    private ListView lv;
    private ProgressDialog pDialog;
    Context context;
    public  String url = "https://interview-e18de.firebaseio.com/media.json?print=pretty";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
      lv =findViewById(R.id.listview);
        new GetContacts().execute();


    }
    class GetContacts extends AsyncTask<Void, Void, Void>

    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog( MainActivity.this );
            pDialog.setMessage( "Please wait..." );
            pDialog.setCancelable( false );
            pDialog.show();


        }
        @Override
        protected Void doInBackground(Void... voids) {
            Http_handler sh = new Http_handler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String des = jsonObject.getString("description");
                        String url = jsonObject.getString("url");
                        String title = jsonObject.getString("title");
                        Log.e( "des", des);
                        Listdata data = new Listdata(title,url,des);
                        Log.e("Data",data.toString());
                        listdata.add(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            adapter = new CustomAdapter(getApplicationContext(), R.layout.layout, listdata);
            lv.setAdapter(adapter);
        }
    }
}
