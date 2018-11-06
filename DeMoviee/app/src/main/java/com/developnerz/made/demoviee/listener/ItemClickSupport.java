package com.developnerz.made.demoviee.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.developnerz.made.demoviee.R;

/**
 * Created by Rych Emrycho on 7/21/2018 at 10:44 AM.
 * Updated by Rych Emrycho on 7/21/2018 at 10:44 AM.
 */
public class ItemClickSupport {
    private final RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    private ItemClickSupport(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        this.recyclerView.setTag(R.id.item_click_support, this);
        this.recyclerView.addOnChildAttachStateChangeListener(attachListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null){
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                onItemClickListener.onItemClicked(recyclerView, holder.getAdapterPosition(), v);
            }
        }
    };

    private View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (onItemLongClickListener != null){
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                return onItemLongClickListener.onItemLongClicked(recyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener attachListener = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if(onClickListener != null){
                view.setOnClickListener(onClickListener);
            }
            if(onLongClickListener != null){
                view.setOnLongClickListener(onLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
        }
    };

    public static ItemClickSupport addTo(RecyclerView view){
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if(support == null){
            support = new ItemClickSupport(view);
        }
        return support;
    }

    public static ItemClickSupport removeFrom(RecyclerView view){
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if(support != null){
            support.detach(view);
        }
        return support;
    }

    public ItemClickSupport setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
        return this;
    }

    public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener){
        onItemLongClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view){
        view.removeOnChildAttachStateChangeListener(attachListener);
        view.setTag(R.id.item_click_support, null);
    }

    public interface OnItemClickListener{
        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener{
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}
