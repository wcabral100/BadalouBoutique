package badalouboutique.com.bb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//TELA PARA REDEFINIÇÃO DE SENHA

public class EsqSenha extends AppCompatActivity {

    EditText textRedSenha;
    Button btnEnviar;
    ImageView imEmail, btnClose;
    TextView txtRed, txtPopEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esq_senha);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textRedSenha = findViewById(R.id.text_redefinir_senha);
        btnEnviar = findViewById(R.id.btn_avançar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupEsqSenha();

            }
        });
    }

    //POPUP PARA CONFIRMAÇÃO DO ENVIO DO E-MAIL.
    public void popupEsqSenha() {

        Dialog popup = new Dialog(this);
        popup.setContentView(R.layout.custom_logout);
        btnClose = (ImageView) popup.findViewById(R.id.close_bt);
        txtRed = (TextView) popup.findViewById(R.id.txt_red_senha);
        txtPopEmail = (TextView) popup.findViewById(R.id.txt_email);
        imEmail = (ImageView) popup.findViewById(R.id.im_email);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();

            }
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }
}


