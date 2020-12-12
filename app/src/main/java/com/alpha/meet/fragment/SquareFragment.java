package com.alpha.meet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.alpha.meet.R;

/**
 * <p>Class: com.alpha.meet.fragment.SquareFragment</p >
 * <p>Description: </p >
 * <pre>
 *
 * </pre>
 *
 * @author zhu zhengwei
 * @date 2020/12/12/下午2:15
 */
public class SquareFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, null);

        return view;
    }
}
