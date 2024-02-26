package com.shu.nasarovers.nasaList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shu.nasarovers.models.Photos
import com.shu.nasarovers.delegate.HorizontalAdapterDelegateImpl
import com.shu.nasarovers.delegate.NestedVerticalAdapter
import com.shu.nasarovers.delegate.VerticalAdapterDelegateImpl
import com.shu.nasarovers.R
import com.shu.nasarovers.databinding.FragmentFirstBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NasaListViewModel by viewModels()

    //первый адаптер
    //private val nasaAdapter = NasaAdapter{ photos -> onItemClick(photos) }

   // private val nasaAdapter = NasaNewAdapter { photos -> onItemClick(photos) }
    //адаптер с меню
    //private val nasaAdapter = NasaNewAdapter { view -> onMenuClick(view) }

    //Адаптер NasaListAdapter правильная установка слушателей.
//можно было сделать так , чтоб интерфейс L
   /*  private val nasaAdapter = NasaListAdapter(object : NasaListAdapter.Listener {
         override fun onClickPhoto(photos: Photos) {
             onItemClick(photos)
         }
         override fun onClickMenu(view: View) {
             //viewModel.clickMenu(view)
             popupMenus(view)
         }
     })*/

 /*   private val horizontalDelegate = HorizontalAdapterDelegateImpl()

    private val verticalDelegate = VerticalAdapterDelegateImpl(horizontalDelegate)*/

    private lateinit var adapter : NestedVerticalAdapter

    // private val nasaListAdapter = NasaListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NestedVerticalAdapter( VerticalAdapterDelegateImpl(HorizontalAdapterDelegateImpl()))
        binding.recyclerView.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.photos.onEach {
            //при использовании адаптера NasaNewAdapter()
            //nasaAdapter.setData(it)
            //при использовании NasaListAdapter
            //nasaAdapter.submitList(it)
            adapter.setData(it)

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isLoading.onEach {
            binding.swipeRefresh.isRefreshing = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.fab.setOnClickListener { addInfo() }
    }

    private fun onItemClick(item: Photos) {

    }

    private fun onMenuClick(view: View) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FirstFragment()
    }

    private fun addInfo() {
        val inflter = LayoutInflater.from(context)
        val v = inflter.inflate(R.layout.add_item, null)

        /**set view*/
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = context?.let { AlertDialog.Builder(it) }

        addDialog?.setView(v)
        addDialog?.setPositiveButton("Ok") { dialog, _ ->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            //userList.add(UserData("Name: $names","Mobile No. : $number"))
            //userAdapter.notifyDataSetChanged()
            Toast.makeText(context, "Adding User Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog?.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()

        }
        addDialog?.create()
        addDialog?.show()
    }


}