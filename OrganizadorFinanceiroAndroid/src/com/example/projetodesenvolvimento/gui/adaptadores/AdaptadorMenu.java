package com.example.projetodesenvolvimento.gui.adaptadores;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetodesenvolvimento.R;
import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.gui.GridItemVO;

public class AdaptadorMenu extends BaseAdapter {

	Context contexto;
	private List<GridItemVO> itens;

	public AdaptadorMenu(Context c, GridItemVO[] itensPropriedades) {
		this.contexto = c;
		this.itens = Arrays.asList(itensPropriedades);
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public Object getItem(int position) {
		return itens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater li = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.grid_item, null);
			
			GridItemVO vo = itens.get(position);
			
			ImageView iv = (ImageView) v.findViewById(R.id.grid_item_image);
			iv.setImageResource(vo.getIdImagem());
			iv.getLayoutParams().width = LayoutParams.MATCH_PARENT;
			TextView tv = (TextView) v.findViewById(R.id.grid_item_text);
			tv.setText(contexto.getString(vo.getIdLabel()));
			
			v.setOnClickListener(vo.getAcaoClique());
		}
		return v;
	}
}
