package com.example.studentlist

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Danh sách mẫu của sinh viên
        studentList = listOf(
            Student("Nguyen Van A", "12345"),
            Student("Tran Thi B", "67890"),
            Student("Le Van C", "66357"),
            Student("Pham Luu Minh H", "26225"),
            Student("Le Minh Viet A", "77215"),
            Student("Pham Anh Tuan Cu T", "88463"),
            Student("Nguyen Huy K", "11111"),
        )

        // Thiết lập RecyclerView và Adapter
        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)

        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Lắng nghe thay đổi trong ô tìm kiếm
        searchEditText.addTextChangedListener { text ->
            val keyword = text.toString().trim()
            if (keyword.length > 2) {
                // Lọc danh sách sinh viên dựa trên tên hoặc MSSV chứa từ khóa
                val filteredList = studentList.filter {
                    it.name.contains(keyword, ignoreCase = true) || it.mssv.contains(keyword)
                }
                studentAdapter.updateList(filteredList)
            } else {
                // Hiển thị toàn bộ danh sách khi từ khóa có ít hơn 3 ký tự
                studentAdapter.updateList(studentList)
            }
        }
    }
}
