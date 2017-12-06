package com.android.alejandra.spinnerejercicicio15bis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Spinner spTipoFeedBack;
	private ArrayAdapter<CharSequence> adaptador;
	private CheckBox chkRespuesta;
	private EditText nombre, email, cuerpoMensaje;
	// clase para almacenar el mensaje
	private MensajeEmail mensajeEmail;
	private Button enviar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// obtengo referencia a los objetos
		spTipoFeedBack = (Spinner) findViewById(R.id.spTipoFeedback);
		chkRespuesta = (CheckBox) findViewById(R.id.chkRespuesta);
		nombre = (EditText) findViewById(R.id.etNombre);
		email = (EditText) findViewById(R.id.etEmail);
		cuerpoMensaje = (EditText) findViewById(R.id.etMensaje);
		enviar = (Button) findViewById(R.id.btEnviar);

		enviar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// creo el mensaje
				crearMensaje(nombre, email, spTipoFeedBack, cuerpoMensaje);
				// envio el mensaje
				MainActivity.this.enviarMensajeFeedback(mensajeEmail);

			}
		});

		chkRespuesta.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			int original;

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					// guardo el color actual del checkbox (el original)
					original = chkRespuesta.getCurrentTextColor();
					// cambio de color del texto asociado al checkbox
					chkRespuesta.setTextColor(Color.GREEN);
				} else {
					// devuelvo el color original
					chkRespuesta.setTextColor(original);
				}

			}
		});

		// creo el adaptador
		adaptador = ArrayAdapter.createFromResource(this,
				R.array.tipo_feedBack, android.R.layout.simple_spinner_item);
		// asigno el tipo de layout a mostrar cuando se despliegue el spinner
		adaptador
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// asigno el adaptador al spinner
		spTipoFeedBack.setAdapter(adaptador);

		// asigno un prompt al spinner
		spTipoFeedBack.setPromptId(R.string.prompt_spinner);

	}

	private void crearMensaje(EditText nombre, EditText email,
			Spinner spTipoFeedBack, EditText cuerpoMensaje) {

		String texto = "";

		// creo un objeto de tipo mensaje, con el nombre, email, asunto y cuerpo
		mensajeEmail = new MensajeEmail(nombre.getText().toString(), email
				.getText().toString(),
				(String) spTipoFeedBack.getSelectedItem(), cuerpoMensaje
						.getText().toString());

		if (chkRespuesta.isChecked()) {
			texto = mensajeEmail.getMensaje()
					+ "\nEste mensaje requiere respuesta";

		} else {
			texto = mensajeEmail.getMensaje()
					+ "\nEste mensaje no requiere respuesta";
		}
		mensajeEmail.setMensaje(texto);

	}

	public void enviarMensajeFeedback(MensajeEmail mensajeEmail) {

		Intent intentMensaje = new Intent(android.content.Intent.ACTION_SEND);

		intentMensaje.setType("plain/text");
		// Poner a continuaci�n el e-mail que queramos

		String listaEmails[] = { mensajeEmail.getEmail() };

		intentMensaje.putExtra(android.content.Intent.EXTRA_EMAIL, listaEmails);

		intentMensaje.putExtra(android.content.Intent.EXTRA_SUBJECT,
				mensajeEmail.getAsunto());

		intentMensaje.putExtra(android.content.Intent.EXTRA_TEXT,
				mensajeEmail.getMensaje());

		startActivity(intentMensaje);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
