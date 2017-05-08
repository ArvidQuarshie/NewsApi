package adapters;

/**
 * Created by arvid.quarshie on 5/8/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.arvidquarshie.newsapiapp.R;
import model.TechnologyRelated;

import java.util.List;

/**
 * Created by arvid.quarshie on 5/8/2017.
 */

public class TechnologyRelatedAdapter extends RecyclerView.Adapter<TechnologyRelatedAdapter.MyViewHolder> {

    private List<TechnologyRelated> technologyRelatedList;
    Context mContext;
    public String TITLE, DESC;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, description;
        public View mView;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title3);
            description = (TextView) view.findViewById(R.id.description3);

        }

        @Override
        public void onClick(View v) {
            Log.d("ArticlesAdapter:", "onClick " + getPosition() + " " + technologyRelatedList.size());
            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Alert message to be shown");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }


    public TechnologyRelatedAdapter(List<TechnologyRelated> technologyRelatedList) {
        this.technologyRelatedList = technologyRelatedList;
        Log.v("technologyRelatedList", "");
    }

    @Override
    public TechnologyRelatedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.technology_related_item, parent, false);
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TechnologyRelatedAdapter.MyViewHolder holder, int position) {
        final TechnologyRelated technologyRelated = technologyRelatedList.get(position);
        holder.title.setText(technologyRelated.getTitle());
        holder.description.setText(technologyRelated.getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = technologyRelated.getTitle();
                String description = technologyRelated.getDescription();

//populate the dialog with content when clicked
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                alertDialog.setTitle(title);
                alertDialog.setMessage(description);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return technologyRelatedList.size();
    }
}