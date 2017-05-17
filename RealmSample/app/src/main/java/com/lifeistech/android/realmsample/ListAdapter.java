package com.lifeistech.android.realmsample;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Hitoe Watarai on 2017/05/17.
 */
/*
// リスト表示制御用クラス
class ListAdapter extends ArrayAdapter<Memo2> {
    private LayoutInflater inflater;

    public ListAdapter(Context context, List<Memo2> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        final Memo2 data = this.getItem(position);
        EditText text1 = (EditText)view.findViewById(R.id.editText);

        if (data != null) {
            text1.setText(data.getStringData());
        }
        return view;
    }
}*/

// リスト表示制御用クラス
class ListAdapter extends ArrayAdapter<Memo2> {
    private LayoutInflater inflater;
    // values/colors.xmlより設定値を取得するために利用。
    private Resources r;

    public ListAdapter(Context context, List<Memo2> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        r = context.getResources();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        // layout/raw.xmlを紐付ける
        if (view == null) {
            view = inflater.inflate(R.layout.row, parent, false);
        }
        final Memo2 data = this.getItem(position);
        TextView tvData1 = (TextView) view.findViewById(R.id.row1);
        TextView tvData2 = (TextView) view.findViewById(R.id.row2);
        TextView tvData3 = (TextView) view.findViewById(R.id.row3);
        if (data != null) {
            //1列目は日付データとしてフォーマット変更の上、表示
            //2列目は文字列なのでそのまま表示
            tvData2.setText(data.getStringData());
        }
        return view;
    }
}