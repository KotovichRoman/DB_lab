package db.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void BackButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void AddButtonClick(View view) {
        EditText name = findViewById(R.id.edit_text_name);
        EditText surname = findViewById(R.id.edit_text_surname);
        EditText phone = findViewById(R.id.edit_text_phone);
        DatePicker birthday = findViewById(R.id.date_picker);

        if (name.getText().toString().trim().length() >= 1 && surname.getText().toString().trim().length() >= 1
                && phone.getText().toString().trim().length() >= 1) {
            String birthdayText = birthday.getDayOfMonth() + "." + birthday.getMonth() + "." + birthday.getYear();

            Contact contact = new Contact(name.getText().toString(), surname.getText().toString(),
                    phone.getText().toString(), birthdayText);
        }
        else {
            Toast toast = Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}