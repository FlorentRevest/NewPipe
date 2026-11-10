package org.schabi.newpipe.local;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Holder for the header and the footer of a list. The header/footer view is owned by the fragment
 * and there is only one instance of it, but {@link RecyclerView} may create multiple holders for
 * the same view type (e.g. when the previous holder could not be recycled), and requires each
 * holder's item view to be a new, unattached view. Therefore each holder owns an empty container,
 * and the actual header/footer view is moved into the container of the holder being bound.
 */
public class HeaderFooterHolder extends RecyclerView.ViewHolder {
    private final FrameLayout container;

    public HeaderFooterHolder(@NonNull final ViewGroup parent) {
        super(new FrameLayout(parent.getContext()));
        container = (FrameLayout) itemView;
        container.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    /**
     * Shows the provided view in this holder, detaching it from the holder it was previously
     * shown in, if any.
     *
     * @param view the header or footer view to show, or {@code null} to show nothing
     */
    public void setView(@Nullable final View view) {
        if (view != null && view.getParent() == container) {
            return;
        }

        container.removeAllViews();
        if (view != null) {
            if (view.getParent() instanceof ViewGroup) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            container.addView(view);
        }
    }
}
