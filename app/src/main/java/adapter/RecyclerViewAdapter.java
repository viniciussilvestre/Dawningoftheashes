package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dawningoftheashes.R;

import java.util.List;
import java.util.Objects;

import com.example.dawningoftheashes.model.Table;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private OnContactClickListener contactClickListener;
    private List<Table> tablesList;
    private Context context;

    public RecyclerViewAdapter(List<Table> tablesList,Context context, OnContactClickListener onContactClickListener)
    {
        this.tablesList = tablesList;
        this.context = context;
        this.contactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sheet_row, parent, false);
        return new ViewHolder(view, contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Table table = Objects.requireNonNull(tablesList.get(position));
        String tableName = table.getName();
        holder.name.setText(tableName);
    }

    @Override
    public int getItemCount()
    {
        return tablesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnContactClickListener onContactClickListener;
        public TextView name;

        public ViewHolder(@NonNull View itemView, OnContactClickListener onContactClickListener)
        {
            super(itemView);
            name = itemView.findViewById(R.id.rowTableName);
            this.onContactClickListener = onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactClickListener
    {
        void onContactClick(int position);
    }
}
