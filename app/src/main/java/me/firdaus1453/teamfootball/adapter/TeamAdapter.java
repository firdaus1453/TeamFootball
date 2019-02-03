package me.firdaus1453.teamfootball.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.firdaus1453.teamfootball.R;
import me.firdaus1453.teamfootball.helper.Constant;
import me.firdaus1453.teamfootball.model.TeamData;
import me.firdaus1453.teamfootball.ui.DetailTeamActivity;

/**
 * Created by firdaus1453 on 2/3/2019.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private final Context context;
    private final List<TeamData> teamDataList;

    public TeamAdapter(Context context, List<TeamData> teamDataList) {
        this.context = context;
        this.teamDataList = teamDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TeamData teamData = teamDataList.get(position);

        holder.txtNameTeam.setText(teamData.getNameTeam());
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);

        Glide.with(context).load(teamData.getLogoTeam()).apply(options).into(holder.imgLogoTeam);
        Log.i("Cek id team", teamData.getIdTeam());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.KEY_ID_TEAM, teamData.getIdTeam());
                context.startActivity(new Intent(context, DetailTeamActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgLogoTeam)
        ImageView imgLogoTeam;
        @BindView(R.id.txtNameTeam)
        TextView txtNameTeam;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
