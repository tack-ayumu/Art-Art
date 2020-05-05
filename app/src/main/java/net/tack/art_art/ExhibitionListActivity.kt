package net.tack.art_art

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_exhibition_list.*

class ExhibitionListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exhibition_list)

        val recyclerView = recyclerView_museumlist
        val adapter = ViewAdapter(createDataList(), object : ViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                this.onClickRow(tappedView, rowModel)
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun createDataList(): List<RowModel> {

        val catchData = intent.getStringArrayListExtra(EXTRA_RESULTS)


    }




    fun onClickRow(tappedView: View, rowModel: RowModel) {
        Snackbar.make(
            tappedView,
            "Replace with your own action tapped ${rowModel.title}",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null).show()
    }


}