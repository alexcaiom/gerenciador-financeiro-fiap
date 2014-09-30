package com.example.projetodesenvolvimento;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.controladores.ControladorDeMovimentacoes;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.orm.modelos.enums.TipoMovimento;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.Dialogos;
import com.example.projetodesenvolvimento.utils.Sessao;
import com.example.projetodesenvolvimento.utils.UtilsData;

public class CadastroMovimentoActivity extends ClasseActivity {

	private Usuario 		usuario;
	private Movimentacao 	movimentacao;
	private EditText 		txtDescricao;
	private RadioGroup 		radioTipo;
	private RadioButton 	radioGanho;
	private RadioButton 	radioGasto;
	private EditText 		txtValor;
	private EditText 		txtData;
	private Button 			btnCadastrar;
	
	private Calendar data = GregorianCalendar.getInstance();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_movimento);
		/**Verificamos se realizaremos uma alteracao de movimento
		 * ou se apenas cadastraremos um novo
		 */
		verificaParametros(getIntent().getExtras());
		carregarTela();
	}

	private void verificaParametros(Bundle extras) {
		boolean temUsuario = Sessao.usuarioLogado();
		if (temUsuario) {
			usuario = (Usuario) Sessao.getParametro(Constantes.USUARIO);
		}
		boolean existemParametros = extras != null && !extras.isEmpty();
		if (existemParametros) {
			boolean temMovimentacao = extras.containsKey(Constantes.MOVIMENTACAO);
			if (temMovimentacao) {
				movimentacao = (Movimentacao) extras.getSerializable(Constantes.MOVIMENTACAO);
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@Override
	public void carregarTela() {
		ocultarBarraDeAcoes();
		txtDescricao 	= (EditText) 	findViewById(R.id.movimento_cadastro_txtDescricao);
		radioTipo 		= (RadioGroup) 	findViewById(R.id.movimento_cadastro_tipoMovimento);
		radioGasto		= (RadioButton) findViewById(R.id.movimento_cadastro_radioGasto);
		radioGanho		= (RadioButton) findViewById(R.id.movimento_cadastro_radioGanho);
		txtValor 		= (EditText) 	findViewById(R.id.movimento_cadastro_txtValor);
		
		txtData		 	= (EditText) 	findViewById(R.id.movimento_cadastro_txtData);
		btnCadastrar	= (Button) 		findViewById(R.id.movimento_cadastro_btnCadastrar);
		
		boolean temMovimentacao = existe(movimentacao);
		if (temMovimentacao) {
			txtDescricao.setText(movimentacao.getDescricao());
			boolean movimentoEhTipoGanho = movimentacao.getTipo().equals(TipoMovimento.CREDITO); 
			boolean movimentoEhTipoGasto = movimentacao.getTipo().equals(TipoMovimento.DEBITO);
			radioGanho.setSelected(movimentoEhTipoGanho);
			radioGasto.setSelected(movimentoEhTipoGasto);
			txtValor.setText(String.valueOf(movimentacao.getValor().doubleValue()));
			try {
				txtData.setText(UtilsData.calendarToString(movimentacao.getData()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			txtValor.setText(Double.valueOf(0.0).toString());
			try {
				txtData.setText(UtilsData.getDataHojeExtenso());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		txtData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				exibirDialogoCampoData();
			}
		});
		
		carregarEventos();
	}

	@Override
	public void carregarEventos() {
		btnCadastrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean dadosValidos = validarDadosFormulario();
				if (dadosValidos) {
					preencheVO();
					gravar();
				}
			}
		});
		
	}

	protected boolean validarDadosFormulario() {
		if (campoEmBranco(txtDescricao)) {
			mostrarMensagemPreenchimentoParaOCampo("Descrição");
			darFocoAoCampo(txtDescricao);
			return false;
		}
		if (radioTipo.getCheckedRadioButtonId() == -1) {
			mostrarMensagemPreenchimentoParaOCampo("Tipo");
			darFocoAoCampo(radioTipo);
			return false;
		}
		if (campoEmBranco(txtValor) || txtValor.getText().toString().equals("R$ 0.00") || txtValor.getText().toString().equals("0.00")) {
			mostrarMensagemPreenchimentoParaOCampo("Valor");
			darFocoAoCampo(txtValor);
			return false;
		}
		return true;
	}

	protected void preencheVO() {
		if (naoExiste(movimentacao)) {
			movimentacao = new Movimentacao();
		}
		movimentacao.comDescricao(txtDescricao.getText().toString());
		movimentacao.comLogin(usuario.getLogin());
		movimentacao.comValor(new BigDecimal(txtValor.getText().toString()));
		movimentacao.comData(data);
		boolean ehCredito 	= radioTipo.getCheckedRadioButtonId() == radioGanho.getId();
		boolean ehDebito 	= radioTipo.getCheckedRadioButtonId() == radioGasto.getId();
		if (ehCredito) {
			movimentacao.comTipo(TipoMovimento.CREDITO);
		} else if (ehDebito) {
			movimentacao.comTipo(TipoMovimento.DEBITO);
		}
	}
	
	protected void gravar() {
		ControladorDeMovimentacoes.getInstancia(CadastroMovimentoActivity.this).gravar(movimentacao);
	}
	
	private boolean campoEmBranco(EditText campo){
		if (existe(campo)) {
			return campo.getText().toString().isEmpty();
		}
		return false;
	}
	
	private void mostrarMensagemPreenchimentoParaOCampo(String campo) {
		Dialogos.Alerta.exibirMensagemInformacao(CadastroMovimentoActivity.this, false, "O campo "+campo+" deve ser preenchido!", "Campo não preenchido!", null);
	}
	
	private void darFocoAoCampo(View campo) {
		campo.requestFocus();
	}
	
	private void exibirDialogoCampoData(){
		final AlertDialog dialogo ;
		View viewApontadorData = getLayoutInflater().inflate(R.layout.dialogo_apontador_data, null);
		final DatePicker apontador = (DatePicker) viewApontadorData.findViewById(R.id.apontadorData);
		
		android.content.DialogInterface.OnClickListener escutadorOk = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				data.setTimeInMillis(apontador.getCalendarView().getDate());
				try {
					txtData.setText(UtilsData.calendarToString(data));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		
		dialogo = new AlertDialog.Builder(this)
						.setCancelable(true)
						.setNeutralButton("Cancelar", null)
						.setPositiveButton("OK", escutadorOk)
						.setView(viewApontadorData)
						.setTitle(txtData.getText().toString())
						.create();
		apontador.getCalendarView().setOnDateChangeListener(new OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				data.set(year, month, dayOfMonth);
				try {
					dialogo.setTitle(UtilsData.calendarToString(data));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		dialogo.show();
	}

}
