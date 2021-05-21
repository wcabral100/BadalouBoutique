package badalouboutique.com.bb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//TELA DE CADASTRO

public class Cadastro extends AppCompatActivity  implements View.OnClickListener{

    Button btnCad;
    EditText includeNome, includeEmail, includeCel, includeSenha, includeConfSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCad = findViewById(R.id.btn_cad);
        includeNome = findViewById(R.id.include_nome);
        includeEmail = findViewById(R.id.include_email);
        includeCel = findViewById(R.id.include_cel);
        includeSenha = findViewById(R.id.include_senha);
        includeConfSenha = findViewById(R.id.include_conf_senha);

        btnCad.setOnClickListener(this);
    }

    //CADASTRAR OS DADOS INCLUSOS NO BANCO DE DADOS AO CLICAR NO BOTÃO.
    @Override
    public void onClick(View v) {

        String NomeCad = includeNome.getText().toString();
        String EmailCad = includeEmail.getText().toString();
        String CelCad = includeCel.getText().toString();
        String SenhaCad = includeSenha.getText().toString();
        String ConfSenhaCad = includeConfSenha.getText().toString();

        MeuBanco meuBanco = new MeuBanco(getBaseContext());
        String resultado;

        //SE A SENHA FOR COMPATÍVEL CADASTRAR

        if (SenhaCad.equals(ConfSenhaCad)) {
            resultado = meuBanco.insereDadosUsuario(NomeCad, EmailCad, CelCad, SenhaCad);

            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            limpar();

            //SE NÃO FOR COMPATÍVEL MOSTRAR MENSAGEM DE ERRO.
        }else{
            String msg = "As senhas digitadas não são iguais, digite novamente!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    //LIMPAR OS CAMPOS APÓS O CADASTRO.
    public void limpar() {
        includeNome.setText("");
        includeEmail.setText("");
        includeCel.setText("");
        includeSenha.setText("");
        includeConfSenha.setText("");
        includeNome.requestFocus();
    }
}

