package medic.esy.es.mamyapp.Activites.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import medic.esy.es.mamyapp.Activites.model.parent;
import medic.esy.es.mamyapp.R;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context context;
    private List<parent> userphone;

    public UsersAdapter(Context context, List<parent> mUsers) {
        this.context=context;
        this.userphone=mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.userscard,viewGroup,false);
        return new UsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        parent users=userphone.get(i);
        viewHolder.username.setText(users.getName());

    }


    @Override
    public int getItemCount() {
        return userphone.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView profileImage;
        private TextView  username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage=(ImageView)itemView.findViewById(R.id.usProfileImage);
            username=(TextView)itemView.findViewById(R.id.usUsername);
        }
    }
}
