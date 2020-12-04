package com.client.riseup_labs_assessment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.client.riseup_labs_assessment.R;
import com.client.riseup_labs_assessment.models.contents.Content;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private Content contentList;

    public ContentAdapter(Content contentList) {
        this.contentList = contentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Content content = contentList;
        holder.name.setText(content.getName());
        holder.type.setText(content.getType());
        holder.language.setText(content.getLanguage());
        holder.days.setText(String.valueOf(content.getSchedule().getDays()));
        holder.average.setText(String.valueOf(content.getRating().getAverage()));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, language, days, average;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            language = itemView.findViewById(R.id.language);
            days = itemView.findViewById(R.id.days);
            average = itemView.findViewById(R.id.average);
        }
    }
}
