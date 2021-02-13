//==========================================================================
//
// Application: Homework 1
// Activity:    Activity Main
// Course:      CSC 4330
// Homework:    1
// Author:      MD Bakhtiar R Akhib
// Date:        02/08/2021
// Description: This is an android app for Book Bugs that lets user to
//              order e-books from the company. It takes user name and
//              email as user input and also the book they want and the
//              Payment type.
//
//===========================================================================

package edu.wsu;

//Importing packages
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

//---------------------------------
// class ActMain
//---------------------------------
public class ActMain extends AppCompatActivity {

    //-------------------------------------------
    // Constants and variables
    //-------------------------------------------
    private EditText txtBookSelected;
    private EditText txtBookPrice;
    private EditText txtSalesTax;
    private EditText txtTotalCost;
    private EditText txtName;
    private EditText txtEmail;
    private RadioButton rbDebit;
    private RadioButton rbCredit;
    private RadioButton rbPayPal;
    private String radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laymain);

        //Getting the ids by value and storing them into objects
        txtBookSelected = findViewById(R.id.txtBookSelected);
        txtBookPrice = findViewById(R.id.txtBookPrice);
        txtSalesTax = findViewById(R.id.txtSalesTax);
        txtTotalCost = findViewById(R.id.txtTotalCost);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        rbDebit = findViewById(R.id.rbDebit);
        rbCredit = findViewById(R.id.rbCredit);
        rbPayPal = findViewById(R.id.rbPaypal);
    }

    //----------------------------------------------------
    // Show List Dialog Box
    //----------------------------------------------------
    final String[] books = {"A Time To Kill, $7.99",           //Storing the book info in an array
            "The Lion, the Witch and the Wardrobe, &12.99",
            "The Little Prince, $5.99",
            "East of Eden, $10.99",
            "A Scanner Darkly, $5.99",
            "Brave New World, $8.99",
            "The Fault In Our Stars, $14.95",
            "IN Cold Blood, $5.50"};

    public void showListDialogBox(View v){
        //Building a new dialogue box
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Select A Book");
        builder.setItems(books, new DialogInterface.OnClickListener() {
            @Override
            //Overriding the onClick function
            public void onClick(DialogInterface dialog, int which) {
                //Storing the selected value from the list in a variable
                String s = books[which];

                //Splitting the value for getting the name and the price
                //And storing the result in an array
                String[] bookSplitter = s.split("[,$]");

                //The value at the 2nd index value is the price so parsing that
                double price = Double.parseDouble(bookSplitter[2]);

                //Calulating the tax based on the price
                double tax = (price * 6) / 100;

                //The total price is the addition of the price and tax
                double Total = price + tax;

                //Setting the text for book name as the value at index 0 of the array is the name
                txtBookSelected.setText(bookSplitter[0]);

                //Setting the price of the book
                txtBookPrice.setText("$" + String.valueOf(price));

                //Setting the tax of the book to 2 decimal places
                txtSalesTax.setText("$" + (String.format("%.2f",tax)));

                //Setting the total price of the book to 2 decimal places
                txtTotalCost.setText("$" + (String.format("%.2f",Total)));

                //Setting a Toast message to let the user know of the text book selected
                Toast.makeText(getApplicationContext(), "List of books: \"" + books[which] + "\" selected.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    //-------------------------------------------------------------
    // On Radio Button Click
    //------------------------------------------------------------
    public void onRadioButtonCLicked(View v){
        //Declaring variables
        boolean checked = ((RadioButton) v).isChecked();

        //Switch statements to check the current radio selected
        switch (v.getId()){
            case R.id.rbDebit:
                //if selected Debit the variable radio will be set as Debit
                if(checked)
                    radio = "Debit";
                break;
            case R.id.rbCredit:
                //if selected Debit the variable radio will be set as Credit
                if(checked)
                    radio = "Credit";
                break;
            case R.id.rbPaypal:
                //if selected Debit the variable radio will be set as PayPal
                if(checked)
                    radio = "PayPal";
                break;
            default:
                //Default to set as no payment
                radio = "No Payment method selected";
                break;
        }
    }

    //---------------------------------------------------
    // On Clicking submit
    //---------------------------------------------------
    public void onSubmitClicked(View v){
        //Output toast message to show the details of the submission
        //Splitting the string by \n to go to the next line
        Toast.makeText(getApplicationContext(), "Your order has been requested " +
                "\nYour Order information:" +
                "\n Customer Name: " + txtName.getText().toString() +
                "\n Customer Email: " + txtEmail.getText().toString() +
                "\n Book Selected: " + txtBookSelected.getText().toString() +
                "\n Book Price: " + txtBookPrice.getText().toString() +
                "\n Sales Tax: " + txtSalesTax.getText().toString() +
                "\n Total Cost: " + txtTotalCost.getText().toString() +
                "\n Payment Type: " + radio, Toast.LENGTH_LONG).show();
    }

    public void onResetClicked(View v){
        //Setting dialog box to ask if the user wants to reset or no
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Are you sure you want to reset all the fields?");
        builder.setMessage("If you do, you would have to fill everything again");

        //IF the user decides to reset this will be executed
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            //onClick function resets every field to previous
            public void onClick(DialogInterface dialog, int which) {
                txtName.setText("");
                txtEmail.setText("");
                txtBookSelected.setText("");
                txtBookPrice.setText("");
                txtSalesTax.setText("");
                txtBookPrice.setText("");
                txtTotalCost.setText("");

                //setting all the radio to checked: false
                rbDebit.setChecked(false);
                rbCredit.setChecked(false);
                rbPayPal.setChecked(false);
                radio = "No Payment method selected";

                //Toast message to show that reset was executed
                Toast.makeText(getApplicationContext(),"All the fields have been reset!", Toast.LENGTH_SHORT).show();
            }
        });

        //If the user selects no this will be executed
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do nothing just show a toast message that nothing was changed
                Toast.makeText(getApplicationContext(),"Nothing changed!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }


}