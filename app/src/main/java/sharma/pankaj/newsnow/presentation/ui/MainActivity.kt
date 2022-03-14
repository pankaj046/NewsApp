package sharma.pankaj.newsnow.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.coroutineScope
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import sharma.pankaj.newsnow.R
import sharma.pankaj.newsnow.comman.Keys
import sharma.pankaj.newsnow.comman.Resource
import sharma.pankaj.newsnow.data.model.Article
import sharma.pankaj.newsnow.databinding.ActivityMainBinding
import sharma.pankaj.newsnow.domain.utils.ActivityBinding
import sharma.pankaj.newsnow.domain.utils.PreferenceHandler
import sharma.pankaj.newsnow.presentation.viewmodels.NewsViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by ActivityBinding<ActivityMainBinding>(R.layout.activity_main)

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private val viewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var local: PreferenceHandler

    private val newsFilter =
        arrayListOf("Latest", "Technology", "Health", "Finance", "Arts", "Hacking", "Android")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        binding.newsRecyclerview.apply {
            adapter = newsAdapter
        }
        newsFilter.forEach { addChips(it, binding.newsType) }

        fetchData(newsFilter[0])
        binding.newsType.setOnCheckedChangeListener { chipGroup, i ->
            fetchData(newsFilter[i])
        }
    }


    private fun addChips(menu: String?, chipsGroup: ChipGroup) {
        val chip = Chip(this)
        chip.text = menu
        chip.setChipBackgroundColorResource(R.color.purple_700)
        chip.isCheckable = true
        chip.setTextColor(ContextCompat.getColor(this, R.color.white))
        chipsGroup.addView(chip)
    }

    private fun fetchData(key: String) {
        val url = "everything?q=${key.lowercase()}&apiKey=${Keys.apiKey}"
        viewModel.getNews(url)
    }

    private fun initObserver() {

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.newsList.collect {
                if (it.isLoading) {

                }
                if (it.error.isNotBlank()) {

                }
                it.data?.let { data ->
                    newsAdapter.updateNews(data as MutableList<Article>)
                }
            }
        }
    }

}