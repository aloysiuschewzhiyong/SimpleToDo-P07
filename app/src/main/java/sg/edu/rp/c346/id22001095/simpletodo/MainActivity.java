package sg.edu.rp.c346.id22001095.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerOption;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    EditText editText;
    ListView lvTasks;
    ArrayList<String> alTasks;
    ArrayAdapter aaTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerOption = findViewById(R.id.spinnerAddOrRemove);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        editText = findViewById(R.id.editText);
        lvTasks = findViewById(R.id.listViewTasks);

        alTasks = new ArrayList<>();

        aaTasks = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alTasks);
        lvTasks.setAdapter(aaTasks);


        spinnerOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);

                        editText.setHint("Type in a new task here");

                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String newTask = editText.getText().toString();
                                alTasks.add(newTask);
                                aaTasks.notifyDataSetChanged();
                            }
                        });

                        break;

                    case 1:
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);

                        editText.setHint("Type in the index of the task to be removed");

                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    int index = Integer.parseInt(editText.getText().toString());

                                    if (alTasks.size() == 0){
                                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(index > alTasks.size()){
                                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        alTasks.remove(index);
                                        aaTasks.notifyDataSetChanged();
                                    }
                                }
                                catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "Invalid index", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });


    }
}
