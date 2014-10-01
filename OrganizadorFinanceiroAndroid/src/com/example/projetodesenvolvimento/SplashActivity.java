package com.example.projetodesenvolvimento;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.adaptadores.GalleryImageAdapter;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.utils.Dialogos;
import com.example.projetodesenvolvimento.utils.GeradorSQLBean;

public class SplashActivity extends ClasseActivity {
	
//	ImageView imagemInstrucoes;
	Gallery galeria;
	Button btnCadastrar, btnLogin;
	private int[] IMAGE_IDS = {
								R.drawable.tela01_quadro01,
								R.drawable.tela01_quadro02,
								R.drawable.tela01_quadro03,
								R.drawable.tela01_quadro04,
								R.drawable.tela01_quadro05
	};
	private int indice = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		carregarTela();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public void carregarTela() {
		ocultarBarraDeAcoes();
//		imagemInstrucoes = (ImageView) findViewById(R.id.img_instrucoes);
		galeria 		= (Gallery) findViewById(R.id.splash_imagens);
		btnCadastrar 	= (Button) findViewById(R.id.btnIrParaTelaCadastro);
		btnLogin 		= (Button) findViewById(R.id.btnIrParaTelaLogin);
		carregarEventos();
		
	}

	@Override
	public void carregarEventos() {
		btnCadastrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(CadastroUsuarioActivity.class);
			}
		});
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(LoginActivity.class);
			}
		});
		
		Integer[] idsDasImagens = { R.drawable.tela01_quadro01, 
			R.drawable.tela01_quadro02,
			R.drawable.tela01_quadro03,
			R.drawable.tela01_quadro04,
			R.drawable.tela01_quadro05
		  };
		galeria.setAdapter(new GalleryImageAdapter(this, idsDasImagens));
		
		/*galeria.setOnDragListener(new OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {
				avisar("drag começou: view ("+v.getX()+") evento ("+event.getX()+")", Toast.LENGTH_SHORT);
				return false;
			}
		});*/
		
	}
	
}
