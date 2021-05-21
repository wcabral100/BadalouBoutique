package badalouboutique.com.bb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

//TELA PRINCIPAL DO ESTOQUE

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    RecyclerView lista;
    FloatingActionButton btnAdd;
    ImageView imVazio, btMenu;
    TextView txtVazio, titleToolbar;
    MeuBanco meuBanco;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    ProdutosAdapter produtosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar1 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar1);

        btMenu = findViewById(R.id.bt_menu);
        titleToolbar = findViewById(R.id.txt_toolbar3);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        //CÓDIGO PARA NAVEGAÇÃO DO MENU
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                if(item.getItemId() == R.id.it_home){
                    Toast.makeText(MainActivity.this, "Página inicial", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.it_prod) {
                    Intent intent = new Intent(MainActivity.this, Produtos.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.it_edit) {
                    Intent intent = new Intent(MainActivity.this, Edit.class);
                    startActivity(intent);
                }

                if (item.getItemId() == R.id.it_logout) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar1,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        lista = findViewById(R.id.lista);
        btnAdd = findViewById(R.id.btn_add);
        imVazio = findViewById(R.id.im_vazio);
        txtVazio = findViewById(R.id.txt_vazio);
        btnAdd.setOnClickListener(this);

        //CÓDIGO PARA LISTAR OS PRODUTOS CADASTRADOS
        meuBanco = new MeuBanco(MainActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        listarProdutos();

        produtosAdapter = new ProdutosAdapter(MainActivity.this,this, book_id,
                book_title, book_author, book_pages);
        lista.setAdapter(produtosAdapter);
        lista.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    //BOTÃO PARA IR PARA A TELA DE INCLUSÃO
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add) {
            Intent intent = new Intent(MainActivity.this, Produtos.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    //BUSCAR OS PRODUTOS NO BANCO PARA LISTAR
    void listarProdutos(){
        Cursor cursor = meuBanco.carregaProduto();
        if(cursor.getCount() == 0){
            imVazio.setVisibility(View.VISIBLE);
            txtVazio.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
            imVazio.setVisibility(View.GONE);
            txtVazio.setVisibility(View.GONE);
        }
    }
}
