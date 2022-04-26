package com.mksoftware101.notes.legacy.features.noteList.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mksoftware101.notes.R

abstract class SwipeToDeleteCallback(context: Context) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    private val swipeThreshold = 0.5f
    private val backgroundCornerOffset = 16

    private val icon = ContextCompat.getDrawable(context, R.drawable.ic_delete_outline)
    private val background = ColorDrawable(context.getColor(R.color.red1))

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return swipeThreshold
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
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        val iconMetrics = Pair(icon?.intrinsicHeight ?: 0, icon?.intrinsicWidth ?: 0)
        val iconMargin = (itemView.height - iconMetrics.first) / 2
        val iconTop = itemView.top + (itemView.height - iconMetrics.first) / 2
        val iconBottom = iconTop + iconMetrics.first

        when {
            dX < 0 -> {
                val iconLeft = itemView.right - iconMargin - iconMetrics.second
                val iconRight = itemView.right - iconMargin
                icon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)

                background.setBounds(
                    itemView.right + dX.toInt() - backgroundCornerOffset,
                    itemView.top, itemView.right, itemView.bottom
                )
            }
            else -> {
                background.setBounds(0, 0, 0, 0)
            }
        }
        background.draw(c)
        icon?.draw(c)
    }
}