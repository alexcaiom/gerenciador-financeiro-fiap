/**
 * 
 */
package com.example.projetodesenvolvimento.gui.adaptadores;


import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetodesenvolvimento.CadastroMovimentoActivity;
import com.example.projetodesenvolvimento.R;
import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.UtilsData;
import com.example.projetodesenvolvimento.utils.UtilsNumero;

/**
 * @author Alex
 *
 */
public class AdaptadorListaMovimentos extends ArrayAdapter<Movimentacao> {

	private final ClasseActivity context;
	private final List<Movimentacao> movimentacoes;

	public AdaptadorListaMovimentos(ClasseActivity context, List<Movimentacao> movimentacoes) {
		super(context, R.layout.item_movimentacao, movimentacoes);
		this.context = context;
		this.movimentacoes = movimentacoes;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View item = inflater.inflate(R.layout.item_movimentacao, parent, false);
		
		final Movimentacao m = movimentacoes.get(position);
		
		//Como o item eh dividido em 3 colunas principais, vamos preenche-las em blocos
		
		//Bloco 1 - Imagem Tipo Movmento (Ganho/Gasto/Presente)
		ImageView imgTipo = (ImageView) item.findViewById(R.id.item_movimento_imgTipoMovimento);
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
		lblValor.setText(UtilsNumero.getMoeda(m.getValor().doubleValue()));
		/*TextView lblCategoria 	= (TextView) item.findViewById(R.id.item_movimento_fonte);
		lblCategoria.setText(m.getCategoria().getDescricao());*/
		
		switch (m.getTipo()) {
		case CREDITO:
			imgTipo.setImageResource(R.drawable.check_verde);
			lblDescricao.setTextColor(Color.GREEN);
			lblValor.setTextColor(Color.GREEN);
			break;
		case DEBITO:
			imgTipo.setImageResource(R.drawable.check_cinza);
			lblDescricao.setTextColor(Color.RED);
			lblValor.setTextColor(Color.RED);
			break;
		case PRESENTE:
			imgTipo.setImageResource(R.drawable.check_laranja);
			lblDescricao.setTextColor(Color.BLUE);
			lblValor.setTextColor(Color.BLUE);
		}
		
		item.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle parametro = new Bundle();
				parametro.putSerializable(Constantes.MOVIMENTACAO, m);
				context.irPara(CadastroMovimentoActivity.class, parametro);
			}
		});
		return item;
	}

}
