package com.example.bartek.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bartek.myapplication.Database.Database;
import com.example.bartek.myapplication.Model.Wydarzenie;
import com.example.bartek.myapplication.R;

import java.util.List;

class EventViewHolder extends RecyclerView.ViewHolder {

    public View view;
    public TextView data, wydarzenie;
    public ImageView iv1;

    public EventViewHolder(View itemView) {
        super(itemView);
        data = (TextView) itemView.findViewById(R.id.data);
        wydarzenie = (TextView) itemView.findViewById(R.id.wydarzenie);
        iv1 = (ImageView) itemView.findViewById(R.id.iv1);


        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database database = new Database(view.getContext());
                boolean insertData = database.setNotatkaEvent(data.getText().toString(),
                        wydarzenie.getText().toString());

                if (insertData) {
                    Toast.makeText(view.getContext(), "Dodano do kalendarza",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Nie można dodać do kalendarza",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Wydarzenie> wydarzenie;
    private Context context;

    public EventAdapter(List<Wydarzenie> wydarzenie, Context context) {
        this.wydarzenie = wydarzenie;
        this.context = context;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_event_item, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.data.setText(wydarzenie.get(position).getData());
        holder.wydarzenie.setText(wydarzenie.get(position).getNazwa() + " @ " +
                wydarzenie.get(position).getMiejsce());
    }

    @Override
    public int getItemCount() {
        return wydarzenie.size();
    }
}
