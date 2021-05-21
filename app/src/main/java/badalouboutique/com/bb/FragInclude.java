package badalouboutique.com.bb;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//TELA DE INCLUSÃO DOS PRODUTOS

public class FragInclude extends Fragment {

    EditText includeProd, includeQtd, includeValor;
    Button btnInclude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.frag_include, container, false);

        includeProd = v.findViewById(R.id.include_prod);
        includeQtd = v.findViewById(R.id.include_qtd);
        includeValor = v.findViewById(R.id.include_valor);
        btnInclude = v.findViewById(R.id.btn_incluir);
        btnInclude.setOnClickListener(new View.OnClickListener() {

            //CADASTRAR OS PRODUTOS NO BANCO DE DADOS
            @Override
            public void onClick(View view) {
                MeuBanco meuBanco = new MeuBanco(getActivity().getBaseContext());
                meuBanco.incluirProduto(includeProd.getText().toString().trim(),
                        Integer.valueOf(includeQtd.getText().toString().trim()),
                            Double.valueOf(includeValor.getText().toString().trim()));
                limpar();
            }
        });
        return v;
    }

    //LIMPAR PRODUTOS CADASTRADOS
    public void limpar() {
        includeProd.setText("");
        includeQtd.setText("");
        includeValor.setText("");
        includeProd.requestFocus();
    }
}