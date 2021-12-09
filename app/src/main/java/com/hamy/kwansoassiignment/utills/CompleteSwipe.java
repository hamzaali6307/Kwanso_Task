
package com.hamy.kwansoassiignment.utills;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.hamy.kwansoassiignment.R;

public class CompleteSwipe extends ItemTouchHelper.SimpleCallback
{
	private final OnCompleteSwipeListener listener;
	private final Context context;

	private final int backgroundColor;
    private final int deleteColor;
    private final int iconPadding;

	public static float CIRCLE_ACCELERATION = 3;

	public CompleteSwipe(int dragDirs, int swipeDirs, Context context, OnCompleteSwipeListener listener) {
		super(dragDirs, swipeDirs);
		this.context = context;
		this.listener = listener;
        backgroundColor = ContextCompat.getColor(context, R.color.white);
        deleteColor = ContextCompat.getColor(context, R.color.teal_700);
        iconPadding = 10;
	}

	@Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
	{
        return true;
    }

	@Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
	{
        listener.onSwiped(viewHolder, viewHolder.getAdapterPosition());
    }

	@Override
	public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder)
	{
        return makeMovementFlags(0, ItemTouchHelper.START);
	}

	@Override
	public float getSwipeEscapeVelocity(float defaultValue) {
		return defaultValue * 5f;
	}

	@Override
	public boolean isLongPressDragEnabled()
	{
		return false;
	}

	@Override
	public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive)
	{
		if (dX == 0f) 
		{
			super.onChildDraw(canvas,
					recyclerView,
					viewHolder,
					dX,
					dY,
					actionState,
					isCurrentlyActive);
			return;
        }

		float left = viewHolder.itemView.getLeft();
        float top = viewHolder.itemView.getTop();
        float right = viewHolder.itemView.getRight();
        float bottom = viewHolder.itemView.getBottom();
        float width = right - left;
        float height = bottom - top;
        float saveCount = canvas.save();

		canvas.clipRect(right + dX, top, right, bottom);
        canvas.drawColor(backgroundColor);

		Drawable deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_done_all_24);

		Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		circlePaint.setColor(deleteColor);

		float progress = -dX / width;
        float swipeThreshold = getSwipeThreshold(viewHolder);
        float opacity = 1f;
        float iconScale = 1f;
        float circleRadius;
        
		circleRadius = (progress - swipeThreshold) * width * CIRCLE_ACCELERATION;
		
		if (deleteIcon != null)
		{
			float cx = right - iconPadding - deleteIcon.getIntrinsicWidth() / 2f;
			float cy = top + height / 2f;
            float halfIconSize = deleteIcon.getIntrinsicWidth() * iconScale / 2f;

            deleteIcon.setBounds((int)(cx - halfIconSize), (int)(cy - halfIconSize), (int)(cx + halfIconSize), (int)(cy + halfIconSize));
            deleteIcon.setAlpha(Math.round(opacity * 255f));
			
            deleteIcon.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN));
            if (circleRadius > 0f)
			{
                canvas.drawCircle(cx, cy, circleRadius, circlePaint);
				deleteIcon.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_ATOP));
            }
            deleteIcon.draw(canvas);
		}
        canvas.restoreToCount(Math.round(saveCount));

		super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
	}

	public interface OnCompleteSwipeListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int position);
    }
}
