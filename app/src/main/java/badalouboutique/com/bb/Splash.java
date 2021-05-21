package badalouboutique.com.bb;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

//TELA DE ABERTURA

public class Splash extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Login();
            }
        }, 3000);
    }

    private void Login() {
        Intent intent = new Intent(Splash.this, Login.class);
        startActivity(intent);
        finish();
    }
}