package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;
import com.edu.rafaelsaito.debu.Modelo.ContatoListEntity;
import com.edu.rafaelsaito.debu.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class ListaContatosAdapter extends RecyclerView.Adapter<ListaContatosAdapter.ViewHolder> {

    private ContatoListEntity contatoList;
    OnRecyclerViewSelected onRecyclerViewSelected;
    private Context context;

    //Construtor que recebe a lista
    ListaContatosAdapter(ContatoListEntity contatoList, Context context) {
        this.contatoList = contatoList;
        this.context = context;
    }

    //infla o componente view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_list, parent, false);
        return new ViewHolder(v);
    }

    //seta os dados nas views
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ContatoEntity contatoEntity = contatoList.getContatos().get(position);
        holder.contactName.setText(contatoEntity.getName());
        holder.contactTel.setText(contatoEntity.getTelephone());

        Log.d("caminho da imagem", "aqui " + contatoEntity.getImage());

        final Context context = holder.contactImage.getContext();
        final ImageView img = holder.contactImage;
        Glide.with(context)
                .load(contatoEntity.getImage())
                .asBitmap().centerCrop()
                .into(new BitmapImageViewTarget(holder.contactImage){
                    @Override
                    protected  void  setResource(Bitmap resource){
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                        circularBitmapDrawable.setCircular(true);
                        img.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    //retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return contatoList.getContatos().size();
    }

    //mapeamento dos componentes da view
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_contato) TextView contactName;

        @BindView(R.id.image_view) ImageView contactImage;

        @BindView(R.id.tel_contato) TextView contactTel;

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
    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }

}
