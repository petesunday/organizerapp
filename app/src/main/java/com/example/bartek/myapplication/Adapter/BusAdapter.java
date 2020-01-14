package com.example.bartek.myapplication.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bartek.myapplication.Model.Rozklad;
import com.example.bartek.myapplication.R;

import java.util.List;

class  BusViewHolder extends RecyclerView.ViewHolder{

    public View view;
    public TextView idlinia_autobusowa, przystanek_poczatkowy, przystanek_koncowy, godzina_odjazdu;


    public BusViewHolder(View itemView) {
        super(itemView);
        idlinia_autobusowa = (TextView)itemView.findViewById(R.id.idlinia_autobusowa);
        przystanek_poczatkowy = (TextView)itemView.findViewById(R.id.przystanek_poczatkowy);
        przystanek_koncowy = (TextView)itemView.findViewById(R.id.przystanek_koncowy);
        godzina_odjazdu = (TextView)itemView.findViewById(R.id.godzina_odjazdu);

    }
}

public class BusAdapter extends RecyclerView.Adapter<BusViewHolder>{

    private List<Rozklad> rozklad;
    private Context context;

    public BusAdapter(List<Rozklad> rozklad, Context context) {
        this.rozklad = rozklad;
        this.context = context;
    }

    @Override
    public BusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_busitem,parent,false);
        return new BusViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BusViewHolder holder, int position) {
        holder.idlinia_autobusowa.setText(Integer.toString(rozklad.get(position)
                .getIdlinia_autobusowa()));
        holder.przystanek_poczatkowy.setText(rozklad.get(position).getPrzystanek_poczatkowy());
        holder.przystanek_koncowy.setText(rozklad.get(position).getPrzystanek_koncowy());
        holder.godzina_odjazdu.setText(rozklad.get(position).getGodzina_odjazdu());
    }

    @Override
    public int getItemCount() {
        return rozklad.size();
    }
}
