/**
 * 
 */
package com.example.projetodesenvolvimento.gui.adaptadores;


import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetodesenvolvimento.R;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.utils.UtilsData;

/**
 * @author Alex
 *
 */
public class AdaptadorListaMovimentos extends ArrayAdapter<Movimentacao> {

	private final Context context;
	private final List<Movimentacao> movimentacoes;

	public AdaptadorListaMovimentos(Context context, List<Movimentacao> movimentacoes) {
		super(context, R.layout.item_movimentacao, movimentacoes);
		this.context = context;
		this.movimentacoes = movimentacoes;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View item = inflater.inflate(R.layout.item_movimentacao, parent, false);
		
		Movimentacao m = movimentacoes.get(position);
		
		//Como o item eh dividido em 3 colunas principais, vamos preenche-las em blocos
		
		//Bloco 1 - Imagem Tipo Movmento (Ganho/Gasto/Presente)
		ImageView imgTipo = (ImageView) item.findViewById(R.id.item_movimento_imgTipoMovimento);
		switch (m.getTipo()) {
		case CREDITO:
			imgTipo.setImageResource(R.drawable.check_verde);
			break;
		case DEBITO:
			imgTipo.setImageResource(R.drawable.check_cinza);
			break;
		case PRESENTE:
			imgTipo.setImageResource(R.drawable.check_laranja);
		}
		
		//Bloco 2 - Descricao e Data do Movimento
		TextView lblDescricao 	= (TextView) item.findViewById(R.id.item_movimento_descricao);
		lblDescricao.setText(m.getDescricao());
		TextView lblData 		= (TextView) item.findViewById(R.id.item_movimento_data);
		try {
			lblData.setText(UtilsData.calendarToStringTelaItens(m.getData()));
			if (m.getData().equals(UtilsData.getDataAtual())) {
				lblData.setTypeface(null, Typeface.BOLD);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Bloco 3 - Valor e Categoria da Fonte
		TextView lblValor 		= (TextView) item.findViewById(R.id.item_movimento_valor);
		lblValor.setText(lblValor.getText().toString()+String.valueOf(m.getValor().doubleValue()));
		/*TextView lblCategoria 	= (TextView) item.findViewById(R.id.item_movimento_fonte);
		lblCategoria.setText(m.getCategoria().getDescricao());*/
		return item;
	}

}
