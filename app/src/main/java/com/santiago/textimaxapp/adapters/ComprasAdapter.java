package com.santiago.textimaxapp.adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.santiago.textimaxapp.R;
import com.santiago.textimaxapp.activities.DetailVentaActivity;
import com.santiago.textimaxapp.models.Compra;

import java.util.ArrayList;
import java.util.List;

public class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.ViewHolder> {

    private  static final String TAG = ComprasAdapter.class.getSimpleName();


    private List<Compra> compras;

    public ComprasAdapter(){
        this.compras = new ArrayList<>();
    }

    public void setCompras(List<Compra> compras){
        this.compras = compras;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // para la vista principal
        public TextView cliente;
        public TextView anumordcom;
        public TextView afecordcom;




        public ViewHolder(View itemView) {
            super(itemView);
            cliente = itemView.findViewById(R.id.txt_cliente);
            anumordcom = itemView.findViewById(R.id.txt_anumordcom);
            afecordcom = itemView.findViewById(R.id.txt_afecordcom);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compra, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        final Compra compra = this.compras.get(position);

        viewHolder.cliente.setText(compra.getCliente());
        viewHolder.anumordcom.setText(compra.getAnumordcom());
        viewHolder.afecordcom.setText(compra.getAfecordcom());



        //detalles

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(viewHolder.itemView.getContext(), DetailVentaActivity.class);
                intent.putExtra("ID", compra.getId());
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return this.compras.size();
    }

}
