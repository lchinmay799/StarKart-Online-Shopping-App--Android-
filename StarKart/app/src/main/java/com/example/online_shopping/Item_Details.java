package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Item_Details extends AppCompatActivity {
    ImageView item ;
    TextView details,days;
    Integer index;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item__details);
        index= Integer.parseInt(getIntent().getStringExtra("index"));
         item = findViewById(R.id.itemimage);
         details =findViewById(R.id.details);
        int[] IMAGE_IDS = {R.drawable.boat, R.drawable.jbl, R.drawable.sannheiser,R.drawable.sony,
        R.drawable.hp,R.drawable.asus,R.drawable.lenovo,R.drawable.acer,
        R.drawable.apple,R.drawable.samsung,R.drawable.rog,R.drawable.vivo};
        String[] detail = {"    With Mic:Yes\n\n" +
                "    Connector type: 3.5 mm\n\n" +
                "    Foldable/ Collapsible: Designed for people always on the move, easy storage and easy to carry\n\n" +
                "    Extra bass: Add extra thump to your music\n\n" +
                "    One button universal remote allows you to answer and manage your calls effortlessly","    With Mic:Yes\n\n" +
                "    Bluetooth version: 4\n\n" +
                "    Wireless range: 8 m\n" +
                "    Battery life: 11 hrs | Charging time: 2 hrs\n\n" +
                "    Flatwire: Stays tangle free even in your pocket\n\n" +
                "    Foldable/ Collapsible: Designed for people always on the move, easy storage and easy to carry\n\n" +
                "    Extra bass: Add extra thump to your music","    With Mic:No\n\n" +
                "    Connector type: 3.5 mm\n\n" +
                "    Collapsible rotating ear-pieces\n\n" +
                "    Side coiled cable\n\n" +
                "    32 dB Noise Attenuation","   With Mic:No\n\n" +
                "    Connector type: 3.5 mm\n\n" +
                "    Flatwire: Stays tangle free even in your pocket\n\n" +
                "    Foldable/ Collapsible: Designed for people always on the move, easy storage and easy to carry\n\n" +
                "    well-balanced sound: hear your music as the artist intended","    Pre-installed Genuine Windows 10 OS\n\n" +
                "    Preloaded with MS Office\n\n" +
                "    Light Laptop without Optical Disk Drive\n\n" +
                "    15.6 inch HD WLED Backlit Brightview Display (220 nits Brightness, 101 ppi, 45% Color Gamut)","    Stylish & Portable Thin and Light Laptop\n\n" +
                "    14 inch Full HD LED Backlit Anti-glare TN Display (220 nits Brightness)\n\n" +
                "    Light Laptop without Optical Disk Drive","    Pre-installed Genuine Windows 10 OS\n\n" +
                "    Light Laptop without Optical Disk Drive\n\n" +
                "    15.6 inch HD LED Backlit Anti-glare Display (200 nits Brightness, 45% NTSC, 75% Screen-to-body Ratio)","    Stylish & Portable Thin and Light Laptop\n\n" +
                "    14 inch HD LED Backlit TFT Display\n\n" +
                "    Light Laptop without Optical Disk Drive","    64 GB ROM\n\n" +
                "    11.94 cm (4.7 inch) Retina HD Display\n\n" +
                "    12MP Rear Camera | 7MP Front Camera\n\n" +
                "    A13 Bionic Chip with 3rd Gen Neural Engine Processor\n\n" +
                "    Water and Dust Resistant (1 meter for Upto 30 minutes, IP67)\n\n" +
                "    Fast Charge Capable\n\n" +
                "    Wireless charging (Works with Qi Chargers | Qi Chargers are Sold Separately","    6 GB RAM | 64 GB ROM | Expandable Upto 512 GB\n\n" +
                "    16.26 cm (6.4 inch) Full HD+ Display\n\n" +
                "    64MP + 8MP + 5MP | 32MP Front Camera\n\n" +
                "    6000 mAh Lithium-ion Battery\n\n" +
                "    Exynos Octa Core Processor\n\n" +
                "    Super AMOLED Display","    12 GB RAM | 128 GB ROM\n\n" +
                "    16.74 cm (6.59 inch) Full HD+ Display\n\n" +
                "    64MP + 13MP + 5MP | 24MP Front Camera\n\n" +
                "    6000 mAh Lithium Polymer Battery\n\n" +
                "    Qualcomm Snapdragon 865+ (SM8250) Processor","    4 GB RAM | 64 GB ROM | Expandable Upto 256 GB\n\n" +
                "    16.0 cm (6.3 inch) Full HD+ Display\n\n" +
                "    16MP + 5MP | 24MP Front Camera\n\n" +
                "    3260 mAh Li-ion Battery\n\n" +
                "    Qualcomm Snapdragon 626 Processor"};
        details.setText(detail[index]);
        item.setImageResource(IMAGE_IDS[index]);
        TextView p=findViewById(R.id.price);
       price=getIntent().getStringExtra("price");
        if (!price.equals("Out Of Stock"))
            price="Rs "+price;
        p.setText(price);
    }

    public void buy(View v)
    {
        com.example.online_shopping.MainActivity.db.purchase(index+1);
        Intent intent = new Intent(this,Address.class);
        intent.putExtra("price", price);
        startActivity(intent);
    }
    public void addcart(View v)
    {
        com.example.online_shopping.MainActivity.db.addCart(index+1);
        Intent intent = new Intent(this,Cart.class);
        startActivity(intent);
    }

    public void share(View v)
    {
        String[] links={"https://www.flipkart.com/boat-rockerz-450-bluetooth-headset/p/itmdb79a9c0cb56f?pid=ACCFEHZ8GSGWMMSD&lid=LSTACCFEHZ8GSGWMMSDXIRNEY&marketplace=FLIPKART&srno=s_1_18&otracker=AS_QueryStore_OrganicAutoSuggest_2_4_na_na_na&otracker1=AS_QueryStore_OrganicAutoSuggest_2_4_na_na_na&fm=SEARCH&iid=4a4e7f5a-717f-496f-b7f2-7cbf1c15d3ad.ACCFEHZ8GSGWMMSD.SEARCH&ppt=sp&ppn=sp&ssid=eoli1jv0436indvk1609998814095&qH=dc6844f6027b0a32",
        "https://www.flipkart.com/jbl-t450bt-extra-bass-bluetooth-headset/p/itmed401fb9259c1?pid=ACCEXBNK9ZZMUN4Y&lid=LSTACCEXBNK9ZZMUN4Y6HLPG4&marketplace=FLIPKART&srno=s_1_11&otracker=search&otracker1=search&fm=SEARCH&iid=13da6742-8849-4659-956d-5cd44f980465.ACCEXBNK9ZZMUN4Y.SEARCH&ppt=sp&ppn=sp&ssid=8pinzgwom7zbvx1c1609998845646&qH=0e8eaa0d16605fd0",
        "https://www.flipkart.com/sennheiser-hd-280-pro-headphones-wired-headset-without-mic/p/itm4c3b2b7c12907?pid=ACCEEV5V7YG8UEEQ&lid=LSTACCEEV5V7YG8UEEQI8WX5N&marketplace=FLIPKART&srno=s_1_6&otracker=search&otracker1=search&fm=SEARCH&iid=894d3b79-9b19-4ba2-94df-a2a53386718b.ACCEEV5V7YG8UEEQ.SEARCH&ppt=sp&ppn=sp&ssid=c4jyx1mlop07nxfk1609998876878&qH=d286badf1dc5a5ab",
        "https://www.flipkart.com/sony-zx110a-wired-headset-without-mic/p/itm7fe99719b28a0?pid=ACCDZRSEYPFHAT76&lid=LSTACCDZRSEYPFHAT76A6MK3V&marketplace=FLIPKART&spotlightTagId=BestsellerId_0pm%2Ffcn&srno=s_1_1&otracker=search&otracker1=search&fm=SEARCH&iid=33ce3004-e38d-4441-82f7-2f85cabe5aba.ACCDZRSEYPFHAT76.SEARCH&ppt=sp&ppn=sp&ssid=8lp9qn23bf9icykg1609998892752&qH=a684a6245806d98f",
        "https://www.flipkart.com/hp-chromebook-x360-core-i3-10th-gen-8-gb-128-gb-emmc-storage-chrome-os-14c-ca0005tu-2-1-laptop/p/itm9ff842ba9a507?pid=COMFVDHW3VFQTYY2&lid=LSTCOMFVDHW3VFQTYY2U9REYJ&marketplace=FLIPKART&srno=s_1_23&otracker=search&otracker1=search&fm=SEARCH&iid=fd906f28-436e-4787-a1df-69ea55205589.COMFVDHW3VFQTYY2.SEARCH&ppt=sp&ppn=sp&ssid=n8qwak18ulieuozk1609998919781&qH=b71fcf18630c6f16",
        "https://www.flipkart.com/asus-vivobook-14-ryzen-5-quad-core-3500u-8-gb-512-gb-ssd-windows-10-home-x412da-ek501t-thin-light-laptop/p/itm534292c311d33?pid=COMFMCSBD8T53WEJ&lid=LSTCOMFMCSBD8T53WEJ0QMSW3&marketplace=FLIPKART&srno=s_1_1&otracker=search&otracker1=search&fm=SEARCH&iid=a679ce95-5161-4edf-9b8f-c3190788e84c.COMFMCSBD8T53WEJ.SEARCH&ppt=sp&ppn=sp&ssid=jtalw2jtzo557l6o1609998947216&qH=1d800f4fac0cf606",
        "https://www.flipkart.com/lenovo-ideapad-s340-core-i3-10th-gen-8-gb-256-gb-ssd-windows-10-home-s340-14iil-thin-light-laptop/p/itm13f71d077bba9?pid=COMFQX68TYCRCYKM&lid=LSTCOMFQX68TYCRCYKMGS7UBC&marketplace=FLIPKART&srno=s_1_5&otracker=search&otracker1=search&fm=SEARCH&iid=71bb2af3-fc2f-4b08-813e-28bb3e7431d1.COMFQX68TYCRCYKM.SEARCH&ppt=sp&ppn=sp&ssid=mvyp0rajmo3cketc1609998974918&qH=161c8e3fab4e96bc",
        "https://www.flipkart.com/acer-aspire-3-core-i5-10th-gen-4-gb-1-tb-hdd-windows-10-home-2-gb-graphics-a315-57g-laptop/p/itm4d67dd588820a?pid=COMFYFXF7SNDRHGH&lid=LSTCOMFYFXF7SNDRHGHX3RPHU&marketplace=FLIPKART&srno=s_1_7&otracker=search&otracker1=search&fm=SEARCH&iid=2e8ccf46-4959-43b6-b0cf-48f62ad8b1e7.COMFYFXF7SNDRHGH.SEARCH&ppt=sp&ppn=sp&ssid=x4h4aiqh5q888dts1609998996123&qH=69e6048ac3894abc",
        "https://www.flipkart.com/apple-iphone-11-green-128-gb/p/itm97529bbf640ca?pid=MOBFWQ6BHBKMH4BF&lid=LSTMOBFWQ6BHBKMH4BFPOHIFL&marketplace=FLIPKART&srno=s_1_6&otracker=AS_QueryStore_OrganicAutoSuggest_2_6_na_na_na&otracker1=AS_QueryStore_OrganicAutoSuggest_2_6_na_na_na&fm=SEARCH&iid=782fb2a4-db8e-4a12-8fa5-bfd040aa30cb.MOBFWQ6BHBKMH4BF.SEARCH&ppt=sp&ppn=sp&ssid=j5qn2jqjuxdn0wlc1609999023300&qH=cb603b9543d774e1",
        "https://www.flipkart.com/samsung-galaxy-f41-fusion-blue-128-gb/p/itm4769d0667cdf9?pid=MOBFV5PWG5MGD4CF&lid=LSTMOBFV5PWG5MGD4CFZ8YQJZ&marketplace=FLIPKART&srno=s_1_6&otracker=search&otracker1=search&fm=SEARCH&iid=2751895c-8f06-49dc-aa79-f548c44f3f8a.MOBFV5PWG5MGD4CF.SEARCH&ppt=sp&ppn=sp&ssid=0eud5pxonym870u81609999043323&qH=0258c7d48242959a",
        "https://www.flipkart.com/asus-rog-phone-3-black-128-gb/p/itmf8623d755871d?pid=MOBFUXPBV3TFMPAH&lid=LSTMOBFUXPBV3TFMPAH63D3RA&marketplace=FLIPKART&srno=s_1_1&otracker=search&otracker1=search&fm=SEARCH&iid=6faadb23-55d0-4b2e-a9d1-7691035c8229.MOBFUXPBV3TFMPAH.SEARCH&ppt=sp&ppn=sp&ssid=7hm8ia8zl38zk9hc1609999060149&qH=326099f5a8451bcc",
        "https://www.flipkart.com/vivo-s1-pro-dreamy-white-128-gb/p/itm60bf6c78dfe9a?pid=MOBFNENDE9G2EDSH&lid=LSTMOBFNENDE9G2EDSHWHIXD2&marketplace=FLIPKART&srno=s_1_1&otracker=search&otracker1=search&fm=SEARCH&iid=34db8c82-ccf1-4677-96fd-8b8801911f78.MOBFNENDE9G2EDSH.SEARCH&ppt=sp&ppn=sp&ssid=gom36p2sffcurr401609999084795&qH=278bf4990ddc16ab"};
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,links[index]);
        intent.setType("text/plain");
        startActivity(intent);
    }
    public static boolean isValidPinCode(String pinCode)
    {

        String regex
                = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";

        Pattern p = Pattern.compile(regex);

        if (pinCode == null) {
            return false;
        }


        Matcher m = p.matcher(pinCode);


        return m.matches();
    }
    public void getdays(View v)
    {
        EditText pin=findViewById(R.id.pincode);
        String p=pin.getText().toString();
        if (isValidPinCode(p)) {
            int b = (int) (Math.random() * (15 - 1 + 1) + 1);
            days = findViewById(R.id.days);
            days.setText("Product will be delivered within " + b + " days");
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid Pin Code \n Enter a Valid Pin Code", Toast.LENGTH_SHORT).show();

        }
    }
}