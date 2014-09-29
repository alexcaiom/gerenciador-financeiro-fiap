package com.example.projetodesenvolvimento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.example.projetodesenvolvimento.abstratas.ClasseFragmentoActivity;
import com.example.projetodesenvolvimento.telas.fragmentos.DummySectionFragment;

public class GraficosActivity extends ClasseFragmentoActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	String urlGraficoBarrasVertical = "http://chart.apis.google.com/chart?cht=bvg&chs=600x400&chd=t:100,50,115,80,90,70|10,20,15,30,40,25&chxt=y,x&chxl=1:|Janeiro|Fevereiro|Marco|Abril|Maio|Junho&chxr=0,0,120&chds=0,120&chco=4D8FF9&chbh=15,0,15&chg=8.13,0,10,0&chco=0AD58F,FB0601&chdl=Vendas|Compras";
	String urlGraficoPizza = "http://chart.apis.google.com/chart?cht=p3&chs=450x650&chd=t:100,50,115,80,60|10,20,15,30,50&chxt=x,y&chxl=1:|Janeiro|Fevereiro|Marco|Abril|Maio&chxr=0,0,120&chds=0,120&chco=4D89F9&chbh=35,0,15&chg=8.33,0,5,0&chco=0A8C8A,EBB671&chdl=Ganhos|Gastos";

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graficos);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graficos, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle parametros = new Bundle();
			switch (position) {
			case 0:
				parametros.putString("URL", urlGraficoBarrasVertical);
				break;
			case 1:
				parametros.putString("URL", urlGraficoPizza);
			default:
				break;
			}
			fragment.setArguments(parametros);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "Gráfico de Barra";
			case 1:
				return "Gráfico de Pizza";
			}
			return null;
		}
	}

	@Override
	public void carregarTela() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carregarEventos() {
		// TODO Auto-generated method stub
		
	}

	

}
