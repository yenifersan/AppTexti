package com.santiago.textimaxapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.santiago.textimaxapp.ApiService;
import com.santiago.textimaxapp.ApiServiceGenerator;
import com.santiago.textimaxapp.R;
import com.santiago.textimaxapp.adapters.ComprasAdapter;
import com.santiago.textimaxapp.models.Compra;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView comprasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comprasList = findViewById(R.id.recyclerview);
        comprasList.setLayoutManager(new LinearLayoutManager(this));

        comprasList.setAdapter(new ComprasAdapter());
        initialize();
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<List<Compra>> call = service.getCompras();

        call.enqueue(new Callback<List<Compra>>() {
            @Override
            public void onResponse(Call<List<Compra>> call, Response<List<Compra>>response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {
                        List<Compra> compras = response.body();
                        Log.d(TAG, "compras: " + compras);

                        ComprasAdapter adapter = (ComprasAdapter) comprasList.getAdapter();
                        adapter.setCompras(compras);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Compra>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}