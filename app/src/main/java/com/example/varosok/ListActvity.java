package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;
import com.google.gson.Gson;
import java.io.IOException;



public class ListActvity extends AppCompatActivity {

    private TextView textViewEredmeny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        textViewEredmeny.setMovementMethod(new ScrollingMovementMethod());

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void init() {
        buttonEdit = findViewById(R.id.buttonEdit);
        textViewEredmeny = findViewById(R.id.textViewEredmeny);
        RequestTask req = new RequestTask(url, "GET");
        req.execute();
    }
    private class RequestTask extends AsyncTask<Void, Void, Response> {
        String requestUrl;
        String requestType;
        String requestParams;

        public RequestTask(String requestUrl, String requestType, String requestParams) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
            this.requestParams = requestParams;
        }

        public RequestTask(String requestUrl, String requestType) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl + "/" + requestParams);
                        break;
                }

            } catch (IOException e) {
                Toast.makeText(ListActivity.this,
                        e.toString(), Toast.LENGTH_SHORT).show();
            }
            return response;
        }



        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            Gson converter = new Gson();

            switch (requestType) {
                case "GET":
                    Varosok [] varosArray = converter.fromJson(response.getContent(), Varosok[].class);
                    textViewEredmeny.setText("");
                    StringBuilder sr = new StringBuilder();
                    for (Varosok varos: varosArray) {
                        sr.append(varos.getVaros() + " " + varos.getOrszag() + " " + varos.getLakossag() + System.lineSeparator());
                    }
                    textViewEredmeny.setText(sr.toString());
                    break;
                case "POST":
                    break;

            }
        }
    }

}