package com.organizadorfinanceiro.telas.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.organizadorfinanceiro.R;

/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_graficos_dummy,
					container, false);
			WebView viewGrafico = (WebView) rootView.findViewById(R.id.grafico_wvGrafico);
			String url = getArguments().getString("URL");
			viewGrafico.loadUrl(url);
			return rootView;
		}
	}