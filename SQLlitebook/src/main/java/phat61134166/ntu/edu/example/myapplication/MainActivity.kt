package phat61134166.ntu.edu.example.myapplication

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var databaseBook: SQLiteDatabase? = null
    var ktra = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Tạo mới/mở CSDL
        ///data/data/ntu.bookman
        databaseBook =
            SQLiteDatabase.openOrCreateDatabase("/data/data/ntu.sqllite_book/MyBook.db", null)
        // Ta che hàm sau lại, ở những lần chạy sau
        // vì ta không muốn tạo CDSL lại từ đầu
        //TaoBangVaThemDuLieu();

        // ThemMoiSach(10,"Mạng máy tính",50,10,"Sách về Mạng");
        //
        // CapNhat(2,"Lập trình A đây rồi", 100, 500,"Sách quí");
//        if(databaseBook!=null){
        NapSACHvaoListview()
        //        }
        val nutThem = findViewById<View>(R.id.btnThem) as Button
        val nutSua = findViewById<View>(R.id.btnSua) as Button
        val nutXoa = findViewById<View>(R.id.btnXoa) as Button
        val edChon = findViewById<View>(R.id.edtMaCHON) as TextView
        //        TextView TenSach = (TextView) findViewById(R.id.tvTenSach);
        // Xử lý
        nutXoa.setOnClickListener {
            val maSach = edChon.text.toString()
            val ma = maSach.toInt()
            XoaSach(ma)
        }
        //        nutSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Lấy mã sách vừa chọn
