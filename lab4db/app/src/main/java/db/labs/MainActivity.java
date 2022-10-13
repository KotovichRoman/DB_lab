package db.labs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    BufferedWriter bw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fname = "Base_Lab.txt";
        File f = new File(super.getFilesDir(), fname);
        boolean rc = ExistBase(fname);

        if (!rc) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            try {
                f.createNewFile();
                Log.d("Log_02", "Файл " + fname + " создан");

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
            catch (IOException e) {
                Log.d("Log_02", "Файл " + fname + " не создан");
            }
        }

        try {
            FileWriter fw = new FileWriter(f, true);
            bw = new BufferedWriter(fw);
        }
        catch (IOException e) {
            Log.d("Log_02", "Файл " + fname + " не открыт" + e.getMessage());
        }

        InputClick();
    }

    private void InputClick() {
        EditText name = findViewById(R.id.editTextName);
        EditText surname = findViewById(R.id.editTextSurname);

        WriteLine(surname.getText().toString(), name.getText().toString());
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

    private void WriteLine(String surname, String name) {
        String s = surname + ";" + name + ";" + "\r\n";
        Log.d("Log_02", s);
        try {
            bw.write(s);
            Log.d("Log_02", "Данные записаны");
        }
        catch (IOException e) {
            Log.d("Log_02", e.getMessage());
        }
    }
}