package com.organizadorfinanceiro;

import java.util.GregorianCalendar;

import android.content.Context;
import android.view.View;
import android.widget.GridLayout;

public class CalendarioBuilder {

	/*private String[] meses = {
			"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"
	};
	
	public void montaCalendario(View painelSuperior, View painelCalendario, GregorianCalendar data, Context contexto){

		int dias[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String diasDaSemana[] = { "Dom", "Seg", "Ter", "Quar", "Qui", "Sex", "S�b" };
		String meses[] = { "Janeiro", "Fevereiro", "Mar�o", "Abril", "Maio", "Junho",
				"Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };

		painelCalendario=new GridLayout(contexto);
		drawCalendar(meses[data.get(GregorianCalendar.MONTH)], (1900 + data.get(GregorianCalendar.YEAR)));
		year.setSelectedItem((1900 + data.getYear()));
		month.setSelectedItem(meses[data.get(GregorianCalendar.MONTH)]);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		add(p1);
		add(p2);


	}

		public Integer[] getDiasDaSemana(){
			Integer[] dias = { 
					GregorianCalendar.SUNDAY,
					GregorianCalendar.MONDAY,
					GregorianCalendar.TUESDAY,
					GregorianCalendar.WEDNESDAY,
					GregorianCalendar.THURSDAY,
					GregorianCalendar.FRIDAY,
					GregorianCalendar.SATURDAY
			};
			return dias;
		}


	
	public void drawCalendar(String inputMonth, int inputYear) {
		p2.removeAll();
		for (int i = 0; i < weekdays.length; i++) {
			JLabel label = new JLabel(weekdays[i]);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			p2.add(label);
		}
		Date date = new Date("01-" + inputMonth + "-" + inputYear);
		int noOfDaysInMonth = days[date.getMonth()];
		if (date.getYear() % 4 == 0 && date.getMonth() == 1) {
			noOfDaysInMonth = 29;
		}

		for (int i = 1, day = 1; day <= noOfDaysInMonth; i++) {
			for (int j = 0; j < 7; j++) {
				if (day == 1) {
					if (j == date.getDay()) {
						JLabel label = new JLabel(String.valueOf(day));
						label.setHorizontalAlignment(SwingConstants.RIGHT);
						p2.add(label);
						day++;
					} else {
						JLabel label = new JLabel("");
						p2.add(label);
					}
				} else {
					JLabel label = new JLabel(String.valueOf(day));
					label.setHorizontalAlignment(SwingConstants.RIGHT);
					p2.add(label);
					day++;
				}
				if (day > noOfDaysInMonth) {
					break;
				}
			}
		}
		p2.validate();
		setTitle(inputMonth + ", " + inputYear);
	}*/

}
