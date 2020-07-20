package com.example.newsfeedapp.ui.fragment.wish_list

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeedapp.R
import com.example.newsfeedapp.common.searchQuery
import com.example.newsfeedapp.common.showDialog
import com.example.newsfeedapp.data.model.Article
import com.example.newsfeedapp.ui.adapter.NewsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_wish_list.*
import org.koin.android.viewmodel.ext.android.getViewModel


class WishListFragment : Fragment(R.layout.fragment_wish_list), NewsAdapter.Interaction,
    SearchView.OnQueryTextListener {

    lateinit var viewModel: FavouriteViewModel
    private val newsAdapter by lazy { NewsAdapter(this) }
    private lateinit var favList: MutableList<Article>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
        favList = mutableListOf()
        setHasOptionsMenu(true)
        setupRecyclerView()
        observeToFavLiveData()
        swipeToDelete(view)
    }

    private fun swipeToDelete(view: View) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

           override fun onMove(recyclerView: RecyclerView, iewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
               val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)

                Snackbar.make(view, getString(R.string.deleteArticle), Snackbar.LENGTH_LONG).apply {
                    setAction(getString(R.string.undo)) {
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(favNewsRecycler)
        }
    }

    private fun observeToFavLiveData() {
        viewModel.getSavedArticles()?.observe(viewLifecycleOwner, Observer { articles ->
            if (articles != null) {
                newsAdapter.differ.submitList(articles.reversed())
                favList.addAll(articles)
            }
        })


    }

    private fun setupRecyclerView() {
        favNewsRecycler.apply {
            adapter = newsAdapter
        }
    }

    override fun onItemSelected(position: Int, item: Article) {
        val action = WishListFragmentDirections.actionNavWishListToDetailsFragment(item)
        findNavController().navigate(action)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_deleteAll -> {

                if (favList.isNotEmpty())
                    showDialog(getString(R.string.deleteAll), getString(R.string.yes)
                        , DialogInterface.OnClickListener { dialog, which ->
                            viewModel.deleteAllArticles()
                            favList.clear()
                        }, getString(R.string.no), DialogInterface.OnClickListener { dialog, which ->
                            dialog.dismiss()
                        }, true
                    )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fav_menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        onQueryTextChange(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newsAdapter.differ.submitList(newText?.searchQuery(favList))
        return true
    }
}
