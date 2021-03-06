package com.masteraj.android.inventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


/**
     * API Contract for the Pets app.
     */
    public final class InventoryContract {

        // To prevent someone from accidentally instantiating the contract class,
        // give it an empty constructor.
        private InventoryContract() {}

        /**
         * The "Content authority" is a name for the entire content provider, similar to the
         * relationship between a domain name and its website.  A convenient string to use for the
         * content authority is the package name for the app, which is guaranteed to be unique on the
         * device.
         */
        public static final String CONTENT_AUTHORITY = "com.masteraj.android.inventory";

        /**
         * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
         * the content provider.
         */
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

        /**
         * Possible path (appended to base content URI for possible URI's)
         * For instance, content://com.masteraj.android.pets/pets/ is a valid path for
         * looking at pet data. content://com.masteraj.android.pets/staff/ will fail,
         * as the ContentProvider hasn't been given any information on what to do with "staff".
         */
        public static final String PATH_INVENTORY = "inventory";

        /**
         * Inner class that defines constant values for the pets database table.
         * Each entry in the table represents a single pet.
         */
        public static final class InventoryEntry implements BaseColumns {

            /** The content URI to access the pet data in the provider */
            public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

            /**
             * The MIME type of the {@link #CONTENT_URI} for a list of pets.
             */
            public static final String CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

            /**
             * The MIME type of the {@link #CONTENT_URI} for a single pet.
             */
            public static final String CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

            /** Name of database table for pets */
            public final static String TABLE_NAME = "inventory";

            /**
             * Unique ID number for the pet (only for use in the database table).
             *
             * Type: INTEGER
             */
            public final static String _ID = BaseColumns._ID;

            /**
             * Name of the pet.
             *
             * Type: TEXT
             */
            public final static String COLUMN_INVENTORY_NAME ="name";

            /**
             * Breed of the pet.
             *
             * Type: TEXT
             */
            public final static String COLUMN_INVENTORY_SUPPLIER = "supplier";
            public final static String COLUMN_INVENTORY_EMAILID = "emailid";



            /**
             * price of the good.
             *
             * Type: INTEGER
             */
            public final static String COLUMN_INVENTORY_PRICE= "price";
            /**
             * quantity of the good.
             *
             * Type: INTEGER
             */
            public final static String COLUMN_INVENTORY_QUANTITY= "quantity";
            public final static String COLUMN_INVENTORY_PLATFORM= "platform";
            public final static String COLUMN_INVENTORY_IMAGE= "image";

            /**
             * Possible values for the PLATFORM of the pet.
             */
            public static final int PLATFORM_PC = 0;
            public static final int PLATFORM_XBOX = 2;
            public static final int PLATFORM_PS4 = 1;

            /**
             * Returns whether or not the given gender is {@link #GENDER_UNKNOWN}, {@link #GENDER_MALE},
             * or {@link #GENDER_FEMALE}.
             */
            public static boolean isValidPlatform(int gender) {
                if (gender == PLATFORM_PC || gender == PLATFORM_XBOX || gender == PLATFORM_PS4) {
                    return true;
                }
                return false;
            }

        }

    }



