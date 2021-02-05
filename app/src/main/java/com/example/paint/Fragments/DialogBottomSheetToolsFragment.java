package com.example.paint.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.divyanshu.colorseekbar.ColorSeekBar;
import com.example.paint.Features.PaintActivity;
import com.example.paint.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialogBottomSheetToolsFragment extends BottomSheetDialogFragment {
    public static DialogBottomSheetToolsFragment newInstance() {
        return new DialogBottomSheetToolsFragment();
    }

    private String color;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_tools, container, false);
        final ColorSeekBar seekBar_color = view.findViewById(R.id.color_seek_bar);
        final SeekBar seekBar_sizeTool = view.findViewById(R.id.seekBar_setSizeTool);
        final RadioGroup RadioGroup_colors = view.findViewById(R.id.RadioGroup_colors);

        Bundle bundle = getArguments();

        if (bundle != null) {
            seekBar_sizeTool.setProgress(bundle.getInt("size"));
        }

        seekBar_sizeTool.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((PaintActivity) getActivity()).setSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_color.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i) {
                color = String.format("#%06X", i);
                ((PaintActivity) getActivity()).setColor(color);
            }
        });

        RadioGroup_colors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.white_radioBtn:
                        color = String.format("#%06X", getResources().getColor(R.color.colorWhite));
                        break;

                    case R.id.yellow_radioBtn:
                        color = String.format("#%06X", getResources().getColor(R.color.colorYellow));
                        break;

                    case R.id.black_radioBtn:
                        color = String.format("#%06X", getResources().getColor(R.color.colorBlack));
                        break;

                    case R.id.red_radioBtn:
                        color = String.format("#%06X", getResources().getColor(R.color.colorRed));
                        break;

                    case R.id.green_radioBtn:
                        color = String.format("#%06X", getResources().getColor(R.color.colorGreen));
                        break;

                    case R.id.blue_radioBtn:
                        color = String.format("#%06X", getResources().getColor(R.color.colorBlue));
                        break;

                    case R.id.pink_radioBtn:
                        color = String.format("#%06X", getResources().getColor(R.color.colorPink));
                        break;

                    case R.id.coffee_radioBtn:
                        color = String.format("#%06X", getResources().getColor(R.color.colorCoffee));
                        break;
                }

                ((PaintActivity) getActivity()).setColor(color);
            }
        });

        return view;
    }
}
