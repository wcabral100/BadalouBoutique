package badalouboutique.com.bb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.util.ArrayList;

//BANCOS DE DADOS

public class MeuBanco extends SQLiteOpenHelper {

    //ATRIBUINDO NOME E VERSÃO DO BANCO E DECLARANDO VARIÁVEIS
    private final Context context;
    private static final String DATABASE_NAME = "BadalouBoutiqueApp.db";
    private static final int DATABASE_VERSION = 1;

    /*
    private static final String TABLE_NAME1 = "usuarios";
    private static final String COLUMN_ID1 = "_id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_CEL = "celular";
    private static final String COLUMN_SENHA = "senha";
    */

    private static final String TABLE_NAME2 = "produtos";
    private static final String COLUMN_ID2 = "_id";
    private static final String COLUMN_PROD = "produto";
    private static final String COLUMN_QTD = "qtd";
    private static final String COLUMN_VALOR = "valor";

    public MeuBanco(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //CRIANDO AS TABELAS
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tab1 = "CREATE TABLE usuarios ("
                + "codigo integer primary key autoincrement,"
                + "nome text,"
                + "email text,"
                + "celular text,"
                + "senha text)";
        db.execSQL(tab1);

        String tab2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROD + " TEXT, " +
                COLUMN_QTD + " INTEGER, " +
                COLUMN_VALOR + " DECIMAL);";
        db.execSQL(tab2);
    }

    //CÓDIGO PAREA REINICIAR AS TABELAS
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    //METÓDOS PARA LIGAÇÃO COM  BANCO DE DADOS
    public String insereDadosUsuario(String nome, String email, String celular, String senha) {
        ContentValues valores;
        long resultado;
        SQLiteDatabase db = this.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("celular", celular);
        valores.put("senha", senha);

        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro os dados, tente novamente!";
        else
            return "Dados do Usuário cadastrado com sucesso!";
    }

    public Cursor carregaDadosLogin(String Login, String SenhaLogin) {
        Cursor cursor;
        String[] campos = { "codigo", "nome", "email", "senha" };
        String where = "email = '" + Login + "' and senha = '" + SenhaLogin + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    void incluirProduto(String produto, int qtd, double valor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PROD, produto);
        cv.put(COLUMN_QTD, qtd);
        cv.put(COLUMN_VALOR, valor);
        long result = db.insert(TABLE_NAME2,null, cv);
        if(result == -1){
            Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Produto cadastrado!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor carregaProduto(){
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public ArrayList<String> spinnerDados() {
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> dadosSpinner = new ArrayList<String>();

        Cursor cursor =  db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                dadosSpinner.add(cursor.getString(cursor.getColumnIndex("produto")));
            }while (cursor.moveToNext());
        }
        return dadosSpinner;
    }
    public ArrayList<String> spinnerDados1() {
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> dadosSpinner1 = new ArrayList<String>();

        Cursor cursor =  db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                dadosSpinner1.add(cursor.getString(cursor.getColumnIndex("qtd")));
            }while (cursor.moveToNext());
        }
        return dadosSpinner1;
    }

}
