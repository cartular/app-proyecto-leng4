package com.varasccatalina.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private List<ScheduleItem> scheduleItems;

    public ScheduleAdapter(List<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleItem scheduleItem = scheduleItems.get(position);
        holder.bind(scheduleItem);
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeSlotText;
        private TextView classText;

        public ViewHolder(View itemView) {
            super(itemView);
            timeSlotText = itemView.findViewById(R.id.timeTextView);
            classText = itemView.findViewById(R.id.classTextView);
        }

        public void bind(ScheduleItem scheduleItem) {
            timeSlotText.setText(scheduleItem.getTime());
            classText.setText(scheduleItem.getClassName());
        }
    }
}
