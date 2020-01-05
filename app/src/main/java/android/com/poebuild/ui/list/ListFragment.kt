package android.com.poebuild.ui.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.com.poebuild.R
import android.widget.CompoundButton
import android.widget.ToggleButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel

    private lateinit var mRecyclerViewItems: RecyclerView
    private lateinit var mPassiveSkillFilter:ToggleButton
    private lateinit var mSkillGemFilter:ToggleButton
    private lateinit var mWeaponFilter:ToggleButton
    private lateinit var mArmourFilter:ToggleButton
    private lateinit var mOtherItemsFilter:ToggleButton

    lateinit var lastFilterClicked: CompoundButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.list_fragment, container, false)
        mRecyclerViewItems = view.findViewById(R.id.recyclerview)
        mPassiveSkillFilter = view.findViewById(R.id.toggleButton_passiveSkills)
        mSkillGemFilter = view.findViewById(R.id.toggleButton_skillGems)
        mWeaponFilter = view.findViewById(R.id.toggleButton_weapons)
        mArmourFilter = view.findViewById(R.id.toggleButton_armours)
        mOtherItemsFilter = view.findViewById(R.id.toggleButton_otherItems)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        initAdapter()
        initFilters()
    }

    fun initAdapter(){
        val adapter = ListAdapter()
        mRecyclerViewItems.layoutManager = LinearLayoutManager(context)
        mRecyclerViewItems.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, Observer { skills ->
            adapter.submitList(skills)
        })
    }

    fun initFilters(){
        mPassiveSkillFilter.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickFilter(isChecked,buttonView, ListViewModel.filter.PASSIVE_SKILLS)
        }

        mSkillGemFilter.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickFilter(isChecked,buttonView, ListViewModel.filter.SKILL_GEMS)
        }

        mOtherItemsFilter.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickFilter(isChecked,buttonView, ListViewModel.filter.OTHER_ITEMS)
        }

        mWeaponFilter.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickFilter(isChecked,buttonView, ListViewModel.filter.WEAPONS)
        }

        mArmourFilter.setOnCheckedChangeListener { buttonView, isChecked ->
            onClickFilter(isChecked,buttonView, ListViewModel.filter.ARMOURS)
        }

        //(R):Passive Skills by default
        lastFilterClicked = mPassiveSkillFilter
    }

    /* UI methods */

    fun onClickFilter(isChecked:Boolean, filterButton: CompoundButton, filter: ListViewModel.filter){
        if(isChecked){
            setActualFilter(filter)
            unDoLastFilterClicked(filterButton)
        }else if(lastFilterClicked == filterButton)
            unDoLastClick(filterButton)
    }

    fun unDoLastFilterClicked(filterButton: CompoundButton){
        val last = lastFilterClicked
        lastFilterClicked = filterButton
        Single.just(last.setChecked(false)).delay(100, TimeUnit.MILLISECONDS)
    }

    fun unDoLastClick(filterButton: CompoundButton){
        filterButton.isChecked = true
    }

    /* Other methods */

    fun setActualFilter(newFilter: ListViewModel.filter){
        viewModel.actualFilter.value = newFilter
    }
}
