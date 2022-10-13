package db.labs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fname = "Base_Lab.txt";
        boolean rc = ExistBase(fname);

        if (!rc) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Создаётся файл " + fname).setPositiveButton("Да",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("Log_02", "Создание файла " + fname);
                        }
                    });
            AlertDialog ad = b.create();
            ad.show();
        }
    }

    private boolean ExistBase(String fname) {
        boolean rc;
        File f = new File(super.getFilesDir(), fname);
        if (rc = f.exists()) {
            Log.d("Log_02", "Файл " + fname + " существует");
        }
        else {
            Log.d("Log_02", "Файл " + fname + " не найден");
        }
        return rc;
    }
}