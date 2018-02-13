/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author leandro
 */
public class FormatarDateFx {

    public static LocalDate localInserir(DatePicker data) {
        try {
            LocalDate local = data.getValue();
            return local;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static LocalDate toLocalDate(Date d) {
        Instant instant = Instant.ofEpochMilli(d.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date formatarDateSelect(DatePicker year) {
        LocalDateTime time = year.getValue().atStartOfDay();
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String rag[]) {
        LocalDateTimeStringConverter ld = new LocalDateTimeStringConverter();
        //ld.fromString(value);
        //  System.out.println("Converter: "+converterDatayyyyMMdd(dp.getValue()));

    }

    /**
     * Converte Date para LocalDate
     *
     * @param d
     * @return LocalDate
     */
    public static LocalDate formatarDataLocal(Date d) {
        Instant instant = Instant.ofEpochMilli(d.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

}
