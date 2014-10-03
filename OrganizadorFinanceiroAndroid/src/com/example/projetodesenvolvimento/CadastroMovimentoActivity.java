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
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.controladores.ControladorDeMovimentacoes;
import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.excecoes.SysErr;
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
	private Spinner			cmbFonte;
	private Button			btnGerenciarFontes;
	private Button 			btnCadastrar;
	private Button			btnExcluir;
	
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
		txtDescricao 		= (EditText) 	findViewById(R.id.movimento_cadastro_txtDescricao);
		radioTipo 			= (RadioGroup) 	findViewById(R.id.movimento_cadastro_tipoMovimento);
		radioGasto			= (RadioButton) findViewById(R.id.movimento_cadastro_radioGasto);
		radioGanho			= (RadioButton) findViewById(R.id.movimento_cadastro_radioGanho);
		txtValor 			= (EditText) 	findViewById(R.id.movimento_cadastro_txtValor);
		cmbFonte			= (Spinner) 	findViewById(R.id.movimento_cadastro_cmbFonte);
		btnGerenciarFontes 	= (Button) 		findViewById(R.id.movimento_cadastro_btnGerenciarFontes);
		txtData		 		= (EditText) 	findViewById(R.id.movimento_cadastro_txtData);
		btnCadastrar		= (Button) 		findViewById(R.id.movimento_cadastro_btnCadastrar);
		btnExcluir			= (Button) 		findViewById(R.id.movimento_cadastro_btnExcluir);
		
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
			try {
				txtData.setText(UtilsData.getDataHojeExtenso());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		btnGerenciarFontes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		txtData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				exibirDialogoCampoData();
			}
		});
		txtData.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					exibirDialogoCampoData();
				}
			}
		});
		
		btnGerenciarFontes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(GestaoFontesMovimentoActivity.class);
			}
		});
		btnExcluir.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				android.content.DialogInterface.OnClickListener acaoSim = new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							ControladorDeMovimentacoes.getInstancia(CadastroMovimentoActivity.this).excluir(movimentacao);
							onBackPressed();
						} catch (SysErr e) {
							Dialogos.Alerta.exibirMensagemErro(e, CadastroMovimentoActivity.this, null);
						}
					}
				};
				
				Dialogos.Alerta.exibirMensagemPergunta(CadastroMovimentoActivity.this, true, "Deseja mesmo excluir este movimento?", "Alerta", acaoSim, null, null);
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
		if (!txtValor.getText().toString().isEmpty()) {
			movimentacao.comValor(new BigDecimal(txtValor.getText().toString()));
		} else {
			movimentacao.comValor(new BigDecimal(0));	
		}
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
		try{
			ControladorDeMovimentacoes.getInstancia(CadastroMovimentoActivity.this).gravar(movimentacao);
			android.content.DialogInterface.OnClickListener escutadorOk = new android.content.DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					onBackPressed();
				}
			};
			Dialogos.Alerta.exibirMensagemInformacao(CadastroMovimentoActivity.this, false, "Cadastrado com sucesso!", "Informação", escutadorOk );
		}catch (Erro e){
			Dialogos.Alerta.exibirMensagemErro(e, CadastroMovimentoActivity.this, null);
		}
		
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
					dialogo.setTitle(UtilsData.calendarToStringDataCompleta(data));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		dialogo.show();
	}

}
