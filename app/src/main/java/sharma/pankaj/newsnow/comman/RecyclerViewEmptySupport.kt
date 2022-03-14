package sharma.pankaj.newsnow.comman

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewEmptySupport : RecyclerView {
    private var emptyView: View? = null
    private var isErrorShow = false
    private val emptyObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            val adapter = adapter
            if (adapter != null && emptyView != null) {
                if (adapter.itemCount == 0) {
                    if (isErrorShow) {
                        isErrorShow = false
                    }
                    emptyView!!.visibility = VISIBLE
                    this@RecyclerViewEmptySupport.visibility = GONE
                } else {
                    isErrorShow = true
                    emptyView!!.visibility = GONE
                    this@RecyclerViewEmptySupport.visibility = VISIBLE
                }
            }
        }
    }

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(emptyObserver)
        emptyObserver.onChanged()
    }

    fun setEmptyView(emptyView: View?) {
        this.emptyView = emptyView
    }
}