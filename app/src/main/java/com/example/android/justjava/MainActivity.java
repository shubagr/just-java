package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    CheckBox Whippedcream , chocolate;
    TextView  quantityTextView;
    Button myButton;
    EditText myinput;
    String name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        myButton = (Button)findViewById(R.id.order_button);
        Whippedcream = (CheckBox)findViewById((R.id.checkbox_whippedcream));
        chocolate = (CheckBox)findViewById((R.id.checkbox_choclate));
        myinput = (EditText)findViewById(R.id.enter_name);


    }
    //* This method is called when the minus button is clicked.

    /**
     * This method is called when the plus button is clicked.
     */
    // this method increase the no of quantity
    public void increment(View view) {
        if(quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        }
        else
            Toast.makeText(this, "you cannot have more than 100 cups of coffee", Toast.LENGTH_SHORT).show();

    }

    // this method decreases the value of the quantity till it is 1...
    public void decrement(View view) {
        if(quantity >=2) {


            quantity = quantity - 1;
            display(quantity);
        }
        else
            Toast.makeText(this, "you cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

       boolean haswhippedcream = Whippedcream.isChecked();
        boolean haschocolate = chocolate.isChecked();

        name = myinput.getText().toString();
        int price = calculatePrice(haswhippedcream, haschocolate);
        String priceMessage = createordersummary(name, price, haswhippedcream, haschocolate);


            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));//only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for" + name);
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

       /* Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:"));  // This ensures only SMS apps respond
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for" + name );
        intent.putExtra(Intent.EXTRA_STREAM, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/





       // displayPrice(quantity * 10);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        quantityTextView.setText("" + number);
    }




    // this method calculates the price.

    public int calculatePrice(boolean addwhippedcream , boolean addchocolate)
    {
        int baseprice=10;
        if(addwhippedcream)
        {
            baseprice +=2;
        }
        if (addchocolate)
        {
            baseprice += 3;
        }

        return quantity*baseprice;
    }
    // this function prints the oorder summary
    public String createordersummary(String name ,int price,boolean addwhippedcream, boolean addchocolate)
    {

        String pricemessage = "NAME :" + name;
        pricemessage += "\nAdd whipped cream?" + addwhippedcream;
        pricemessage += "\nAdd chocolate?" + addchocolate;
        pricemessage +=  "\nquantity: " + quantity;
        pricemessage +=   "\ntotal : $" + price;
        pricemessage +=  "\n thank u!";
        return pricemessage;

    }

   /* public void checkboxclicked(View view)
    {
        // is the check box clicked??
        boolean checked = ((CheckBox) view).isChecked();

        // check which check box is cliked?
        switch(view.getId())
        {
            case R.id.checkbox_whippedcream:
                if (checked)
                    // put some cream on coffee
                    else
                // remove the cream
                break;

        }
    }*/

   /* private String getAccountname()
    {
        return "android@gmail.com";
        //return "droid@gmail.com";
    }*/


}