/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author FMaia
 */
public class FactoryCalendario {
    private static final String barra = "/";
    private static final String traco = "-";
    private static final String doisPonto = ":";
    
    public static final Integer DATA = 1;
    public static final Integer DATA_TIME = 2;
    public static final Integer TIME = 3;
    public static final Integer DATA_TIME_MILLISECOND = 4; //millisecond
    
    public static Calendar getInstance(){
        Calendar hoje = Calendar.getInstance();
        return hoje;
    }
    
    public static Calendar toCalendar(String dataString){
        String dataVetor[] = dataString.split(barra);
        Calendar dataCalendar = Calendar.getInstance();
        dataCalendar.set(Integer.parseInt(dataVetor[2]),
                Integer.parseInt(dataVetor[1])-1,Integer.parseInt(dataVetor[0]));
        return dataCalendar;
    }
    
    public static Calendar toCalendar(Date dataDate){
        if(dataDate == null)
           return null;
        Calendar dataCalendar = Calendar.getInstance();
        dataCalendar.setTime(dataDate);
        return dataCalendar;
    }
    
    public static Date toDate(Calendar dataCalendar){
        if(dataCalendar == null)
            return null;
        /*Date dataDate = new Date();
        dataDate.setTime(dataCalendar.getTimeInMillis());
        return dataDate;*/
        return dataCalendar.getTime();
    }
    
    public static String toFormatString(Calendar dataCalendar, String formato){
        if(dataCalendar == null)
           return "";
        SimpleDateFormat dataFormat = new SimpleDateFormat(formato);
        return dataFormat.format(dataCalendar.getTime());
    }
    
    public static String toFormatString(Calendar dataCalendar){
        if(dataCalendar == null)
            return "";
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormat.format(FactoryCalendario.toDate(dataCalendar));
    }
    
    public static String toFormatStringToday(String formato){
        SimpleDateFormat dataFormat = new SimpleDateFormat(formato);
        return dataFormat.format(FactoryCalendario.toDate(Calendar.getInstance()));
    }
    
    public static String toFormatStringToday(){
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormat.format(FactoryCalendario.toDate(Calendar.getInstance()));
    }
    
    public static String toDBFormat(Calendar dataCalendar){
        if(dataCalendar == null)
            return "";
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dataFormat.format(FactoryCalendario.toDate(dataCalendar));
    }
    
    public static String toDBFormat(Calendar dataCalendar, Integer op){
        String confData;
        if(dataCalendar == null)
            return "";
        switch (op){
            case 1:
                confData = "yyyy-MM-dd";
            break;
            case 2:
                confData = "yyyy-MM-dd hh:mm:ss";
            break;
            case 3:
                confData = "hh:mm:ss";
            break;
            case 4:
                confData = "yyyy-MM-dd hh:mm:ss.SSS";
            break;
            default:
                confData = "yyyy-MM-dd hh:mm:ss";
        }
        SimpleDateFormat dataFormat = new SimpleDateFormat(confData);
        return dataFormat.format(FactoryCalendario.toDate(dataCalendar));
    }
    
    public static String toDBFormat(Calendar dataCalendar, Integer op,String fuso){
        String confData;
        if(dataCalendar == null)
            return "";
        switch (op){
            case 1:
                confData = "yyyy-MM-dd";
            break;
            case 2:
                confData = "yyyy-MM-dd hh:mm:ss";
            break;
            case 3:
                confData = "hh:mm:ss";
            break;
            case 4:
                confData = "yyyy-MM-dd hh:mm:ss.SSS";
            break;
            default:
                confData = "yyyy-MM-dd hh:mm:ss";
        }
        SimpleDateFormat dataFormat = new SimpleDateFormat(confData);
        return dataFormat.format(FactoryCalendario.toDate(dataCalendar))+fuso;
    }
    
    public static Calendar toDBCalendar(String DBFormat){
        String dataVetor[] = DBFormat.substring(0, 10).split(traco);
        String timeVetor[] = DBFormat.substring(11).split(doisPonto);
        Integer ano = Integer.valueOf(dataVetor[0]);
        Integer mes = Integer.valueOf(dataVetor[1]);
        Integer dia = Integer.valueOf(dataVetor[2]);
        Integer horas = Integer.valueOf(timeVetor[0]);
        Integer minutos = Integer.valueOf(timeVetor[1]);
        Integer segundo = Integer.valueOf(timeVetor[2]);
        Calendar dataCalendar = Calendar.getInstance();
        dataCalendar.set(ano, mes-1, dia, horas, minutos,segundo);
        return dataCalendar;
    }
    
    public static java.sql.Date toDBDate(Calendar dataCalendar){
        if(dataCalendar == null)
            return null;
        return java.sql.Date.valueOf(FactoryCalendario.toDBFormat(dataCalendar,FactoryCalendario.DATA_TIME));
    }
}