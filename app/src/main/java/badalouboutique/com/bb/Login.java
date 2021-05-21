package badalouboutique.com.bb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

//TELA DE LOGIN

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnEsqSenha, btnEntrar, btnCadastrar;
    EditText textEmail, textSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEsqSenha = findViewById(R.id.btn_esq_senha);
        btnEntrar = findViewById(R.id.btn_entrar);
        btnCadastrar = findViewById(R.id.btn_cadastrar);
        textEmail = findViewById(R.id.text_email);
        textSenha = findViewById(R.id.text_senha);

        btnEsqSenha.setOnClickListener(this);
        btnEntrar.setOnClickListener(this);
        btnCadastrar.setOnClickListener(this);
    }

    //AÇÕES COM O CLICK
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_esq_senha) {
            Intent it = new Intent(this, EsqSenha.class);
            startActivity(it);
        }

        if (v.getId() == R.id.btn_entrar) {
            consultaUsuarioLogin();
        }

        if (v.getId() == R.id.btn_cadastrar) {
            Intent it = new Intent(this, Cadastro.class);
            startActivity(it);
        }
    }

    //BUSCANDO O USUARIO CADASTRADO NO BANCO DE DADOS PARA O LOGIN
    public void consultaUsuarioLogin() {
        String Login = textEmail.getText().toString();
        String SenhaLogin = textSenha.getText().toString();

        MeuBanco meuBanco = new MeuBanco(getBaseContext());

        Cursor dados = meuBanco.carregaDadosLogin(Login, SenhaLogin) ;

        //SE O USUARIO FOR ENCONTRADO, LOGAR
        if(dados.moveToFirst()){
            // levando parametros (nome do usuario) para a tela de login
            Intent it = new Intent(this, MainActivity.class);
            String nome = dados.getString(1);
            Bundle parametros = new Bundle();
            parametros.putString("nome",nome);
            it.putExtras(parametros);
            startActivity(it);

            //SE NÃO FOR ENCONTRADO MOSTRAR MENSAGEM DE ERRO
        }else{
            String msg= "Dados não encontrados no sistema";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            limpar();
        }
    }

    //LIMPAR DADOS DO CAMPO
    public void limpar(){
        textEmail.setText("");
        textSenha.setText("");
        textEmail.requestFocus();
    }
}

