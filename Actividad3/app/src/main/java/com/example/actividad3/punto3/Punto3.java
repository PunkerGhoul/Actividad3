package com.example.actividad3.punto3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.actividad3.MainActivity;
import com.example.actividad3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Punto3 extends AppCompatActivity {

    private final ArrayList<Product> data = new ArrayList<>();
    FloatingActionButton btnHome;
    ListView lvProducts;
    EditText etChange;
    Button btnFacturar;
    double changeFor;
    TextView tvFactura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.punto3);
        btnHome = findViewById(R.id.floatingActionButtonHomeAct3);
        lvProducts = findViewById(R.id.listViewProducts);
        etChange = findViewById(R.id.editTextNumberDecimal);
        btnFacturar = findViewById(R.id.buttonFacturar);
        tvFactura = findViewById(R.id.textViewFactura);
        generateListContent();
        lvProducts.setAdapter(new MyListAdaper(this, R.layout.list_item, data));
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("List item was clicked at " + position);
            }
        });
        btnHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Punto3.this, MainActivity.class));
            }
        });
        btnFacturar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    changeFor = Double.parseDouble(String.valueOf(etChange.getText()));
                    tvFactura.setText(genFactura(data, changeFor));
                } catch (NumberFormatException ex) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void generateListContent() {
        for (int i = 1; i < 10; i++) {
            data.add(new Product("Test" + i, 10000.0 * i, 0));
        }
    }

    private String genFactura(ArrayList<Product> data, double changeFor) {
        StringBuilder res = new StringBuilder("Cant.\tNom.\tUnit.\tPrice\n");
        double total = 0.0;
        for (Product p : data) {
            if (p.getQuantity() > 0) {
                double priceByUnits = p.getPrice() * p.getQuantity();
                res.append(p.getQuantity()).append("\t").append(p.getName()).append("\t$")
                        .append(p.getPrice()).append("\t$").append(priceByUnits).append("\n");
                total += priceByUnits;
            }
        }
        if (res.length() == 23) {
            return "Debe añadir productos al carrito";
        }
        if (changeFor < total) {
            return "Fondos Insuficientes";
        }
        double change = changeFor - total;
        res.append("Total: ").append(total).append("\n");
        res.append("Cambio: ").append(change);
        return res.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    private class MyListAdaper extends ArrayAdapter<Product> {
        private final int layout;
        private List<Product> mObjects;

        private MyListAdaper(Context context, int resource, List<Product> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.textViewNameItem = convertView.findViewById(R.id.textViewNameItem);
                viewHolder.textViewPriceLabel = convertView.findViewById(R.id.textViewPrice);
                viewHolder.textViewPriceValue = convertView.findViewById(R.id.textViewPriceValue);
                viewHolder.editTextNumber = convertView.findViewById(R.id.editTextNumber);
                viewHolder.floatingActionButtonSubs = convertView.findViewById(R.id.floatingActionButtonSubs);
                viewHolder.floatingActionButtonAdds = convertView.findViewById(R.id.floatingActionButtonAdds);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            ViewHolder finalMainViewholder = mainViewholder;
            mainViewholder.floatingActionButtonSubs.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.parseInt(String.valueOf(finalMainViewholder.editTextNumber.getText()));
                    if (num > 0) {
                        num -= 1;
                        finalMainViewholder.editTextNumber.setText(String.valueOf(num));
                        data.get(position).setQuantity(num);
                    }
                }
            });
            mainViewholder.floatingActionButtonAdds.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.parseInt(String.valueOf(finalMainViewholder.editTextNumber.getText()));
                    num += 1;
                    finalMainViewholder.editTextNumber.setText(String.valueOf(num));
                    data.get(position).setQuantity(num);
                }
            });
            mainViewholder.textViewNameItem.setText(getItem(position).getName());
            mainViewholder.textViewPriceValue.setText(String.valueOf(getItem(position).getPrice()));

            return convertView;
        }
    }

    public static class ViewHolder {

        TextView textViewNameItem;
        TextView textViewPriceLabel;
        TextView textViewPriceValue;
        EditText editTextNumber;
        FloatingActionButton floatingActionButtonSubs;
        FloatingActionButton floatingActionButtonAdds;
    }
}
