package com.santiago.textimaxapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.santiago.textimaxapp.ApiService;
import com.santiago.textimaxapp.ApiServiceGenerator;
import com.santiago.textimaxapp.R;
import com.santiago.textimaxapp.models.Compra;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailVentaActivity extends AppCompatActivity {

    private static final String TAG = DetailVentaActivity.class.getSimpleName();

    private Integer id;
    private TextView anumordcom;
    private TextView afecordcom;
    private TextView cliente;
    private TextView formapago;
    private TextView moneda;
    private TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_venta);

        anumordcom=(TextView)findViewById(R.id.txt_anumordcom);
        afecordcom=(TextView) findViewById(R.id.txt_afecordcom);
        cliente=(TextView)findViewById(R.id.txt_cliente);
        formapago=(TextView)findViewById(R.id.txt_formapago);
        moneda= (TextView)findViewById(R.id.txt_moneda);
        total= (TextView) findViewById(R.id.txt_total);


        id= getIntent().getExtras().getInt("ID");

        Log.e(TAG, "id:" + id);

        initialize();
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Compra> call = service.showCompra(id);

        call.enqueue(new Callback<Compra>() {
            @Override
            public void onResponse(Call<Compra> call, Response<Compra> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Compra compra = response.body();
                        Log.d(TAG, "compra: " + compra);
                        anumordcom.setText(compra.getAnumordcom());
                        afecordcom.setText(compra.getAfecordcom());
                        cliente.setText(compra.getCliente());


                        formapago.setText(compra.getFormapago());
                        moneda.setText(compra.getMoneda());
                        total.setText("s/. "+compra.getTotal());

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DetailVentaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Compra> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(DetailVentaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


    //metodo para aprobar la orden de compra mediante un checkbox
    public void onCheckboxClicked(View view) {




    }



}
