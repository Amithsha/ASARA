package com.example.asara;
import static com.example.asara.R.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.CaseViewHolder> {

    private List<Case> caseList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Case selectedCase);
    }

    public CaseAdapter(Context context, List<Case> caseList, OnItemClickListener listener) {
        this.context = context;
        this.caseList = caseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layout.list_item_case, parent, false);
        return new CaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseViewHolder holder, int position) {
        Case currentCase = caseList.get(position);
        holder.bind(currentCase, listener);
    }

    @Override
    public int getItemCount() {
        return caseList.size();
    }

    public class CaseViewHolder extends RecyclerView.ViewHolder {
        private TextView idTextView;
        private TextView shortDescriptionTextView;
        private TextView fullDescriptionTextView;

        private TextView statusTextView;

        public CaseViewHolder(View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(id.textViewCaseId);
            shortDescriptionTextView = itemView.findViewById(id.textViewShortDescription);
            fullDescriptionTextView = itemView.findViewById(id.textViewFullDescription);
            statusTextView = itemView.findViewById(id.textViewStatus);
        }

        public void bind(final Case currentCase, final OnItemClickListener listener) {
            idTextView.setText(String.valueOf(currentCase.getId()));
            shortDescriptionTextView.setText(currentCase.getShortDescription());
            fullDescriptionTextView.setText(currentCase.getFullDescription());
            statusTextView.setText(currentCase.getStatus());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(currentCase);
                }
            });
        }
    }
}
