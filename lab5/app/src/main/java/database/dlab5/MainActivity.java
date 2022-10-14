package database.dlab5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private final static String fname = "lab5.txt";
    private static Scanner x;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean rc = false;
        File f = new File(super.getFilesDir(), fname);

        if(rc = f.exists()) {
            Log.d("Log_02", "File " + fname + " exists");
        }
        else {
            Log.d("Log_02", "File " + fname + " don't found");

            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("File " + fname + " is creating..").setPositiveButton("okay",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try
                            {
                                f.createNewFile();
                                Log.d("Log_02", "File "+ fname+" is created//" + f.getAbsolutePath() );

                            }
                            catch (IOException e) {
                                Log.d("Log_02", "File "+ fname+" is not created");
                            }
                        }
                    });

            AlertDialog ad = b.create();
            ad.show();
        }
    }

    public void saveText(View view){
        try {
            EditText key1 = (EditText) findViewById(R.id.key1txt);
            EditText value1 = (EditText) findViewById(R.id.value1txt);

            if(key1.getText().toString().trim().length() < 1 && value1.getText().toString().trim().length() < 1) {
                Toast.makeText(this, "Введите ключ и значение", Toast.LENGTH_SHORT).show();
            }
            else {
                writeRandomAccessFile(fname, -1, key1.getText().toString(), value1.getText().toString(),20);
                key1.setText("");
                value1.setText("");
            }
        }
        catch(Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void readText(View view)
    {
        File f = new File(getFilesDir(), fname);
        EditText key2 = (EditText) findViewById(R.id.key2txt);
        TextView value2 = (TextView) findViewById(R.id.value2txt);
        boolean found = false;
        String tempKey="";
        String key = key2.getText().toString();

        try {
            RandomAccessFile file = new RandomAccessFile(f, "rw");
            x = new Scanner(f);
            while(x.hasNext() && !found) {
                tempKey = x.next();
                if(tempKey.substring(0,5).equals(key)) {
                    found=true;
                    value2.setText(tempKey.substring(6,tempKey.length()));
                }
            }
            if(!found) {
                Toast.makeText(this, "Значения к такому ключу нет", Toast.LENGTH_SHORT).show();
            }
            file.close();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void writeRandomAccessFile(String filePath, int lineToWriteData,String key, String value, int charsPerLine) {
        File f = new File(getFilesDir(), filePath);
        String data = key + "," + value + "***";
        int bytesPerLine = charsPerLine+2;
        int pos = 0;

        try {
            RandomAccessFile file = new RandomAccessFile(f, "rw");
            long whereToWrite;
            if(lineToWriteData==-1) {
                whereToWrite= file.length();
            }
            else {
                whereToWrite = bytesPerLine*lineToWriteData;
            }
            boolean found = false;
            String tempKey = "";
            try {
                x = new Scanner(f);
                while(x.hasNext() && !found) {
                    tempKey = x.next();

                    if(tempKey.substring(0,5).equals(key)) {
                        found=true;
                        Integer v1 = pos * bytesPerLine;
                        if((pos*bytesPerLine - 2)<0)
                        {
                            file.seek(v1);
                        }else {
                            file.seek(v1 - pos*2);
                        }

                        file.write(data.getBytes());
                        file.writeBytes(System.getProperty("line.separator"));
                        Toast.makeText(this, "Значение перезаписано", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        if(tempKey.substring(4,5).equals(key.substring(4,5))) {
                            Toast.makeText(this, "Коллизия: " +tempKey.substring(4,5)+"+"+key.substring(4,5), Toast.LENGTH_SHORT).show();
                            Integer v1 = pos * bytesPerLine;
                            String link;
                            if((pos*bytesPerLine - 2)<0)
                            {
                                file.seek(v1);
                                link = tempKey.substring(0,16) + "," +whereToWrite;
                            }else {
                                file.seek(v1 - pos*2);
                                Integer v2 = v1 - pos*2;
                                link = tempKey.substring(0,16) + "," + whereToWrite;
                            }

                            file.write(link.getBytes());
                            file.writeBytes(System.getProperty("line.separator"));
                        }
                    }
                    pos++;
                }
                if(!found)
                {
                    file.seek(whereToWrite);
                    file.write(data.getBytes());
                    file.writeBytes(System.getProperty("line.separator"));
                    Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();

                }
            }
            catch(Exception e)
            {
                Toast.makeText(this, "error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}