package br.edu.ifsuldeminas.mch.applivro;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.edu.ifsuldeminas.mch.applivro.model.Book;
import br.edu.ifsuldeminas.mch.applivro.model.db.BookDAO;


public class MainActivity extends AppCompatActivity {

    private ListView todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        todoList = findViewById(R.id.todo_list);
        registerForContextMenu(todoList);

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Book book = (Book) todoList.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("id", book);

                startActivity(intent);
            }
        });
        updateBooks();

    }

    protected void onResume() {
        super.onResume();
        updateBooks();
    }

    private void updateBooks() {
        BookDAO dao = new BookDAO(this);
        List<Book> bookList = dao.loadBooks();
        dao.close();

        ArrayAdapter<Book> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        todoList.setAdapter(arrayAdapter);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem itemDelete = menu.add("Deletar livro");
        MenuItem itemCompartilhar = menu.add("Compartilhar livro");

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Book book = (Book) todoList.getItemAtPosition(info.position);

        itemCompartilhar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                    compartilharLivro(book);
                    return true;
            }
        });

        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo itemClicado = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Book book = (Book) todoList.getItemAtPosition(itemClicado.position);
                BookDAO dao = new BookDAO(MainActivity.this);
                dao.delete(book);
                dao.close();
                updateBooks();
                Toast t = Toast.makeText(MainActivity.this, "Livro excluído com sucesso!", Toast.LENGTH_SHORT);
                t.show();
                return true;
            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(MainActivity.this, InicialActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void compartilharLivro(Book book) {
        // Formata a mensagem com o título e o autor do livro
        String textoParaCompartilhar = String.format("Confira este livro: %s - %s", book.getTitle(), book.getAuthor());

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textoParaCompartilhar);

        // Verifica se há aplicativos que podem lidar com a intenção
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Compartilhar livro"));
        }
    }

}