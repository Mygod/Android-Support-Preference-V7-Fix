package com.takisoft.fix.support.v7.preference.widget;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;

import static com.takisoft.fix.support.v7.preference.widget.SimpleMenuPopupWindow.DIALOG;
import static com.takisoft.fix.support.v7.preference.widget.SimpleMenuPopupWindow.HORIZONTAL;

public class SimpleMenuListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public CheckedTextView mCheckedTextView;

    private SimpleMenuPopupWindow mWindow;

    public SimpleMenuListItemHolder(View itemView) {
        super(itemView);

        mCheckedTextView = itemView.findViewById(android.R.id.text1);
        itemView.setOnClickListener(this);
    }

    public void bind(SimpleMenuPopupWindow window, int position) {
        mWindow = window;
        mCheckedTextView.setText(mWindow.getEntries()[position]);
        mCheckedTextView.setChecked(position == mWindow.getSelectedIndex());
        mCheckedTextView.setMaxLines(mWindow.getMode() == DIALOG ? Integer.MAX_VALUE : 1);

        int padding = mWindow.listPadding[mWindow.getMode()][HORIZONTAL];
        int paddingVertical = mCheckedTextView.getPaddingTop();
        mCheckedTextView.setPadding(padding, paddingVertical, padding, paddingVertical);
    }

    @Override
    public void onClick(View view) {
        if (mWindow.getOnItemClickListener() != null) {
            mWindow.getOnItemClickListener().onClick(getAdapterPosition());
        }

        if (mWindow.isShowing()) {
            mWindow.dismiss();
        }
    }
}
