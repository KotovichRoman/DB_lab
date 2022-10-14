package db.labs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fname = "Base_Lab.txt";
        f = new File(super.getFilesDir(), fname);
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
        else {
            ReadLine();
        }
    }

    public void InputClick(View view) {
        EditText name = findViewById(R.id.editTextName);
        EditText surname = findViewById(R.id.editTextSurname);
        if (name.getText().toString().trim().length() >= 1 &&
                surname.getText().toString().trim().length() >= 1) {
            WriteLine(surname.getText().toString(), name.getText().toString());

            name.setText("");
            surname.setText("");
        }
        else {
            Toast.makeText(this, "Введите ключ и значение", Toast.LENGTH_SHORT).show();
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

    private void WriteLine(String surname, String name) {
        String s = surname + ";" + name + ";" + "\r\n";
        try {
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(s);

            bw.close();
            fw.close();

            Log.d("Log_02", "Данные записаны");
        }
        catch (IOException e) {
            Log.d("Log_02", "Данные не записаны");
        }

        ReadLine();
    }

    private void ReadLine() {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            TextView resultRead = findViewById(R.id.readResult);

            String line = "";
            resultRead.setText("");

            try {
                while ((line = br.readLine()) != null) {
                    resultRead.append(line + "\n");
                }
                Log.d("Log_02", "Данные прочитаны");
            } catch (IOException e) {
                Log.d("Log_02", e.getMessage());
            }

            br.close();
            fr.close();
        }
        catch (IOException e) {
            Log.d("Log_02", "Файл не открыт");
        }
    }
}