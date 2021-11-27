package com.example.scheduler.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.PaymentMethod;
import com.midtrans.sdk.corekit.core.SdkCoreFlowBuilder;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.BankType;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.CreditCard;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;

import java.util.ArrayList;

public class MakePayment extends AppCompatActivity implements TransactionFinishedCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
        }

        Button pay = findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                // Initialize Midtrans SDK
                initMidtransSdk();

                MidtransSDK.getInstance().setTransactionRequest(transactionRequest(
                        "1",
                        20000,
                        1,
                        "caleb"
                        //getIntent().getIntExtra("price", 0),
                        //getIntent().getIntExtra("qty", 0),
                        //getIntent().getStringExtra("name")
                ));
                MidtransSDK.getInstance().startPaymentUiFlow(MakePayment.this);

            }
        });
    }

    private void initMidtransSdk() {
        SdkUIFlowBuilder.init()
                .setContext(this)
                .setMerchantBaseUrl(BuildConfig.BASE_URL)
                .setClientKey(BuildConfig.CLIENT_KEY)
                .setTransactionFinishedCallback(this)
                .enableLog(true)
                .buildSDK();
    }

    public static TransactionRequest transactionRequest(String id, int price, int qty, String name) {
        TransactionRequest request = new TransactionRequest(System.currentTimeMillis() + " ", 24990);

        ItemDetails details = new ItemDetails(id, price, qty, name);

        ArrayList<ItemDetails> itemDetails = new ArrayList<>();
        itemDetails.add(details);
        request.setItemDetails(itemDetails);

        CreditCard creditCard = new CreditCard();
        creditCard.setSaveCard(false);
        creditCard.setBank(BankType.MANDIRI);
        request.setCreditCard(creditCard);

        return request;
    }

    @Override
    public void onTransactionFinished(TransactionResult transactionResult) {
        if (transactionResult.getResponse() != null) {
            switch (transactionResult.getStatus()) {
                case TransactionResult.STATUS_SUCCESS:
                    Toast.makeText(this, "Transaction Finished ID : " + transactionResult.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;
                case TransactionResult.STATUS_PENDING:
                    Toast.makeText(this, "Transaction Pending ID : " + transactionResult.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;
                case TransactionResult.STATUS_FAILED:
                    Toast.makeText(this, "Transaction Failed ID : " + transactionResult.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;

            }
            transactionResult.getResponse().getValidationMessages();
        } else if (transactionResult.isTransactionCanceled()) {
            Toast.makeText(this, "Transaction Canceled", Toast.LENGTH_SHORT).show();
        } else {
            if (transactionResult.getStatus().equalsIgnoreCase(TransactionResult.STATUS_INVALID)) {
                Toast.makeText(this, "Transaction Invalid", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Transaction Finished with failure", Toast.LENGTH_SHORT).show();
            }
        }
    }

}