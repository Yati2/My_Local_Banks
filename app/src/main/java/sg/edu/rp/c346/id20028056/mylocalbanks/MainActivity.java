package sg.edu.rp.c346.id20028056.mylocalbanks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String bank = "";
    TextView tvDbs;
    TextView tvOcbc;
    TextView tvUob;
    Boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDbs = findViewById(R.id.dbs);
        tvOcbc = findViewById(R.id.ocbc);
        tvUob = findViewById(R.id.uob);

        registerForContextMenu(tvDbs);
        registerForContextMenu(tvOcbc);
        registerForContextMenu(tvUob);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        if (v == tvDbs) {
            bank = "dbs";
        } else if (v == tvOcbc) {
            bank = "ocbc";
        } else if (v == tvUob) {
            bank = "uob";
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        if (bank.equalsIgnoreCase("dbs")) {
            intent = createIntents(id, null, R.string.dbs_web, R.string.dbs_num);
            changeColor(id, tvDbs);

        } else if (bank.equalsIgnoreCase("ocbc")) {

            intent = createIntents(id, null, R.string.ocbc_web, R.string.ocbc_num);
            changeColor(id, tvOcbc);

        } else if (bank.equalsIgnoreCase("uob")) {
            intent = createIntents(id, null, R.string.uob_web, R.string.uob_num);
            changeColor(id, tvUob);
        }

        if (id != R.id.fav) {
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ChineseSelection) {

            tvDbs.setText(R.string.dbs_chinese);
            tvOcbc.setText(R.string.ocbc_chinese);
            tvUob.setText(R.string.uob_chinese);
            return true;

        } else if (id == R.id.EnglishSelection) {
            tvDbs.setText(R.string.dbs_name);
            tvOcbc.setText(R.string.ocbc_name);
            tvUob.setText(R.string.uob_name);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeColor(int id, TextView v) {
        if (id == R.id.fav) {
            if (!isFav) {
                v.setTextColor(0xFFFF0000);
                isFav = true;
            } else if (isFav) {
                v.setTextColor(0xFF000000);
                isFav = false;
            }
        }
    }

    public Intent createIntents(int id, Intent intent, int web, int mobileNum) {
        if (id == R.id.website) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(web)));

        } else if (id == R.id.contact) {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse(getString(mobileNum)));
        }
        return intent;

    }
}