//                String maSach = edChon.getText().toString();
//                // Tạo intent
//                Intent intentSua = new Intent();
//                // gói dữ liệu
//                intentSua.putExtra("masach", maSach);
//                // Khởi động SuaACtivity
//                startActivity(intentSua);
//            }
//        });
        nutThem.setOnClickListener {
            val intentThem = Intent(this@MainActivity, ThemActivity::class.java)
            startActivity(intentThem)
        }
    }

    //    public void themsach(View v){
    //        Intent nhapsach = new Intent(this, ThemActivity.class);
    //        startActivity(nhapsach);
    //    }
    fun ThemMoiSach(ma: Int, ten: String, sotrang: Int, gia: Float, mota: String) {
        val sqlThem = "INSERT INTO BOOKS VALUES( " + ma + "," +
                "'" + ten + "'," +
                sotrang + "," +
                gia + "," +
                "'" + mota + "')"
        databaseBook!!.execSQL(sqlThem)
        val row = ContentValues()
        // put (key, value)     key=tên cột, value= giá trị
        row.put("BookID", ma)
        row.put("BookName", ten)
        row.put("Page", sotrang)
        row.put("Price", gia)
        row.put("Description", mota)
        val kq = databaseBook!!.insert("BOOKS", null, row)
        if (kq == -1L) Toast.makeText(this, "Không thêm được", Toast.LENGTH_LONG).show() else {
            Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_LONG).show()
        }
    }

    fun XoaSach(ma: Int) {
        val thamSoTruyen = arrayOf(ma.toString())
        val kq = databaseBook!!.delete("BOOKS", "BookID=?", thamSoTruyen)
        if (kq == 0) Toast.makeText(this, "Không xóa  được", Toast.LENGTH_LONG).show() else {
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_LONG).show()
        }
        NapSACHvaoListview()
    }

    //    void CapNhat(int maGOC, String tenMOI, int sotrangMOI, float giaMOI, String motaMOI) {
    //        String[] thamSoTruyen = {String.valueOf(maGOC)};
    //        ContentValues row = new ContentValues();
    //        // put (key, value)     key=tên cột, value= giá trị
    //        row.put("BookName", tenMOI);
    //        row.put("Page", sotrangMOI);
    //        row.put("Price", giaMOI);
    //        row.put("Description", motaMOI);
    //        //
    //        int kq = databaseBook.update("BOOKS", row, "BookID=?", thamSoTruyen);
    //        if (kq == 0)
    //            Toast.makeText(this, "Không cập nhật được", Toast.LENGTH_LONG).show();
    //        else {
    //            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
    //        }
    //    }
    fun NapSACHvaoListview() {
        //1. Lấy tham chiếu đến Listview trong Layout
        val listView = findViewById<View>(R.id.lvSACH) as ListView
        //2. Nguồn dữ liệu
        // Mỗi phần tử là một String, gồm:mã+tên+giá
        val dsSach = ArrayList<String?>()
        // Mở DB, select dữ liệu, đưa vào ArratList
        ///----------------
        val cs = databaseBook!!.rawQuery("SELECT * FROM BOOKS", null)
        cs.moveToFirst()
        // duyệt từng dòng (bản ghi), tính toán
        while (true) {
            if (cs.isAfterLast == true) break
            // Lấy mã sách
            val ms = cs.getInt(0) // Cột 0, ở dòng hiện tại
            // Lấy tên sách
            val tenSach = cs.getString(1)
            // Lấy giá bán
            val gia = cs.getFloat(3)
            // Nối lại, để đưa vào arraylist
            val dong = ms.toString() + "--- " +
                    tenSach + " ----" + gia.toString()
            // đưa vào arrayList
            dsSach.add(dong)
            // Sang dòng (bản ghi tiếp)
            cs.moveToNext()
        }

        ///---------------
        //3. Adapter
        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_list_item_1,
            dsSach as List<Any?>
        );
        //Gắn vào Listview
        listView.adapter = adapter
        // Bắt và xử lý sự kiện
        listView.onItemClickListener =
            OnItemClickListener { adapterView, view, i, l -> //                String dongChon = dsSach.get(i);
                // có dạng:   8 -- Tên sách  -- ...
                // Xử lý tách lấy phần mã
                //                int k = dongChon.indexOf(" "); // Tìm vị trí xuất hiện đầu tiên của khoảng trắng
                //                String ma = dongChon.substring(0,k);  // trích lấy phần mã
                val edChon = findViewById<View>(R.id.edtMaCHON) as TextView
                edChon.text = (i + 1).toString()
                val Chon = dsSach[i]
                val start = Chon!!.indexOf("- ") + 2
                val end = Chon.indexOf(" -")
                val ten = Chon.substring(start, end)
                val TenSach = findViewById<View>(R.id.tvTenSach) as TextView
                TenSach.text = ten
            }
    }

    //================================================
    fun TaoBangVaThemDuLieu() {
        //Lệnh tạo bảng
        // sqlXoaBang nếu đã có
        if (ktra) ktra = false else if (ktra == false) return
        val sqlXoaBang = "DROP TABLE IF EXISTS BOOKS"
        databaseBook!!.execSQL(sqlXoaBang)
        val sqlTaoBang = "CREATE TABLE BOOKS(     BookID integer PRIMARY KEY, " +
                "   BookName text, " +
                "   Page integer, " +
                "   Price Float, " +
                "   Description text)"
        databaseBook!!.execSQL(sqlTaoBang)
        // Thêm bản ghi
        val sqlThem1 = "INSERT INTO BOOKS VALUES(1, 'Java', 100, 9.99, 'Sách về java')"
        databaseBook!!.execSQL(sqlThem1)
        val sqlThem2 = "INSERT INTO BOOKS VALUES(2, 'Android', 320, 19.00, 'Android cơ bản')"
        databaseBook!!.execSQL(sqlThem2)
        val sqlThem3 = "INSERT INTO BOOKS VALUES(3, 'Học làm giàu', 120, 0.99, 'sách đọc cho vui') "
        databaseBook!!.execSQL(sqlThem3)
        val sqlThem4 =
            "INSERT INTO BOOKS VALUES(4, 'Từ điển Anh-Việt', 1000, 29.50, 'Từ điển 100.000 từ')"
        databaseBook!!.execSQL(sqlThem4)
        val sqlThem5 = "INSERT INTO BOOKS VALUES(5, 'CNXH', 1, 1, 'chuyện cổ tích')"
        databaseBook!!.execSQL(sqlThem5)
    }
}