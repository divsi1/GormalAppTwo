package com.example.gormalapptwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
implements FetchDataCallbackInterface{

    private ArrayList<Book> mBookList = new ArrayList<Book>();
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rcy_books);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        callToApi("http://206.189.128.26/api/getAllAvailableBooks");

    }

    public void callToApi(String url) {
//        VolleyLog.DEBUG = true;

        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        //this token expires!!!please enter the latest valid token from wensite link
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //success
                        MainActivity.this.fetchDataCallback(response);
                        Log.d("string url response", response);
                        //do add to db
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle error response
                        Log.d("error response", error.toString());

                    }
                }
        );

        queue.add(req);
    }

    @Override
    public void fetchDataCallback(String result) {
        try {
            JSONObject myResponse = new JSONObject(result);
            Log.d("divya", result);

            JSONArray responseArray = myResponse.getJSONArray("results");
            for (int i = 0; i < responseArray.length(); i++) {
                                    Book book = new Book();
//                                user.setId(responseArray.getJSONObject(i).optInt("id"));
                                    book.setBookName(responseArray.getJSONObject(i).optString("book_name"));
                                    book.setBookDesc(responseArray.getJSONObject(i).optString("book_desc"));
                                    book.setBookAuthor(responseArray.getJSONObject(i).optString("book_author"));
                                    book.setBookPrice(responseArray.getJSONObject(i).optString("book_price"));
                book.setBookImgUrl(responseArray.getJSONObject(i).optString("book_img_url"));

                mBookList.add(book);
            }
            BookAdapter adapter = new BookAdapter( mBookList,MainActivity.this);
            mRecyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
