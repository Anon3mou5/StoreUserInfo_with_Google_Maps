package com.example.task;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {


    boolean isloaded=false;
    private static final String ARG_SECTION_NUMBER = "section_number";
    List<data> list = new ArrayList<data>() ;
    ApiInterface apiInterface;
    adaptermodel jz;
    private PageViewModel pageViewModel;

//                            JSONObject j = response;
//                          //  Toast.makeText(getContext(),response.toString(),Toast.LENGTH_LONG).show();;
//                            JSONArray jsonarray = null;
//                            try {
//                                jsonarray = j.getJSONArray("articles");
//                                for (int i = 0; i < jsonarray.length(); i++) {
//                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
//                                    String heading = jsonobject.getString("title");
//                                    String url = jsonobject.getString("urlToImage");
//                                    String title = jsonobject.getString("description");
//                                    String website = jsonobject.getString("url");
//                                    String timings = jsonobject.getString("publishedAt");
//                                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                                    DateFormat Format = new SimpleDateFormat("HH:mm:ss");
//                                    String date = timings.substring(0,10);
//                                    String time = timings.substring(11,19);
//                                    data model = new data(url, title, time, date, heading, website);
//                                    if (url != null) {
//                                        list.add(model);
//                              //          Toast.makeText(getContext(),"added",Toast.LENGTH_LONG).show();
//                                        Log.d("DATA",model.getHeading()+ "    "+ model.getThumbnail());
//                                        jz.notifyDataSetChanged();
//                                    }
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            isloaded=true;
//                        }
//                    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.image_viewer, container, false);
        RecyclerView recycle = v.findViewById(R.id.recycle);
        LinearLayoutManager lm = new LinearLayoutManager(v.getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        getTodos(getContext(),recycle,lm);
        return  v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }


    public void getTodos(final Context c, final RecyclerView recyclerView, final RecyclerView.LayoutManager lm){

        Call<List<data>> call = apiInterface.getTodos();
        call.enqueue(new Callback<List<data>>() {
            @Override
            public void onResponse(Call<List<data>> call, Response<List<data>> response) {
               // Log.e("ANSWER", "onResponse: " +  response[0].thumbnail);
                Log.d("ANSWER"," onResponse: " + (response.body()).get(0).title+"\n" + (response.body()).get(0).getThumbnail());
                 list = response.body();
                 jz = new adaptermodel(list);
                 recyclerView.setAdapter(jz);
                 recyclerView.setLayoutManager(lm);
                  jz.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<data>> call, Throwable t) {
          //      Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });



    }

    public void getTodoUsingRouteParameter(View view) {

        Call<data> todoCall = apiInterface.getTodo(3);
        todoCall.enqueue(new Callback<data>() {
            @Override
            public void onResponse(Call<data> call, Response<data> response) {

            }

            @Override
            public void onFailure(Call<data> call, Throwable t) {

            }
        });

    }


    public void getTodosUsingQuery(View view) {

        Call<List<data>> listCall = apiInterface.getTodosUsingQuery(3, false);
        listCall.enqueue(new Callback<List<data>>() {

            @Override
            public void onResponse(Call<List<data>> call, Response<List<data>> response) {

            }

            @Override
            public void onFailure(Call<List<data>> call, Throwable t) {

            }
        });

    }
}