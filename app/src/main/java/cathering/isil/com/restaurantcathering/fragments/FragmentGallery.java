/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cathering.isil.com.restaurantcathering.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

import cathering.isil.com.restaurantcathering.R;

public class FragmentGallery extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

	private SliderLayout mSlider;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);


		mSlider = (SliderLayout) rootView.findViewById(R.id.slider);

		HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
		file_maps.put("Salon principal 1",R.drawable.g1);
		file_maps.put("Salon buffet ",R.drawable.g2);
		file_maps.put("Salon principal 2",R.drawable.g3);
		file_maps.put("Salon de bodas", R.drawable.g4);
		file_maps.put("Salon secundario 1", R.drawable.g5);
		file_maps.put("Salon secundario 2", R.drawable.g6);

		for(String name : file_maps.keySet()){
			TextSliderView textSliderView = new TextSliderView(getActivity().getApplicationContext());
			// initialize a SliderLayout
			textSliderView
					.description(name)
					.image(file_maps.get(name))
					.setScaleType(BaseSliderView.ScaleType.Fit)
					.setOnSliderClickListener(this);

			//add your extra information
			textSliderView.bundle(new Bundle());
			textSliderView.getBundle()
					.putString("extra",name);

			mSlider.addSlider(textSliderView);
		}

		mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
		mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
		mSlider.setCustomAnimation(new DescriptionAnimation());
		mSlider.setDuration(4000);
		mSlider.addOnPageChangeListener(this);

		return rootView;		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onSliderClick(BaseSliderView baseSliderView) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

	@Override
	public void onPageSelected(int position) {

	}

	@Override
	public void onPageScrollStateChanged(int state) {}

}
