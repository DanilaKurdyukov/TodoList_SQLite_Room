package com.example.sqlliteapp.data

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlliteapp.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


abstract class ItemTouchHelperCallback(var context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {


    val deleteColor = ContextCompat.getColor(context, R.color.delete_color)
    val deleteIcon =  R.drawable.baseline_delete_24
    val editColor = ContextCompat.getColor(context, R.color.edit_color)
    val editIcon = R.drawable.baseline_edit_note_24

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeLeftBackgroundColor(deleteColor)
            .addSwipeLeftPadding(0,30f,20f,30f)
            .addSwipeLeftCornerRadius(0,20f)
            .addSwipeLeftActionIcon(deleteIcon)
            .addSwipeRightActionIcon(editIcon)
            .addSwipeRightBackgroundColor(editColor)
            .addSwipeRightPadding(0,30f,20f,30f)
            .addSwipeRightCornerRadius(0,20f)
            .create()
            .decorate()

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.5f
    }

}