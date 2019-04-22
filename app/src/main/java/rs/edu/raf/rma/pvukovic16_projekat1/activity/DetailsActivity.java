package rs.edu.raf.rma.pvukovic16_projekat1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rs.edu.raf.rma.pvukovic16_projekat1.R;

public class DetailsActivity extends AppCompatActivity {

    private static final String URL = "https://picsum.photos/400/600/?random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  // hide the title
        getSupportActionBar().hide();                   // hide the title bar
        setContentView(R.layout.activity_details);

        init();
    }

    private void init() {
        TextView nameTv = findViewById(R.id.tv_det_name);
        TextView catTv = findViewById(R.id.tv_det_cat);
        TextView costTv = findViewById(R.id.tv_det_cost);
        TextView dateTv = findViewById(R.id.tv_det_date);
        ImageView imageView = findViewById(R.id.iv_det);
        Button rmbtn = findViewById(R.id.btn_det_remove);

        nameTv.setText(getIntent().getStringExtra("expenseName"));
        catTv.setText(getString(R.string.det_cat, getIntent().getStringExtra("expenseCategory")));
        costTv.setText(getString(R.string.det_cost, getIntent().getIntExtra("expenseCost", 0)));
        dateTv.setText(getString(R.string.det_date, getIntent().getStringExtra("expenseDate")));

        Picasso.get().load(URL).into(imageView);
        rmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("expenseId", getIntent().getIntExtra("expenseId", -1));
                setResult(1, intent);
                finish();
            }
        });

    }

}
