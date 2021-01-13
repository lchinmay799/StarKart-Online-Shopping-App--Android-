package com.example.online_shopping;
import java.text.SimpleDateFormat;
import java.util.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


public class Database extends SQLiteOpenHelper {
    public Integer user;


    public Database(Context context) {
        super(context, "Shopping1" , null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE users (uid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,uname varchar(30),uemail varchar(30),contact INTEGER,password varchar(20))"
        );
        db.execSQL(
                "CREATE TABLE products (pid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,pname varchar(30),price INTEGER)"
        );
        db.execSQL(
                "CREATE TABLE cart (cid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,pid INTEGER,uid INTEGER, FOREIGN KEY(pid) REFERENCES products(pid),FOREIGN KEY(uid) REFERENCES users(uid))"
        );
        db.execSQL(
                "CREATE TABLE orders (oid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,pid INTEGER,odate datetime,uid INTEGER, FOREIGN KEY(pid) REFERENCES products(pid),FOREIGN KEY(uid) REFERENCES users(uid))"
        );
        db.execSQL(
                "CREATE TABLE address (aid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,uid INTEGER,street varchar(70),city varchar(20),state varchar(20),pincode INTEGER,FOREIGN KEY(uid) REFERENCES users(uid))"
        ); }
        public void insert() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO products(pname,price) VALUES('Boat Rockerz',1399)");
            db.execSQL("INSERT INTO products(pname,price) VALUES('JBL Stars 4',1499)");
            db.execSQL("INSERT INTO products(pname,price) VALUES ('Sannheiser',2999)");
            db.execSQL("INSERT INTO products(pname,price) VALUES('Sony',999)");
            db.execSQL("INSERT INTO products(pname,price) VALUES('HP Laptop',49999)");
            db.execSQL("INSERT INTO products(pname,price) VALUES ('ASUS Laptop',54999)");
            db.execSQL("INSERT INTO products(pname,price) VALUES('Lenovo Laptop',73999)");
            db.execSQL("INSERT INTO products(pname,price) VALUES('ACER Laptop',34999)");
            db.execSQL("INSERT INTO products(pname,price) VALUES ('APPLE Iphone 12',114000)");
            db.execSQL("INSERT INTO products(pname,price) VALUES('Samsung Mobile',29999)");
            db.execSQL("INSERT INTO products(pname,price) VALUES('ASUS ROG 3',41999)");
            db.execSQL("INSERT INTO products(pname,price) VALUES ('VIVO V9',23999)");
     }
    public boolean signup(String uname,String uemail,Long contact,String password)
    {
        if(!checkuser(uemail)) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(
                    "INSERT INTO users(uname,uemail,contact,password) VALUES ('" + uname + "','" + uemail + "'," + contact + ",'" + password + "')"
            );
            Cursor c = db.rawQuery(
                    "SELECT uid FROM  users WHERE uemail='"+uemail+"'", null
            );
            c.moveToFirst();
            user = Integer.parseInt(c.getString(0));
            db.execSQL("INSERT INTO address(uid) VALUES (" + user + ")");
            return true;
        }
        else
            return false;

    }
    public String getPrice(int ind)
    {
        if(ind!=-1) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery(
                    "SELECT price FROM  products WHERE pid="+ind,null
            );
            c.moveToFirst();
            return c.getString(0);
        }
        return "Out Of Stock";
    }

    public void addCart(int product)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO cart(pid,uid) VALUES("+product+","+user+")");
    }

    public String getcontact()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT contact FROM users WHERE uid="+user,null);
        c.moveToFirst();
        return c.getString(0);
    }

    public int[] getcart()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT pid FROM cart WHERE uid="+user,null);
        c.moveToFirst();
        int[] cartid=new int[c.getCount()];
        int i=0;
        do {
            cartid[i]=Integer.parseInt(c.getString(0));
            i+=1;
        }while(c.moveToNext());
        return cartid;
    }

    public void emptycart()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE  FROM cart WHERE uid="+user);
    }

    public String getmail()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT uemail FROM users WHERE uid="+user,null);
        c.moveToFirst();
        return c.getString(0);
    }

    public String[] userdetails()
    {
        String[] details=new String[7];
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT uname,uemail,contact FROM users WHERE uid="+user,null);
        c.moveToFirst();
        details[0]=c.getString(0);
        details[1]=c.getString(1);
        details[2]=c.getString(2);
        c=db.rawQuery("SELECT street,city,state,pincode FROM address WHERE uid="+user,null);
        c.moveToFirst();
        if(!c.isNull(0))
        {
            details[3]=c.getString(0);
            details[4]=c.getString(1);
            details[5]=c.getString(2);
            details[6]=c.getString(3);
        }
        else
            details[3]="NULL";
        return details;
    }

    public void addAddress(String[] address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE address SET street='"+address[0]+"',city='"+address[1]+"',state='"+address[2]+"',pincode="+address[3]+" WHERE uid="+user);
    }

    public void purchase(int product)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        db.execSQL("INSERT INTO orders(pid,odate,uid) VALUES("+product+",'"+formatter.format(date)+"',"+user+")");
    }

    public ArrayList<ArrayList<String>> orderdetails()
    {
        ArrayList<ArrayList<String>> item = new ArrayList<ArrayList<String>>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT products.pid,pname,price,odate from products JOIN orders WHERE products.pid==orders.pid and uid="+user,null);
        if(c.getCount()!=0) {
            c.moveToFirst();
            int i = 0;
            do {
                item.add(new ArrayList<String>());
                item.get(i).add(c.getString(0));
                item.get(i).add(c.getString(1));
                item.get(i).add(c.getString(2));
                item.get(i).add(c.getString(3));
                i += 1;
            } while (c.moveToNext());
        }
        return item;
    }

    public ArrayList<ArrayList<String>> cartdetails()
    {
        ArrayList<ArrayList<String>> item = new ArrayList<ArrayList<String>>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT cart.pid,pname,price from cart JOIN products WHERE products.pid==cart.pid and uid="+user,null);
        if(c.getCount()!=0) {
            c.moveToFirst();
            int i = 0;
            do {
                item.add(new ArrayList<String>());
                item.get(i).add(c.getString(0));
                item.get(i).add(c.getString(1));
                item.get(i).add(c.getString(2));
                i += 1;
            } while (c.moveToNext());
        }
        return item;
    }


    public boolean checkuser(String mail)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM users WHERE uemail='"+mail+"'",null);
        c.moveToFirst();
        if (c.getCount()==0)
            return false;
        return true;
    }

    public String login(String mail,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        if (checkuser(mail))
        {
            Cursor c = db.rawQuery(
                    "SELECT uid,uname,password FROM  users WHERE uemail='" + mail + "'", null
            );
            c.moveToFirst();
            user=Integer.parseInt(c.getString(0));
            String p = c.getString(2);
            String n = c.getString(1);
            if (p.equals(password))
                return n;
            return "False";
        }
        return "no";
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS cart");
        db.execSQL("DROP TABLE IF EXISTS products");
        db.execSQL("DROP TABLE IF EXISTS address");
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);

    }

}
