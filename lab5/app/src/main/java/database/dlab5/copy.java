package database.dlab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*public class MainActivity extends AppCompatActivity {
    private final static String fname = "lab5.txt";
    public Integer countRows = 0;
    private static final int PERMISSION_REQUEST_CODE = 123;
    private String txt;
    private static Scanner x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean rc = false;
        File f = new File(super.getFilesDir(),fname);
        if(rc = f.exists())
        {
            Log.d("Log_02", "File "+ fname+" exists");
        }
        else
        {
            Log.d("Log_02", "File "+ fname+" dont found");
            //dialog window about creating a file
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("File " + fname + " is creating..").setPositiveButton("okay",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("Log_02", "File " + fname + " is creating");
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



            if(key1.getText().toString().trim().length() < 1 && value1.getText().toString().trim().length() < 1)
            {
                Toast.makeText(this, "Введите ключ и значение", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //int hashedKey = getHashCode(key1.getText().toString());
                txt="";
                txt = key1.getText().toString()  +value1.getText().toString();
                /*while(value1.getText().toString().length()<10)
                {
                    txt  =txt + "*";
                }*/
                //txt  = txt + value1.getText().toString() + "*";
                /*txt +="\n";
                if(hasPermissions())
                {
                    //writeData(fname, txt, 0);
                    writeData(fname, key1.getText().toString(), value1.getText().toString(), 0);
                    Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
                }else
                {
                    requestPermissionWithRationale();
                }



            }



        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // метод для записи данных в файл
    private void writeData(String filePath, String dataKey, String dataValue, int seek) throws IOException {
        // открываем файл с возможностью как чтения, так и записи

        File f = new File(getFilesDir(), filePath);
        RandomAccessFile file = new RandomAccessFile(f, "rw");
        // переходим на определенный индекс
        file.seek(file.length());
        //String pos = String.valueOf(file.getFilePointer());
        //Toast.makeText(this, pos, Toast.LENGTH_SHORT).show();

        // запишем данные в этом месте


        countRows++;*/

       /* Integer i = 0;
        Integer isCopyFound = 0;
        if(file.length()==0)
        {file.write(data.getBytes());}
        else
            {for(i =0; i < countRows; i++)
            {
                String str = readRandomAccessFile(filePath, 0, i+1, 5);
                str = str.substring(0,5);
                //Toast.makeText(this, data.substring(0,5) + "*" + str, Toast.LENGTH_SHORT).show();

                if(data.substring(0,5).equals(str))
                {
                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
                    //break;
                    isCopyFound=1;

                }
                else
                {
                    Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
                    //isCopyFound=1;
                }
            }
                if(isCopyFound==0)
                {
                    file.write(data.getBytes());
                }}*/
       /* boolean found = false;
        String tempKey="";
        try
        {
            x = new Scanner(f);

        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



        file.close();
    }

    // метод для чтения с файла
    private byte[] readCharsFromFile(String filePath, int seek, int chars) throws IOException {
        // открываем файл только для чтения
        File f = new File(getFilesDir(), filePath);
        RandomAccessFile file = new RandomAccessFile(f, "rw");
        file.seek(seek);
        byte[] bytes = new byte[chars];
        file.read(bytes);
        file.close();
        return bytes;
    }

    private int getHashCode(String key)
    {
        return Integer.parseInt(key.substring(3,4));
    }

    private boolean hasPermissions(){
        int res = 0;
        //string array of permissions,
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        for (String perms : permissions){
            res = checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)){
                return false;
            }
        }
        return true;
    }
    public void requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            final String message = "Storage permission is needed to show files count";
            Snackbar.make(MainActivity.this.findViewById(R.id.activity_view), message, Snackbar.LENGTH_LONG)
                    .setAction("GRANT", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            requestPerms();
                        }
                    })
                    .show();
        } else {
            requestPerms();
        }
    }
    private void requestPerms(){
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissions,PERMISSION_REQUEST_CODE);
        }
    }

    public String readRandomAccessFile(String filePath, int lineStart, int lineEnd, int charsPerLine)
    {
        File f = new File(getFilesDir(), filePath);
        String data = "";

        ArrayList<String> dialogLinesRead = new ArrayList<String>();
        int bytesPerLine = charsPerLine+2;

        try{
            RandomAccessFile file = new RandomAccessFile(f, "rw");
            for(int i = lineStart; i < lineEnd; i++)
            {
                file.seek(bytesPerLine*i);
                data = file.readLine();
                dialogLinesRead.add(data);

            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String returnData ="";
        for(int i =0; i< dialogLinesRead.size();i++)
        {
            returnData+=dialogLinesRead.get(i);
        }
        if(dialogLinesRead.isEmpty())
        {
            Toast.makeText(this, "Файл пуст", Toast.LENGTH_SHORT).show();
        }
        return returnData;

    }

}*/