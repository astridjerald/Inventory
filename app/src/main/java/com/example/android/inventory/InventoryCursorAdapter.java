package com.example.android.inventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.android.inventory.data.InventoryContract;
import com.example.android.inventory.data.InventoryContract.InventoryEntry;


import com.example.android.inventory.data.InventoryContract.InventoryEntry;
import com.example.android.inventory.data.InventoryProvider;

/**
 * {@link PetCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class InventoryCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link PetCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }
private int Quantity;
    private String Pricy;
    private String Name;
    private Bitmap bp;
    private TextView quantityTextView;
    private TextView nameTextView;
    private TextView priceTextView;
    private ImageView gamepicview;
    private Context contextu;
    private Uri currentItemUri;
    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        nameTextView = (TextView) view.findViewById(R.id.name);
        quantityTextView = (TextView) view.findViewById(R.id.quantity);
        priceTextView = (TextView) view.findViewById(R.id.price);
        Button salebutton = (Button) view.findViewById(R.id.sale);
        gamepicview=(ImageView) view.findViewById(R.id.itemimage);
        long id = cursor.getLong(cursor.getColumnIndex(InventoryEntry._ID));
        currentItemUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);
        contextu=context;
        // Find the columns of pet attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRICE);
        int picColumnIndex=cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_IMAGE);

        // Read the pet attributes from the Cursor for the current pet
        Name = cursor.getString(nameColumnIndex);
        Quantity = cursor.getInt(quantityColumnIndex);
        float Price = cursor.getFloat(priceColumnIndex);



            byte[] ImageIndex=cursor.getBlob(picColumnIndex);
            bp=convertToBitmap(ImageIndex);
            gamepicview.setImageBitmap(bp);


        Pricy = Float.toString(Price) + " $";
        String Quantitty = Integer.toString(Quantity) + " copies left";
        nameTextView.setText(Name);
        quantityTextView.setText(Quantitty);
        priceTextView.setText(Pricy);

        salebutton.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
if(Quantity>=1){
    Quantity--;

    setter();
}
else{
    Toast.makeText(contextu,"Restock Copies", Toast.LENGTH_SHORT).show();
}
            }});


        // If the pet breed is empty string or null, then use some default text
        // that says "Unknown breed", so the TextView isn't blank.
//        if (TextUtils.isEmpty(petBreed)) {
//            petBreed = context.getString(R.string.unknown_breed);
//        }

        // Update the TextViews with the attributes for the current pet
    }
    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);

    }
        private void setter()
        {
            ContentValues values = new ContentValues();
            values.put(InventoryEntry.COLUMN_INVENTORY_QUANTITY, Quantity);
            contextu.getContentResolver().update(currentItemUri, values, null, null);
            String Quantitty = Integer.toString(Quantity) + " copies left";;
            quantityTextView.setText(Quantitty);

        }

}
