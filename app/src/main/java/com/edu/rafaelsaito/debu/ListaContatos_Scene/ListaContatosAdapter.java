package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;
import com.edu.rafaelsaito.debu.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class ListaContatosAdapter extends RecyclerView.Adapter<ListaContatosAdapter.ViewHolder> {

    private List<ContatoEntity> contatoList;
    OnRecyclerViewSelected onRecyclerViewSelected;
    private Context context;

    //Construtor que recebe a lista
    ListaContatosAdapter(List<ContatoEntity> contatoList, Context context) {
        this.contatoList = contatoList;
        this.context = context;
    }

    //infla o componente view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lista_contatos, parent, false);
        return new ViewHolder(v);
    }

    //seta os dados nas views
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContatoEntity contatoEntity = contatoList.get(position);
        holder.contactName.setText(contatoEntity.getName());
        holder.contactTel.setText(contatoEntity.getTelephone());
        Picasso.with(context)
                .load(contatoEntity.getImage())
                .centerCrop()
                .fit()
                .into(holder.contactImage);
    }

    //retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return contatoList.size();
    }

    //mapeamento dos componentes da view
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_contato)
        TextView contactName;

        @BindView(R.id.image_view)
        ImageView contactImage;

        @BindView(R.id.tel_contato)
        TextView contactTel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //seta o clique rápido
        @OnClick(R.id.container)
        void onItemClick(View view){
            if(onRecyclerViewSelected != null)
                onRecyclerViewSelected.onClick(view, getAdapterPosition());

        }

        //seta o clique longo
//        @OnLongClick(R.id.container)
//        boolean onLongItemClick(View view){
//            if(onRecyclerViewSelected != null)
//                onRecyclerViewSelected.onLongClick(view, getAdapterPosition());
//
//            return true;
//        }

    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }

}
