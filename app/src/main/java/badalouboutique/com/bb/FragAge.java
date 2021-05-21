package badalouboutique.com.bb;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;

//TELA DE AGENDAMENTO

public class FragAge extends Fragment implements View.OnClickListener{

    Button btnAge;
    TextView txtPedido;
    ImageView closeBt, checkPedido;
    private Spinner spinnerProd, spinnerQtd, spinnerEnt, spinnerPag;
    private TextView spinnerData;
    private int dia, mês, ano;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_age, container, false);

        spinnerProd = v.findViewById(R.id.spinner_prod);
        spinnerQtd = v.findViewById(R.id.spinner_qtd);
        spinnerEnt = v.findViewById(R.id.spinner_entrega);
        spinnerPag = v.findViewById(R.id.spinner_pag);
        btnAge = v.findViewById(R.id.btn_agendar);
        spinnerData = v.findViewById(R.id.spinner_data);

        spinnerData.setOnClickListener(this);

        btnAge.setOnClickListener(this);

        //FAZENDO A BUSCA NO BANCO DE DADOS DA LISTA PARA O SPINNER PRODUTOS
        MeuBanco meuBanco= new MeuBanco(getActivity().getBaseContext());
        ArrayList<String> dadosSpinner = meuBanco.spinnerDados();
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_selectable_list_item, dadosSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinnerProd.setAdapter(adapter);

        //FAZENDO A BUSCA NO BANCO DE DADOS DA LISTA PARA O SPINNER QTD
        MeuBanco meuBanco1 = new MeuBanco(getActivity().getBaseContext());
        ArrayList<String> dadosSpinner1 = meuBanco1.spinnerDados1();
        ArrayAdapter adapterQ = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_selectable_list_item, dadosSpinner1);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinnerQtd.setAdapter(adapterQ);

        //SPINNER ENTREGA ATRÁVES DO ARRAY CRIADO NA STRINGS
        ArrayAdapter adapaterEnt = ArrayAdapter.createFromResource(getActivity(),
                R.array.entregaSpinner, R.layout.support_simple_spinner_dropdown_item);
        spinnerEnt.setAdapter(adapaterEnt);

        //SPINNER PAGAMENTO ATRÁVES DO ARRAY CRIADO NA STRINGS
        ArrayAdapter adapaterPag = ArrayAdapter.createFromResource(getActivity(),
                R.array.pagSpinner, R.layout.support_simple_spinner_dropdown_item);
        spinnerPag.setAdapter(adapaterPag);

        return v;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.spinner_data) {
            dadosSpinnerData();
        }
        if (v.getId() == R.id.btn_agendar) {
            popupPedido();
        }
    }

    //CÓDIGO PARA O SPINNER DATA
    public void dadosSpinnerData() {

        final Calendar calendar = Calendar.getInstance();
        dia = calendar.get(Calendar.DATE);
        mês = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int ano, int mês, int dia) {
                spinnerData.setText(dia + "/" + mês + "/" + ano);
            }
        }, ano, mês, dia);
        datePickerDialog.show();
    }

    //POPUP PARA CONFIRMAÇÃO DO PEDIDO AGENDADO
    public void popupPedido() {

        Dialog popup = new Dialog(getActivity());
        popup.setContentView(R.layout.custom_popup);
        closeBt = (ImageView) popup.findViewById(R.id.close_bt);
        txtPedido = (TextView) popup.findViewById(R.id.txt_pedido);
        checkPedido = (ImageView) popup.findViewById(R.id.check_pedido);

        closeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();

            }
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }
}


