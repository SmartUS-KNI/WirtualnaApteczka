package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AddNewPackActivity extends AppCompatActivity {

    EditText packSizeEditText;
    AutoCompleteTextView packSizeUnitAcTextView;
    EditText barcodeEditText;
    ImageButton cameraScannerImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pack);

        setTitle(getResources().getString(R.string.title_activity_add_new_pack));

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_medicine_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void initViews() {
        packSizeEditText = (EditText) findViewById(R.id.pack_size);
        packSizeUnitAcTextView = (AutoCompleteTextView) findViewById(R.id.pack_unit);
        barcodeEditText = (EditText) findViewById(R.id.barcode_From_New_Medicine_EditText);
        initCameraScanner();

    }

    private void initCameraScanner() {

        cameraScannerImageButton = (ImageButton) findViewById(R.id.barcode_From_Camera);

        cameraScannerImageButton.setOnClickListener(v -> {
            IntentIntegrator integrator = new IntentIntegrator(AddNewPackActivity.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
            integrator.setPrompt(getString(R.string.prompt_for_barcode_scanner));
            integrator.setCameraId(0);
            integrator.setBarcodeImageEnabled(false);
            integrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, getString(R.string.cancelled_scanner_barcode), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getString(R.string.correct_scanner_barcode), Toast.LENGTH_LONG).show();
                barcodeEditText.setText(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
