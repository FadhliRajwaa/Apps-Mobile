package com.example.apps_mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mengaktifkan edge-to-edge layout untuk aplikasi
        EdgeToEdge.enable(this);

        // Menetapkan layout XML yang digunakan
        setContentView(R.layout.activity_main);

        // Mendapatkan referensi ke komponen-komponen UI
        TextView tv1 = findViewById(R.id.textView2);
        TextView tv2 = findViewById(R.id.textView);
        TextView tv3 = findViewById(R.id.textView3);
        EditText et1 = findViewById(R.id.editTextNumber2);
        EditText et2 = findViewById(R.id.editTextNumber3);
        Button bt1 = findViewById(R.id.button2);
        Button bt2 = findViewById(R.id.button3);
        Button bt3 = findViewById(R.id.button4);
        Button bt4 = findViewById(R.id.button5);

        // Menangani insets untuk memberikan padding pada view berdasarkan system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Mengatur click listeners untuk setiap tombol untuk melakukan operasi matematika
        bt1.setOnClickListener(v -> performOperation(tv1, et1, et2, '+'));
        bt2.setOnClickListener(v -> performOperation(tv1, et1, et2, '-'));
        bt3.setOnClickListener(v -> performOperation(tv1, et1, et2, '*'));
        bt4.setOnClickListener(v -> performOperation(tv1, et1, et2, '/'));

    }

    // Metode untuk melakukan operasi matematika dan menampilkan hasil
    private void performOperation(TextView resultView, EditText input1, EditText input2, char operation) {
        try {
            // Mengonversi teks input menjadi nilai double
            double num1 = Double.parseDouble(input1.getText().toString());
            double num2 = Double.parseDouble(input2.getText().toString());
            double result = 0;

            // Menentukan operasi yang dilakukan berdasarkan karakter operasi yang diberikan
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    // Mengecek jika pembagi adalah nol
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            // Menampilkan hasil ke TextView
            resultView.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            // Menampilkan pesan error jika input tidak valid
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}
