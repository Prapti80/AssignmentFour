package com.example.assignmentfour.ui.theme

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class FamilyExpandableAdapter(
    private val context: Context,
    private val familyNames: List<String>,
    private val familyDetails: Map<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int = familyNames.size

    override fun getChildrenCount(groupPosition: Int): Int =
        familyDetails[familyNames[groupPosition]]?.size ?: 0

    override fun getGroup(groupPosition: Int): String = familyNames[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): String =
        familyDetails[familyNames[groupPosition]]?.get(childPosition) ?: ""

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long =
        (groupPosition * 100 + childPosition).toLong()

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = getGroup(groupPosition)
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = getChild(groupPosition, childPosition)
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}
