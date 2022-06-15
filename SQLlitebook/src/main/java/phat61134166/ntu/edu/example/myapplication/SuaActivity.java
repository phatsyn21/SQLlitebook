package phat61134166.ntu.edu.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class SuaActivity extends AppCompatActivity {
    SQLiteDatabase databaseBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);

//        databaseBook = SQLiteDatabase.openOrCreateDatabase("/data/data/ntu.bookman/MyBook.db",null);
//
//        // Lấy về Intent sửa đã gửi
//        Intent intentSS = getIntent();
//        // Lấy ra dữ liệu đã gói thông qua key là masach
//        //
//        int maSachSua = intentSS.getIntExtra("masach",0);
//        //
//        // Lấy các thông tin hiện tại, fill vào các điều khiển
//        String sqlChon = "SELECT * FROM BOOKS";
//        String[] thamsoTruyen ={String.valueOf(maSachSua)};
//        Cursor cs= databaseBook.rawQuery("SELECT * FROM BOOKS where BookID=?",thamsoTruyen);
//        int ma= cs.getInt(0);
//        String ten =cs.getString(1);
//
//        // Lấy các thông tin từ điều khiển, truyền vào cho hàm cập nhật
//
//    }
//    void CapNhat(int maGOC, String tenMOI, int sotrangMOI, float giaMOI, String motaMOI)
//    {
//        String[] thamSoTruyen={String.valueOf(maGOC)};
//        ContentValues row = new ContentValues();
//        // put (key, value)     key=tên cột, value= giá trị
//        row.put("BookName", tenMOI);
//        row.put("Page", sotrangMOI);
//        row.put("Price", giaMOI);
//        row.put("Description", motaMOI);
//        //
//        int kq = databaseBook.update("BOOKS",row,"BookID=?",thamSoTruyen);
//        if (kq ==0)
//            Toast.makeText(this, "Không cập nhật được",Toast.LENGTH_LONG).show();
//        else
//        {
//            Toast.makeText(this, "Cập nhật thành công",Toast.LENGTH_LONG).show();
//        }
    }
}
