package com.lifeistech.android.karaokelistingapp;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Hitoe Watarai on 2017/05/14.
 */

// リスト表示制御用クラス
class ListAdapter extends ArrayAdapter<Data> {
    private LayoutInflater inflater;
    // values/colors.xmlより設定値を取得するために利用。
    private Resources r;

    public ListAdapter(Context context, List<Data> objects) {
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
        final Data data = this.getItem(position);
        TextView tvData1 = (TextView) view.findViewById(R.id.row1);
        TextView tvData2 = (TextView) view.findViewById(R.id.row2);
        TextView tvData3 = (TextView) view.findViewById(R.id.row3);
        if (data != null) {
            //1列目は日付データとしてフォーマット変更の上、表示
            SimpleDateFormat ymd = new SimpleDateFormat("MM/dd kk:mm", Locale.JAPANESE);
            tvData1.setText(ymd.format(data.getLongData()));
            //2列目は文字列なのでそのまま表示
            tvData2.setText(data.getStringData());
            //3列目は文字列なのでそのまま表示
            tvData3.setText(data.getArtistData());
        }
        //偶数行の場合の背景色を設定
        if (position % 2 == 0) {
            tvData1.setBackgroundColor(r.getColor(R.color.data1));
            tvData2.setBackgroundColor(r.getColor(R.color.data1));
            tvData3.setBackgroundColor(r.getColor(R.color.data1));
        }
        //奇数行の場合の背景色を設定
        else {
            tvData1.setBackgroundColor(r.getColor(R.color.data2));
            tvData2.setBackgroundColor(r.getColor(R.color.data2));
            tvData3.setBackgroundColor(r.getColor(R.color.data2));
        }
        return view;
    }
}