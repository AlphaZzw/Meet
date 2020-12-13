package com.alpha.meet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.meet.R;
import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.List;

/**
 * <p>Class: com.alpha.meet.adapter.CloudTagAdapter</p >
 * <p>Description: </p >
 * <pre>
 *
 * </pre>
 *
 * @author zhu zhengwei
 * @date 2020/12/13/上午11:55
 */
public class CloudTagAdapter extends TagsAdapter {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public CloudTagAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public View getView(Context context, int position, ViewGroup parent) {
        //初始化View
        View view = inflater.inflate(R.layout.layout_star_view_item, null);
        //初始化控件
        ImageView iv_star_icon = view.findViewById(R.id.iv_star_icon);
        TextView tv_star_name = view.findViewById(R.id.tv_star_name);
        tv_star_name.setText(mList.get(position));
        switch (position % 10) {
            case 0:
                iv_star_icon.setImageResource(R.drawable.img_star_icon_3);
                break;
            case 1:
                iv_star_icon.setImageResource(R.drawable.img_star);
                break;
            case 2:
                iv_star_icon.setImageResource(R.drawable.img_glide_load_error);
                break;
            case 3:
                iv_star_icon.setImageResource(R.drawable.img_voice_line_icon);
                break;
            case 4:
                iv_star_icon.setImageResource(R.drawable.img_answer);
                break;
            case 5:
                iv_star_icon.setImageResource(R.drawable.img_answer_icon);
                break;
            default:
                iv_star_icon.setImageResource(R.drawable.img_user_info_bg);
                break;

        }
        return view;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {

    }
}
