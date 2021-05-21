package badalouboutique.com.bb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

//CLASSE PARA LISTAR OS PRODUTOS CADASTRADOS

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList listCod, listProd, listQtd, listValor;

    ProdutosAdapter(Activity activity, Context context, ArrayList listCod, ArrayList listProd,
                    ArrayList listQtd, ArrayList listValor) {
        this.activity = activity;
        this.context = context;
        this.listCod = listCod;
        this.listProd = listProd;
        this.listQtd = listQtd;
        this.listValor = listValor;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_lista, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.list_id_txt.setText(String.valueOf(listCod.get(position)));
        holder.list_prod_txt.setText(String.valueOf(listProd.get(position)));
        holder.list_qtd_txt.setText(String.valueOf(listQtd.get(position)));
        holder.list_valor_txt.setText(String.valueOf(listValor.get(position)));
        //Recyclerview onClickListener
        holder.linearLayoutLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Edit.class);
                intent.putExtra("id", String.valueOf(listCod.get(position)));
                intent.putExtra("produto", String.valueOf(listProd.get(position)));
                intent.putExtra("qtd", String.valueOf(listQtd.get(position)));
                intent.putExtra("valor", String.valueOf(listValor.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCod.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView list_id_txt, list_prod_txt, list_qtd_txt, list_valor_txt;
        LinearLayout linearLayoutLista;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_id_txt = itemView.findViewById(R.id.list_id_txt);
            list_prod_txt = itemView.findViewById(R.id.lis_prod_txt);
            list_qtd_txt = itemView.findViewById(R.id.list_qtd_txt);
            list_valor_txt = itemView.findViewById(R.id.list_valor_txt);
            linearLayoutLista = itemView.findViewById(R.id.linear_layout_lista);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            linearLayoutLista.setAnimation(translate_anim);
        }
    }
}
