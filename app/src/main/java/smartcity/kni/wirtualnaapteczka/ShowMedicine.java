public class ShowMedicineInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_medicine_info);

        SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
        Medicine medicie = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id",0));

        EditText nameOfMedicine = (EditText) findViewById(R.id.name_Of_Medicine_Info_EditText);
        nameOfMedicine.setText(medicie.getName());

        TextView descriptionOfMedicine = (TextView) findViewById(R.id.decsription_From_Medicin_Info_TextView);
        descriptionOfMedicine.setText(medicie.getDescription());

        EditText barcodeOfMedicine = (EditText) findViewById(R.id.barcode_From_Medicine_Info_EditText);
        barcodeOfMedicine.setText(medicie.getEAN());

        Button deleteButton = (Button) findViewById(R.id.delete_Medicine_Info_Button);

        Button updateButton = (Button) findViewById(R.id.modify_Medicine_Info_Button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowMedicineInfoActivity.this);
                builder.setTitle("Usuń");
                builder.setMessage("Czy na pewno chcesz usunać lek?");
                builder.setPositiveButton("TAK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // here in case of positive ansver program is closeing
                        SQLiteDatabaseHelper.getInstance().deleteMedicine(getIntent().getLongExtra("Id", 0));
                        startActivity(new Intent(ShowMedicineInfoActivity.this, ActivityMedicineList.class));
                    }
                });
                builder.setNegativeButton("NIE", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ShowMedicineInfoActivity.this,EditMedicineFromActivity.class);

                Long medicineId = getIntent().getLongExtra("Id", 0);
                intent.putExtra("Id",medicineId);

                startActivity(intent);
            }
        });
    }
}