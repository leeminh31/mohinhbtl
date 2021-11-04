package com.example.mohinhbtl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {

    private List<User> mListUser;
    private List<User> mListUserAll;
    private IClickItemUser iClickItemUser;




    public interface IClickItemUser{
        void updateUser(User user);
        void deleteUser(User user);
    }

    public UserAdapter(IClickItemUser iClickItemUser) {
        this.iClickItemUser = iClickItemUser;
    }

    public void setData(List<User> list){
        this.mListUser=list;
        this.mListUserAll=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user=mListUser.get(position);
        if(user==null){
            return;
        }
        holder.tvUsername.setText(user.getUsername());
        holder.tvAddress.setText(user.getAddress());
        holder.tvCccd.setText(user.getCccd());
        holder.tvPhone.setText(user.getPhone());
        holder.tvDate.setText(user.getDate());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemUser.updateUser(user);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemUser.deleteUser(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListUser != null){
            return  mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private TextView tvAddress;
        private TextView tvCount;
        private TextView tvCccd;
        private TextView tvPhone;
        private TextView tvDate;
        private Button btnUpdate;
        private Button btnDelete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername=itemView.findViewById(R.id.tv_username);
            tvAddress=itemView.findViewById(R.id.tv_address);
            tvCount=itemView.findViewById(R.id.tv_count);
            tvCccd=itemView.findViewById(R.id.tv_cccd);
            tvPhone=itemView.findViewById(R.id.tv_phone);
            tvDate=itemView.findViewById(R.id.tv_date);
            btnUpdate=itemView.findViewById(R.id.btn_update);
            btnDelete=itemView.findViewById(R.id.btn_delete);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String strSearch=charSequence.toString();
                if(strSearch.isEmpty()){
                    mListUser=mListUserAll;
                }else{
                    List<User> list=new ArrayList<>();
                    for(User user:mListUserAll){
                        if(user.getDate().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(user);
                        }
                    }
                    mListUser=list;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=mListUser;
                    return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListUser= (List<User>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
