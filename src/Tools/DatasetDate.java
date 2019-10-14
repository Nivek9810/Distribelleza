/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author user
 */
public class DatasetDate {

    private String typeRange;
    private int typeConditional, typeResult, before;

    private Date date_I, date_F;

    public DatasetDate(String typeRange, int typeConditional, int typeResult, int before, Date date_I, Date date_F) {
        this.typeRange = typeRange;
        this.typeConditional = typeConditional;
        this.typeResult = typeResult;
        this.before = before;
        this.date_I = date_I;
        this.date_F = date_F;
    }

    public DatasetDate(Date date_I, Date date_F) {
        this.typeRange = "";
        this.typeConditional = Calendar.DAY_OF_MONTH;
        this.typeResult = Calendar.HOUR_OF_DAY;
        this.date_I = date_I;
        this.date_F = date_F;
        this.before = 1;
    }

    public LocalDateTime restarFecha(String fechaYHora, long cant, int type) {
        /*
        1: Días
        2: Meses
        3: Años
         */
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime fechaYHoraLocal = LocalDateTime.parse(fechaYHora, formateador);
        switch (type) {
            case 1:
                fechaYHoraLocal = fechaYHoraLocal.minusDays(cant);
                break;
            case 2:
                fechaYHoraLocal = fechaYHoraLocal.minusMonths(cant);
                break;
            case 3:
                fechaYHoraLocal = fechaYHoraLocal.minusYears(cant);
                break;
            default:
                break;
        }
        return fechaYHoraLocal;
    }

    private int calcularDiferencia(Date fechaInicial, Date fechaFinal) {
        return (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
    }

    public String getTypeRange() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat dt = new SimpleDateFormat("uuuu-MM-dd HH:mm:ss");

        Calendar cInicial = GregorianCalendar.getInstance();
        Calendar cFinal = GregorianCalendar.getInstance();
        Locale locale = Locale.getDefault();
        cInicial.setTime(date_I);
        cFinal.setTime(date_F);

        /*
            Hay que modificar el (Dia del mes --> Hora) -> De acuerdo al tipo.
            (Mes del año --> Dia) -> De acuerdo al tipo.
            (Año --> mes) -> De acuerdo al tipo.
         */
        int diferencia = this.calcularDiferencia(date_I, date_F);
        System.out.println("Diferencia en DatasetDate: " + diferencia);
        if (diferencia >= 0 && diferencia < 29) {
            this.typeConditional = Calendar.DAY_OF_MONTH;
            this.typeResult = Calendar.HOUR_OF_DAY;
            this.before = this.restarFecha(dt.format(date_F.getTime()), 1, 1).getDayOfMonth();
            return " desde el Día " + sdf.format(this.date_F.getTime());
        } else if (diferencia >= 29 && diferencia <= 364) {
            this.typeConditional = Calendar.MONTH;
            this.typeResult = Calendar.DAY_OF_MONTH;
            this.before = this.restarFecha(dt.format(date_F.getTime()), 1, 2).getMonthValue();
            return " Mes de "
                    + cInicial.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) + " a "
                    + cFinal.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) + " del "
                    + cFinal.get(Calendar.YEAR);
        } else if (diferencia >= 365) {
            this.typeConditional = Calendar.YEAR;
            this.typeResult = Calendar.YEAR;
            this.before = this.restarFecha(dt.format(date_F.getTime()), 1, 3).getYear();
            return " Año " + cInicial.get(Calendar.YEAR) + " al " + cFinal.get(Calendar.YEAR);
        } else {
            return typeRange;
        }
    }

    public int getTypeConditional() {
        return typeConditional;
    }

    public int getTypeResult() {
        return typeResult;
    }

    public int getBefore() {
        return before;
    }

}
